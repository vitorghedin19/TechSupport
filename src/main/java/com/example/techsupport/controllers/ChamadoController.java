// ==========================================================================
// ARQUIVO: ChamadoController.java -> API REST dos CHAMADOS (entidade central do sistema).
// FLUXO GERAL: Front (React) -> requisição HTTP -> este controller -> ChamadoRepository -> PostgreSQL -> JSON de volta.
//
// ENDPOINTS (prefixo /chamado):
//   GET    /chamado              lista todos          |  GET   /chamado/{id}          busca um
//   POST   /chamado              cria                 |  PUT   /chamado/{id}          atualiza os dados
//   PATCH  /chamado/{id}/status  muda só o status     |  DELETE /chamado/{id}/fechar  fecha o chamado
//
// !!! PONTOS PARA A APRESENTAÇÃO !!!
//  - A REGRA CENTRAL ("só fecha se registrar a solução no histórico") NÃO está implementada aqui: o método fechar()
//    muda direto para FECHADO, sem exigir solução. Também não há cálculo de SLA/ATRASADO neste código.
//  - "fechar" usa @DeleteMapping mas NÃO apaga nada: só troca o status (exclusão lógica).
// ==========================================================================
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

// @RestController: devolve JSON. @RequestMapping("/chamado"): prefixo de URL. @Tag: só nome/descrição no Swagger.
@RestController
@RequestMapping("/chamado")
@Tag(name = "Chamado", description = "Endpoints responsáveis pelo gerenciamento de chamados do sistema TechSupport, permitindo consultar e cadastrar chamados.")
public class ChamadoController {

    // Injeção de dependência: o Spring entrega o repository pronto (sem "new").
    @Autowired
    private ChamadoRepository chamadoRepository;

    // GET /chamado -> lista TODOS os chamados. findAll() = SELECT * FROM chamado. O Jackson converte a lista para JSON.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de chamados!", description = "Método reponsável em efetuar a consulta de todos os chamados sem filtro!")
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(chamadoRepository.findAll());

    }

    // GET /chamado/{id} -> busca um chamado. @PathVariable pega o {id} da URL (ex.: /chamado/3 -> id = 3).
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar chamados por ID!", description = "Método responsável em efetuar busca de chamados existentes por ID")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id){
        // findById devolve um Optional (pode ou não achar). orElse(null) transforma "não achou" em null.
        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            // Achou: 200 OK com o chamado no corpo.
            return ResponseEntity.ok(chamadoBanco);
        }
        // Não achou: 404 Not Found.
        return ResponseEntity.notFound().build();
    }

    // POST /chamado -> cria um chamado novo. @RequestBody converte o JSON recebido em um objeto Chamado.
    // Obs.: @ResponseStatus(CREATED) pede 201, mas o método devolve ResponseEntity.ok(), e o ResponseEntity
    // tem prioridade: na prática a resposta sai 200.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de chamados!", description = "Método reponsável em efetuar a criação de novos chamados!")
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){

        // save() faz INSERT (id vazio). O objeto devolvido já vem com o id gerado pelo banco e com o status padrão ABERTO.
        var chamadoBanco = chamadoRepository.save(chamado);
        return ResponseEntity.ok(chamadoBanco);

    }

    // PATCH /chamado/{id}/status -> altera SOMENTE o status. PATCH = atualização parcial (PUT = atualiza o registro todo).
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de chamados!", description = "Método reponsável em atualizar os status de chamados!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null) {
            // Do DTO usa só o campo statusChamado (os outros 3 campos do DTO são ignorados aqui).
            chamadoBanco.setStatus(statusRequest.statusChamado());
            // save() com id existente faz UPDATE.
            chamadoRepository.save(chamadoBanco);
            // 200 OK sem corpo (ResponseEntity<Void>).
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    // PUT /chamado/{id} -> atualiza os dados do chamado (título, descrição, prioridade e status).
    // Obs.: o tipo de retorno está como ResponseEntity<Usuario> (parece engano de cópia), mas funciona porque
    // o corpo é sempre vazio (.build()).
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar chamados!", description = "Método reponsável em atualizar os dados de chamados!")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){

        try {
            Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
            if ( chamadoBanco != null ){
                // Copia campo a campo os valores novos (vindos do JSON) para o objeto que veio do banco.
                // O id não é copiado: o registro continua sendo o mesmo.
                chamadoBanco.setStatus(chamado.getStatus());
                chamadoBanco.setTitulo(chamado.getTitulo());
                chamadoBanco.setDescricao(chamado.getDescricao());
                chamadoBanco.setPrioridade(chamado.getPrioridade());
                // Como chamadoBanco já tem id, o save() faz UPDATE.
                chamadoRepository.save(chamadoBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (RuntimeException e){
            // Captura qualquer erro de execução e o relança embrulhado (na prática só repassa o erro).
            throw new RuntimeException(e);
        }
    }

    // DELETE /chamado/{id}/fechar -> "fecha" o chamado. NÃO apaga a linha do banco: só define o status FECHADO.
    // É onde a regra de negócio "exigir solução no histórico antes de fechar" deveria ser verificada.
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
