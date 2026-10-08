package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.SolicitanteResponse;
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
}
