// Controller do CRUD de Chamado (a entidade central do sistema).
//
// FLUXO GERAL (vale pros 4 controllers de cadastro):
// -> o front chama uma URL
// -> o metodo aqui recebe a requisicao
// -> chama o ChamadoRepository
// -> o repository conversa com o Postgres
// -> o resultado volta como JSON pro front.
//
// ENDPOINTS (prefixo /chamado):
//   GET    /chamado              lista todos
//   GET    /chamado/{id}         busca um pelo id
//   POST   /chamado              cadastra um novo
//   PUT    /chamado/{id}         atualiza todos os dados
//   PATCH  /chamado/{id}/status  atualiza so o status
//   DELETE /chamado/{id}/excluir marca o chamado como EXCLUIDO (nao apaga do banco)
//
// AVISO PRA PROVA: hoje nao existe nenhum endpoint que feche o chamado exigindo uma solucao
// registrada - so existe excluir(), que so troca o status. A regra "so fecha com solucao no
// historico" ainda precisa ser criada.
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

    // GET /chamado -> lista todos os chamados do banco.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de chamados!", description = "Método reponsável em efetuar a consulta de todos os chamados sem filtro!")
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(chamadoRepository.findAll());

    }

    // GET /chamado/{id} -> busca um chamado pelo id da URL.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar chamados por ID!", description = "Método responsável em efetuar busca de chamados existentes por ID")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id){
        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            return ResponseEntity.ok(chamadoBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /chamado -> recebe um Chamado no corpo da requisicao e salva no banco (INSERT).
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de chamados!", description = "Método reponsável em efetuar a criação de novos chamados!")
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){

        var chamadoBanco = chamadoRepository.save(chamado);
        return ResponseEntity.ok(chamadoBanco);

    }

    // PATCH /chamado/{id}/status -> troca so o status do chamado (nao mexe no resto dos dados).
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

    // PUT /chamado/{id} -> troca titulo, descricao, prioridade e status pelos dados enviados.
    // FLUXO: busca o chamado no banco -> copia os campos novos por cima -> salva de volta (UPDATE).
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

    // DELETE /chamado/{id}/excluir -> nao apaga a linha do banco, so muda o status pra EXCLUIDO.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir chamados!", description = "Método reponsável em excluir chamados!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            chamadoBanco.setStatus(EnumStatusChamado.EXCLUIDO);
            chamadoRepository.save(chamadoBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
