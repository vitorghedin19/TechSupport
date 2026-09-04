package com.example.techsupport.controllers;

import com.example.techsupport.DTOs.AtualizarStatusRequest;
import com.example.techsupport.entities.EnumStatusSolicitante;
import com.example.techsupport.entities.EnumStatusUsuario;
import com.example.techsupport.entities.Solicitante;
import com.example.techsupport.entities.Usuario;
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
    public ResponseEntity<?> listarTodos(){return ResponseEntity.ok(solicitanteRepository.findAll());}

    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar solicitante por ID!", description = "Método responsável em efetuar busca de solicitantes existentes por ID")
    public ResponseEntity<Solicitante> buscarPorId(@PathVariable Long id){
        Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
        if (solicitanteBanco != null) {
            return ResponseEntity.ok(solicitanteBanco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de solicitantes!", description = "Método reponsável em efetuar a criação de novos solicitantes!")
    public ResponseEntity<Solicitante> criar(@RequestBody Solicitante solicitante){

        var solicitanteBanco = solicitanteRepository.save(solicitante);
        return ResponseEntity.ok(solicitanteBanco);

    }

    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de solicitantes!", description = "Método reponsável em atualizar os status de solicitantes!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
        if (solicitanteBanco != null) {
            solicitanteBanco.setStatus(statusRequest.statusSolicitante());
            solicitanteRepository.save(solicitanteBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar solicitantes!", description = "Método reponsável em atualizar os dados de solicitantes!")
    public ResponseEntity<Solicitante> atualizar(@PathVariable Long id, @RequestBody Solicitante solicitante){

        try {
            Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
            if ( solicitanteBanco != null ){
                solicitanteBanco.setNome(solicitante.getNome());
                solicitanteBanco.setSetor(solicitante.getSetor());
                solicitanteBanco.setEmail(solicitante.getEmail());
                solicitanteBanco.setStatus(solicitante.getStatus());
                solicitanteRepository.save(solicitanteBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir solicitantes!", description = "Método reponsável em excluir cadastros de solicitantes!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Solicitante solicitanteBanco = solicitanteRepository.findById(id).orElse(null);
        if (solicitanteBanco != null) {
            solicitanteBanco.setStatus(EnumStatusSolicitante.EXCLUIDO);
            solicitanteRepository.save(solicitanteBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
