package com.example.techsupport.controllers;

import com.example.techsupport.entities.Solicitante;
import com.example.techsupport.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @GetMapping
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(solicitanteRepository.findAll());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Solicitante> criar(@RequestBody Solicitante solicitante){

        var solicitanteBanco = solicitanteRepository.save(solicitante);
        return ResponseEntity.ok(solicitanteBanco);

    }

}
