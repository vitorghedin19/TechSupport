// Controller do CRUD de Usuario (tecnico). O login NAO fica aqui, fica no AuthController.
//
// FLUXO DE UMA EDICAO DE USUARIO (exemplo pratico pra prova):
//  1) front manda PUT /usuarios/{id} com o JSON novo (nome, email, cpf, senha, status)
//  2) este metodo (atualizar) recebe a requisicao
//  3) busca o usuario atual no banco pelo id (usuarioRepository.findById)
//  4) troca campo por campo pelos valores que vieram no JSON
//  5) chama usuarioRepository.save(...) de novo - como o objeto ja tem id, o Spring Data faz UPDATE
//  6) devolve 200 OK pro front (sem corpo) ou 404 se o id nao existir
package com.example.techsupport.controllers;

import com.example.techsupport.DTOs.AtualizarStatusRequest;
import com.example.techsupport.entities.EnumStatusUsuario;
import com.example.techsupport.entities.Usuario;
import com.example.techsupport.repository.UsuarioRepository;
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

    @Autowired //injeção de dependencia
    private UsuarioRepository usuarioRepository;

    // GET /usuarios -> lista todos (inclusive a senha de cada um, ja que a entidade inteira e devolvida).
    @GetMapping
    @Operation(summary = "Método de consulta de listas de usuários!", description = "Método reponsável em efetuar a consulta de todos os usuários sem filtro!")
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    // GET /usuarios/{id} -> busca um pelo id.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar usuário por ID!", description = "Método responsável em efetuar busca de usuários existentes por ID")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco != null) {
            return ResponseEntity.ok(usuarioBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /usuarios -> cadastra um novo.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de usuários!", description = "Método reponsável em efetuar a criação de novos usuários!")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){

        var usuarioBanco = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioBanco);
    }

    // PATCH /usuarios/{id}/status -> troca so o status (ex.: bloquear um usuario).
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de usuários!", description = "Método reponsável em atualizar os status de usuários!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco != null) {
            usuarioBanco.setStatus(statusRequest.statusUsuario());
            usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    // PUT /usuarios/{id} -> atualiza status, cpf, email, nome e senha (veja o fluxo no topo do arquivo).
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar usuários!", description = "Método reponsável em atualizar os dados de usuários!")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){

        try {
            Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
            if ( usuarioBanco != null ){
                usuarioBanco.setStatus(usuario.getStatus());
                usuarioBanco.setCpf(usuario.getCpf());
                usuarioBanco.setEmail(usuario.getEmail());
                usuarioBanco.setNome(usuario.getNome());
                usuarioBanco.setSenha(usuario.getSenha());
                usuarioRepository.save(usuarioBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    // DELETE /usuarios/{id}/excluir -> exclusao logica: so troca o status pra EXCLUIDO.
    @DeleteMapping("/{id}/excluir")
    @Operation(summary = "Método de excluir usuários!", description = "Método reponsável em excluir cadastros de usuários!")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco != null) {
            usuarioBanco.setStatus(EnumStatusUsuario.EXCLUIDO);
            usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
