package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.EnumStatusSolicitante;
import com.example.techsupport.application.DTOs.SolicitanteRequest;
import com.example.techsupport.application.DTOs.SolicitanteResponse;
import com.example.techsupport.domain.entities.Solicitante;
import com.example.techsupport.domain.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;


    public List<SolicitanteResponse> listarTodosSolicitantesTable(){

        return solicitanteRepository.findAll()
                .stream()
                .map(SolicitanteResponse::new)
                .toList();

    }

    public SolicitanteResponse buscarPorId(Long id) {
        var solicitante = solicitanteRepository.findById(id).orElse(null);
        if (solicitante == null){
            return null;
        }
        return new SolicitanteResponse(solicitante);
    }

    public SolicitanteResponse salvarSolicitante(SolicitanteRequest solicitante, Long id) {

        //Novo Solicitante
        if (id == 0L){
            Solicitante solicitanteBanco = new Solicitante(solicitante);
            solicitanteRepository.save(solicitanteBanco);
            return new SolicitanteResponse(solicitanteBanco);
        }
        else {
            //Solicitante existente
            Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
            if ( solicitanteBanco != null ){
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
        if (solicitanteBanco != null){
            solicitanteBanco.setStatus(status);
            solicitanteRepository.save(solicitanteBanco);
            return true;
        }
        return false;
    }
}
