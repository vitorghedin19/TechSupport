package com.example.techsupport.controllers;

import com.example.techsupport.entities.Equipamento;
import com.example.techsupport.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(equipamentoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento){

        var equipamentoBanco = equipamentoRepository.save(equipamento);
        return ResponseEntity.ok(equipamentoBanco);
    }

}
