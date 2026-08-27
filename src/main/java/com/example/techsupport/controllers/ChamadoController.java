package com.example.techsupport.controllers;

import com.example.techsupport.entities.Chamado;
import com.example.techsupport.entities.Solicitante;
import com.example.techsupport.repository.ChamadoRepository;
import com.example.techsupport.repository.SolicitanteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamado")
@Tag(name = "Chamado", description = "Endpoints responsáveis pelo gerenciamento de chamados do sistema TechSupport, permitindo consultar e cadastrar chamados.")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping
    @Operation(summary = "Método de consulta de listas de chamados!", description = "Método reponsável em efetuar a consulta de todos os chamados sem filtro!")
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(chamadoRepository.findAll());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de chamados!", description = "Método reponsável em efetuar a criação de novos chamados!")
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){

        var chamadoBanco = chamadoRepository.save(chamado);
        return ResponseEntity.ok(chamadoBanco);

    }

}