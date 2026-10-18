// Controller do CRUD de Equipamento
package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.application.services.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
@Tag(name = "Equipamentos", description = "Endpoints responsáveis pelo gerenciamento de equipamentos do sistema TechSupport, permitindo consultar e cadastrar equipamentos.")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    // GET /equipamento -> lista todos.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de equipamentos!", description = "Método reponsável em efetuar a consulta de todos os equipamentos sem filtro!")
    public ResponseEntity<List<EquipamentoResponse>> listarTodos() {
        return ResponseEntity.ok(equipamentoService.listarTodosEquipamentosTable());
    }

    // GET /equipamento/{id} -> busca um pelo id.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar equipamento por ID!", description = "Método responsável em efetuar busca de equipamentos existentes por ID")
    public ResponseEntity<EquipamentoResponse> buscarPorId(@PathVariable Long id){
        EquipamentoResponse equipamentoBanco = equipamentoService.buscarPorId(id);
        if (equipamentoBanco != null) {
            return ResponseEntity.ok(equipamentoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /equipamento -> cadastra um novo.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de equipamentos!", description = "Método reponsável em efetuar a criação de novos equipamentos!")
    public ResponseEntity<?> criar(@RequestBody EquipamentoRequest equipamento){

        try {
            EquipamentoResponse equipamentoBanco = equipamentoService.salvarEquipamento(equipamento, 0L);
            return ResponseEntity.ok(equipamentoBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /equipamento/{id}/status -> troca so o status.
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de equipamentos!", description = "Método reponsável em atualizar os status de equipamentos!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        var atualizarEquipamento = equipamentoService.atualizarStatus(statusRequest.statusEquipamento(), id);

        if (atualizarEquipamento) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /equipamento/{id} -> atualiza nome e tipo.
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar equipamentos!", description = "Método reponsável em atualizar as informações de equipamentos!")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody EquipamentoRequest equipamento){

        try {
            EquipamentoResponse equipamentoBanco = equipamentoService.salvarEquipamento(equipamento, id);
            return ResponseEntity.ok(equipamentoBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /equipamento/{id}/excluir -> exclusao logica: so troca o status pra EXCLUIDO.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir equipamentos!", description = "Método reponsável em excluir equipamentos!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        var atualizarEquipamento = equipamentoService.atualizarStatus(EnumStatusEquipamento.EXCLUIDO, id);

        if (atualizarEquipamento) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}