// ==========================================================================
// ARQUIVO: EquipamentoController.java -> API REST dos EQUIPAMENTOS (CRUD).
// Mesma estrutura do ChamadoController: Front -> controller -> EquipamentoRepository -> PostgreSQL -> JSON.
//
// ENDPOINTS (prefixo /equipamento):
//   GET /equipamento | GET /equipamento/{id} | POST /equipamento | PUT /equipamento/{id}
//   PATCH /equipamento/{id}/status | DELETE /equipamento/{id}/excluir (exclusão LÓGICA: status = EXCLUIDO)
// ==========================================================================
package com.example.techsupport.controllers;

import com.example.techsupport.DTOs.AtualizarStatusRequest;
import com.example.techsupport.entities.EnumStatusEquipamento;
import com.example.techsupport.entities.EnumStatusUsuario;
import com.example.techsupport.entities.Equipamento;
import com.example.techsupport.entities.Usuario;
import com.example.techsupport.repository.EquipamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController: devolve JSON. @RequestMapping("/equipamento"): prefixo de URL. @Tag: nome no Swagger.
@RestController
@RequestMapping("/equipamento")
@Tag(name = "Equipamentos", description = "Endpoints responsáveis pelo gerenciamento de equipamentos do sistema TechSupport, permitindo consultar e cadastrar equipamentos.")
public class EquipamentoController {

    // Injeção de dependência do repository (o Spring entrega o objeto pronto).
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    // GET /equipamento -> lista todos (SELECT * FROM equipamento), convertidos para JSON.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de equipamentos!", description = "Método reponsável em efetuar a consulta de todos os equipamentos sem filtro!")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(equipamentoRepository.findAll());
    }

    // GET /equipamento/{id} -> busca um por id; 200 com o objeto ou 404 se não existir.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar equipamento por ID!", description = "Método responsável em efetuar busca de equipamentos existentes por ID")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id){
        // orElse(null): se o Optional estiver vazio (não achou), devolve null.
        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null) {
            return ResponseEntity.ok(equipamentoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /equipamento -> cria. @RequestBody converte o JSON em Equipamento; save() faz INSERT.
    // (O @ResponseStatus(CREATED) é sobrescrito pelo ResponseEntity.ok(): a resposta real sai 200.)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de equipamentos!", description = "Método reponsável em efetuar a criação de novos equipamentos!")
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento){

        var equipamentoBanco = equipamentoRepository.save(equipamento);
        return ResponseEntity.ok(equipamentoBanco);
    }

    // PATCH /equipamento/{id}/status -> altera só o status, usando o campo statusEquipamento do DTO.
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de equipamentos!", description = "Método reponsável em atualizar os status de equipamentos!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null) {
            equipamentoBanco.setStatus(statusRequest.statusEquipamento());
            // save() com id existente = UPDATE.
            equipamentoRepository.save(equipamentoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    // PUT /equipamento/{id} -> atualiza status, tipo e nome do equipamento com os dados enviados no JSON.
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar equipamentos!", description = "Método reponsável em atualizar as informações de equipamentos!")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @RequestBody Equipamento equipamento){

        try {
            Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
            if ( equipamentoBanco != null ){
                // Copia os valores novos para o objeto que veio do banco (o id permanece).
                equipamentoBanco.setStatus(equipamento.getStatus());
                equipamentoBanco.setTipo(equipamento.getTipo());
                equipamentoBanco.setEquipamento(equipamento.getEquipamento());
                equipamentoRepository.save(equipamentoBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    // DELETE /equipamento/{id}/excluir -> EXCLUSÃO LÓGICA (soft delete): não apaga a linha, só marca EXCLUIDO.
    // Vantagem: o histórico (chamados antigos que usaram o equipamento) não se perde.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir equipamentos!", description = "Método reponsável em excluir equipamentos!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null) {
            equipamentoBanco.setStatus(EnumStatusEquipamento.EXCLUIDO);
            equipamentoRepository.save(equipamentoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
