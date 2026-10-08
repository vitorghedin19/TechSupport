package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.EquipamentoResponse;
import com.example.techsupport.domain.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    public List<EquipamentoResponse> listarTodosEquipamentosTable(){

        return equipamentoRepository.findAll()
                .stream()
                .map(EquipamentoResponse::new)
                .toList();
    }
}
