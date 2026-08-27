package com.example.techsupport.controllers;

import com.example.techsupport.entities.Equipamento;
import com.example.techsupport.repository.EquipamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamento")
@Tag(name = "Equipamentos", description = "Endpoints responsáveis pelo gerenciamento de equipamentos do sistema TechSupport, permitindo consultar e cadastrar equipamentos.")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    @Operation(summary = "Método de consulta de listas de equipamentos!", description = "Método reponsável em efetuar a consulta de todos os equipamentos sem filtro!")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(equipamentoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de equipamentos!", description = "Método reponsável em efetuar a criação de novos equipamentos!")
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento){

        var equipamentoBanco = equipamentoRepository.save(equipamento);
        return ResponseEntity.ok(equipamentoBanco);
    }

}
