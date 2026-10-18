package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.application.services.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
@Tag(name = "Chamado", description = "Endpoints responsáveis pelo gerenciamento de chamados do sistema TechSupport, permitindo consultar e cadastrar chamados.")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    @Operation(summary = "Método de consulta de listas de chamados!", description = "Método reponsável em efetuar a consulta de todos os chamados sem filtro!")
    public ResponseEntity<List<ChamadoResponse>> listarTodos(){
        return ResponseEntity.ok(chamadoService.listarTodosChamadosTable());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar chamados por ID!", description = "Método responsável em efetuar busca de chamados existentes por ID")
    public ResponseEntity<ChamadoResponse> buscarPorId(@PathVariable Long id){
        ChamadoResponse chamadoBanco = chamadoService.buscarPorId(id);
        if (chamadoBanco != null) {
            return ResponseEntity.ok(chamadoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de chamados!", description = "Método reponsável em efetuar a criação de novos chamados!")
    public ResponseEntity<?> criar(@RequestBody ChamadoRequest chamado){

        try {
            ChamadoResponse chamadoBanco = chamadoService.salvarChamado(chamado, 0L);
            return ResponseEntity.ok(chamadoBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Método de atualizar o status de chamados!", description = "Método reponsável em atualizar os status de chamados!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusChamadoRequest statusRequest){

        var atualizarChamado = chamadoService.atualizarStatus(statusRequest.statusChamado(), id);

        if (atualizarChamado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar chamados!", description = "Método reponsável em atualizar os dados de chamados!")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ChamadoRequest chamado){

        try {
            ChamadoResponse chamadoBanco = chamadoService.salvarChamado(chamado, id);
            return ResponseEntity.ok(chamadoBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir chamados!", description = "Método reponsável em excluir chamados!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        var atualizarChamado = chamadoService.atualizarStatus(EnumStatusChamado.EXCLUIDO, id);

        if (atualizarChamado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // NOVO: unico caminho pra marcar um chamado como RESOLVIDO. Exige solucao no corpo
    // da requisicao - a validacao de verdade acontece la na entidade, aqui so pega
    // a excecao e devolve 400 com a mensagem pro front.
    @PatchMapping("/{id}/resolver")
    @Operation(summary = "Método de resolver chamados!", description = "Método responsável em marcar um chamado como resolvido, exigindo o registro da solução adotada.")
    public ResponseEntity<?> resolver(@PathVariable Long id, @RequestBody ResolverChamadoRequest request){

        try {
            ChamadoResponse chamadoResolvido = chamadoService.resolver(id, request.solucao());
            if (chamadoResolvido != null) {
                return ResponseEntity.ok(chamadoResolvido);
            }
            return ResponseEntity.notFound().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}