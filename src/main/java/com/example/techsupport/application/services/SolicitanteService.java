package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.domain.entities.Solicitante;
import com.example.techsupport.domain.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ContextoSegurancaService contexto;


    public List<SolicitanteResponse> listarTodosSolicitantesTable(){

        List<Solicitante> solicitantes;

        if (contexto.isAdmin()) {
            solicitantes = solicitanteRepository.findAll();
        } else {
            Long empresaId = contexto.getEmpresaId();
            solicitantes = empresaId == null ? List.of() : solicitanteRepository.findByEmpresa_Id(empresaId);
        }

        return solicitantes
                .stream()
                .map(SolicitanteResponse::new)
                .toList();

    }

    public SolicitanteResponse buscarPorId(Long id) {
        var solicitante = solicitanteRepository.findById(id).orElse(null);
        if (solicitante == null || !contexto.podeAcessar(solicitante.getEmpresa())){
            return null;
        }
        return new SolicitanteResponse(solicitante);
    }

    public SolicitanteResponse salvarSolicitante(SolicitanteRequest solicitante, Long id) {

        //Novo Solicitante
        if (id == 0L){
            Solicitante solicitanteBanco = new Solicitante(solicitante);
            solicitanteBanco.setEmpresa(contexto.getEmpresaDoLogado());
            solicitanteRepository.save(solicitanteBanco);
            return new SolicitanteResponse(solicitanteBanco);
        }
        else {
            //Solicitante existente
            Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
            if ( solicitanteBanco != null && contexto.podeAcessar(solicitanteBanco.getEmpresa()) ){
                solicitanteBanco.setNome(solicitante.nome());
                solicitanteBanco.setEmail(solicitante.email());
                solicitanteBanco.setSetor(solicitante.setor());
                solicitanteRepository.save(solicitanteBanco);
                return new SolicitanteResponse(solicitanteBanco);
            }
        }
        return null;
    }

    public boolean atualizarStatus(EnumStatusSolicitante status, Long id) {

        Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
        if (solicitanteBanco != null && contexto.podeAcessar(solicitanteBanco.getEmpresa())){
            solicitanteBanco.setStatus(status);
            solicitanteRepository.save(solicitanteBanco);
            return true;
        }
        return false;
    }


    public LoginResponse validarSolicitanteAutenticadoRetornaToken (LoginRequest request) { // NOVO

        if (solicitanteRepository.existsSolicitanteByEmailAndSenha(request.email(), request.senha())){
            var token = tokenService.gerarTokenSolicitante(request);
            return new LoginResponse(token);
        }
        return null;
    }

    public SolicitanteLogado getSolicitanteLogado(){
        return (SolicitanteLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public SolicitanteResponse buscarSolicitanteLogado() {

        try {

            var solicitanteLogado = getSolicitanteLogado();
            return solicitanteRepository.findById(solicitanteLogado.id()).stream().map(SolicitanteResponse::new).findFirst().orElse(null);

        }catch (Exception e) {
            throw new RuntimeException("Erro ao buscar solicitante logado");
        }
    }

}