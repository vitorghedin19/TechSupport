// Controller do CRUD de Equipamento. Mesmo padrao do ChamadoController.
package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.application.services.EquipamentoService;
import com.example.techsupport.domain.entities.EnumStatusEquipamento;
import com.example.techsupport.domain.entities.Equipamento;
import com.example.techsupport.domain.repository.EquipamentoRepository;
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

    // GET /equipamento -> lista todos.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de equipamentos!", description = "Método reponsável em efetuar a consulta de todos os equipamentos sem filtro!")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(equipamentoRepository.findAll());
    }

    // GET /equipamento/{id} -> busca um pelo id.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar equipamento por ID!", description = "Método responsável em efetuar busca de equipamentos existentes por ID")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id){
        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null) {
            return ResponseEntity.ok(equipamentoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /equipamento -> cadastra um novo.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de equipamentos!", description = "Método reponsável em efetuar a criação de novos equipamentos!")
    public ResponseEntity<Equipamento> criar(@RequestBody Equipamento equipamento){

        var equipamentoBanco = equipamentoRepository.save(equipamento);
        return ResponseEntity.ok(equipamentoBanco);
    }

    // PATCH /equipamento/{id}/status -> troca so o status.
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de equipamentos!", description = "Método reponsável em atualizar os status de equipamentos!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null) {
            equipamentoBanco.setStatus(statusRequest.statusEquipamento());
            equipamentoRepository.save(equipamentoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    // PUT /equipamento/{id} -> atualiza nome, tipo e status.
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar equipamentos!", description = "Método reponsável em atualizar as informações de equipamentos!")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @RequestBody Equipamento equipamento){

        try {
            Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
            if ( equipamentoBanco != null ){
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

    // DELETE /equipamento/{id}/excluir -> exclusao logica: so troca o status pra EXCLUIDO.
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
