package com.example.techsupport.controllers;

import com.example.techsupport.DTOs.AtualizarStatusRequest;
import com.example.techsupport.entities.*;
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
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(chamadoRepository.findAll());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar chamados por ID!", description = "Método responsável em efetuar busca de chamados existentes por ID")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id){
        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            return ResponseEntity.ok(chamadoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de chamados!", description = "Método reponsável em efetuar a criação de novos chamados!")
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){

        var chamadoBanco = chamadoRepository.save(chamado);
        return ResponseEntity.ok(chamadoBanco);

    }

    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de chamados!", description = "Método reponsável em atualizar os status de chamados!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            chamadoBanco.setStatus(statusRequest.statusChamado());
            chamadoRepository.save(chamadoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar chamados!", description = "Método reponsável em atualizar os dados de chamados!")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){

        try {
            Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
            if ( chamadoBanco != null ){
                chamadoBanco.setStatus(chamado.getStatus());
                chamadoBanco.setTitulo(chamado.getTitulo());
                chamadoBanco.setDescricao(chamado.getDescricao());
                chamadoBanco.setPrioridade(chamado.getPrioridade());
                chamadoRepository.save(chamadoBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}/fechar")
    @Operation(summary = "Método de fechar chamados!", description = "Método reponsável em fechar chamados!")
    public ResponseEntity<Void> fechar(@PathVariable Long id) {
        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            chamadoBanco.setStatus(EnumStatusChamado.FECHADO);
            chamadoRepository.save(chamadoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}