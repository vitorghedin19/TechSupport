// Controller do CRUD de Chamado (a entidade central do sistema)
//
// FLUXO GERAL (vale pros 4 controllers de cadastro):
// -> o front chama uma URL
// -> o metodo aqui recebe a requisicao
// -> chama o ChamadoService
// -> o service fala com o ChamadoRepository
// -> o repository conversa com o Postgres
// -> o resultado volta como JSON pro front.
//
// ENDPOINTS (prefixo /chamado):
//   GET    /chamado              lista todos
//   GET    /chamado/{id}         busca um pelo id
//   POST   /chamado              cadastra um novo
//   PUT    /chamado/{id}         atualiza titulo, descricao e prioridade
//   PATCH  /chamado/{id}/status  atualiza so o status
//   DELETE /chamado/{id}/excluir marca o chamado como EXCLUIDO (nao apaga do banco)
//
// AVISO PRA PROVA: continua sem existir endpoint que feche o chamado exigindo uma solucao
// registrada - so existe excluir(), que so troca o status. A regra "so fecha com solucao no
// historico" ainda precisa ser criada.
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

    // GET /chamado -> lista todos os chamados do banco.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de chamados!", description = "Método reponsável em efetuar a consulta de todos os chamados sem filtro!")
    public ResponseEntity<List<ChamadoResponse>> listarTodos(){
        return ResponseEntity.ok(chamadoService.listarTodosChamadosTable());
    }

    // GET /chamado/{id} -> busca um chamado pelo id da URL.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar chamados por ID!", description = "Método responsável em efetuar busca de chamados existentes por ID")
    public ResponseEntity<ChamadoResponse> buscarPorId(@PathVariable Long id){
        ChamadoResponse chamadoBanco = chamadoService.buscarPorId(id);
        if (chamadoBanco != null) {
            return ResponseEntity.ok(chamadoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /chamado -> cadastra um novo.
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

    // PATCH /chamado/{id}/status -> troca so o status do chamado (nao mexe no resto dos dados).
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de chamados!", description = "Método reponsável em atualizar os status de chamados!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        var atualizarChamado = chamadoService.atualizarStatus(statusRequest.statusChamado(), id);

        if (atualizarChamado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /chamado/{id} -> troca titulo, descricao e prioridade pelos dados enviados.
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

    // DELETE /chamado/{id}/excluir -> nao apaga a linha do banco, so muda o status pra EXCLUIDO.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir chamados!", description = "Método reponsável em excluir chamados!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        var atualizarChamado = chamadoService.atualizarStatus(EnumStatusChamado.EXCLUIDO, id);

        if (atualizarChamado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}