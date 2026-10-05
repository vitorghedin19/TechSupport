package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ChamadoResponse;
import com.example.techsupport.domain.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;


    public List<ChamadoResponse> listarTodosChamadosTable(){

        return chamadoRepository.findAll()
                .stream()
                .map(ChamadoResponse::new)
                .toList();

    }
}
