package com.example.techsupport.controllers;

import com.example.techsupport.entities.Chamado;
import com.example.techsupport.entities.Solicitante;
import com.example.techsupport.repository.ChamadoRepository;
import com.example.techsupport.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(chamadoRepository.findAll());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){

        var chamadoBanco = chamadoRepository.save(chamado);
        return ResponseEntity.ok(chamadoBanco);

    }

}