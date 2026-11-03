package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ChamadoRequest;
import com.example.techsupport.application.DTOs.ChamadoResponse;
import com.example.techsupport.application.DTOs.EnumStatusChamado;
import com.example.techsupport.application.DTOs.SolicitanteLogado;
import com.example.techsupport.domain.entities.Chamado;
import com.example.techsupport.domain.repository.ChamadoRepository;
import com.example.techsupport.domain.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    // Quem esta logado, lido do token. Pode ser UsuarioLogado (tecnico/admin) OU SolicitanteLogado.
    private Object getLogado(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // Tecnico/admin acessam qualquer chamado. Solicitante so acessa os que ele mesmo abriu.
    private boolean podeAcessar(Chamado chamado){
        if (getLogado() instanceof SolicitanteLogado solicitanteLogado){
            return chamado.getSolicitante() != null
                    && chamado.getSolicitante().getId().equals(solicitanteLogado.id());
        }
        return true;
    }

    public List<ChamadoResponse> listarTodosChamadosTable(){

        List<Chamado> chamados;

        if (getLogado() instanceof SolicitanteLogado solicitanteLogado){
            chamados = chamadoRepository.findBySolicitante_Id(solicitanteLogado.id());
        } else {
            chamados = chamadoRepository.findAll();
        }

        return chamados
                .stream()
                .map(ChamadoResponse::new)
                .toList();

    }

    public ChamadoResponse buscarPorId(Long id) {
        var chamado = chamadoRepository.findById(id).orElse(null);
        if (chamado == null || !podeAcessar(chamado)){
            return null;
        }
        return new ChamadoResponse(chamado);
    }

    public ChamadoResponse salvarChamado(ChamadoRequest chamado, Long id) {

        //Novo Chamado
        if (id == 0L){
            Chamado chamadoBanco = new Chamado(chamado);

            if (getLogado() instanceof SolicitanteLogado solicitanteLogado){
                chamadoBanco.setSolicitante(solicitanteRepository.getReferenceById(solicitanteLogado.id()));
            }

            chamadoRepository.save(chamadoBanco);
            return new ChamadoResponse(chamadoBanco);
        }
        else {
            //Chamado existente: so edita titulo, descricao e prioridade (status e solucao ficam de fora)
            Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
            if ( chamadoBanco != null && podeAcessar(chamadoBanco) ){
                chamadoBanco.setTitulo(chamado.titulo());
                chamadoBanco.setDescricao(chamado.descricao());
                chamadoBanco.setPrioridade(chamado.prioridade());
                chamadoRepository.save(chamadoBanco);
                return new ChamadoResponse(chamadoBanco);
            }
        }
        return null;
    }

    public boolean atualizarStatus(EnumStatusChamado status, Long id) {

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null){
            chamadoBanco.setStatus(status);
            chamadoRepository.save(chamadoBanco);
            return true;
        }
        return false;
    }

    // Quem decide se pode resolver ou nao e o metodo resolver() la na entidade -
    // aqui so orquestra a busca e o save.
    public ChamadoResponse resolver(Long id, String solucaoAdotada) {

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco == null) {
            return null;
        }

        chamadoBanco.resolver(solucaoAdotada); // pode lancar IllegalArgumentException
        chamadoRepository.save(chamadoBanco);
        return new ChamadoResponse(chamadoBanco);
    }
}