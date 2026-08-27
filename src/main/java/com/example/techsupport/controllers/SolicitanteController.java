package com.example.techsupport.controllers;

import com.example.techsupport.entities.Solicitante;
import com.example.techsupport.repository.SolicitanteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitante")
@Tag(name = "Solicitante", description = "Endpoints responsáveis pelo gerenciamento de solicitantes do sistema TechSupport, permitindo consultar e cadastrar solicitantes.")
public class SolicitanteController {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @GetMapping
    @Operation(summary = "Método de consulta de listas de solicitantes!", description = "Método reponsável em efetuar a consulta de todos os solicitantes sem filtro!")
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(solicitanteRepository.findAll());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de solicitantes!", description = "Método reponsável em efetuar a criação de novos solicitantes!")
    public ResponseEntity<Solicitante> criar(@RequestBody Solicitante solicitante){

        var solicitanteBanco = solicitanteRepository.save(solicitante);
        return ResponseEntity.ok(solicitanteBanco);

    }

}
