// Controller do CRUD de Usuario (tecnico). O login NAO fica aqui, fica no AuthController.
//
// FLUXO DE UMA EDICAO DE USUARIO (exemplo pratico pra prova):
//  1) front manda PUT /usuarios/{id} com o JSON novo (nome, email, cpf, senha, status)
//  2) este metodo (atualizar) recebe a requisicao
//  3) busca o usuario atual no banco pelo id (usuarioRepository.findById)
//  4) troca campo por campo pelos valores que vieram no JSON
//  5) chama usuarioRepository.save(...) de novo - como o objeto ja tem id, o Spring Data faz UPDATE
//  6) devolve 200 OK pro front (sem corpo) ou 404 se o id nao existir
package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.application.services.UsuarioService;
import com.example.techsupport.application.DTOs.EnumStatusUsuario;
import com.example.techsupport.domain.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints responsáveis pelo gerenciamento de usuários do sistema TechSupport, permitindo consultar e cadastrar usuários.")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /usuarios -> lista todos (inclusive a senha de cada um, ja que a entidade inteira e devolvida).
    @GetMapping
    @Operation(summary = "Método de consulta de listas de usuários!", description = "Método reponsável em efetuar a consulta de todos os usuários sem filtro!")
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {

        return ResponseEntity.ok(usuarioService.listarTodosUsuarioTable());
    }

    // GET /usuarios/{id} -> busca um pelo id.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar usuário por ID!", description = "Método responsável em efetuar busca de usuários existentes por ID")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        UsuarioResponse usuarioBanco = usuarioService.buscarPorId(id);
        if (usuarioBanco != null) {
            return ResponseEntity.ok(usuarioBanco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/admin")
    @Operation(summary = "Método de criação de usuários ADMS!", description = "Método reponsável em efetuar a criação de novos usuários ADMS!")
    public ResponseEntity<CriarAdminResponse> criarAdmin(@RequestBody CriarAdminRequest criarAdminRequest) {

        try {

            CriarAdminResponse respostaSalvar = usuarioService.criarAdmin(criarAdminRequest);

            return ResponseEntity.ok(respostaSalvar);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    // POST /usuarios -> cadastra um novo.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de usuários!", description = "Método reponsável em efetuar a criação de novos usuários!")
    public ResponseEntity<?> criar(@RequestBody UsuarioRequest usuario) {

        try {
            UsuarioResponse usuarioBanco = usuarioService.salvarUsuario(usuario, 0L);
            return ResponseEntity.ok(usuarioBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /usuarios/{id}/status -> troca so o status (ex.: bloquear um usuario).
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de usuários!", description = "Método reponsável em atualizar os status de usuários!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest) {

        var atualizarUsuario = usuarioService.atualizarStatus(statusRequest.statusUsuario(), id);

        if (atualizarUsuario) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /usuarios/{id} -> atualiza status, cpf, email, nome e senha (veja o fluxo no topo do arquivo).
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar usuários!", description = "Método reponsável em atualizar os dados de usuários!")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest usuario) {

        try {
            UsuarioResponse usuarioBanco = usuarioService.salvarUsuario(usuario, id);
            return ResponseEntity.ok(usuarioBanco);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /usuarios/{id}/excluir -> exclusao logica: so troca o status pra EXCLUIDO.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir usuários!", description = "Método reponsável em excluir cadastros de usuários!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        var atualizarUsuario = usuarioService.atualizarStatus(EnumStatusUsuario.EXCLUIDO, id);

        if (atualizarUsuario) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}