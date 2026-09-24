// ==========================================================================
// ARQUIVO: UsuarioController.java -> API REST dos USUÁRIOS / TÉCNICOS (CRUD).
// Mesma estrutura do ChamadoController: Front -> controller -> UsuarioRepository -> PostgreSQL -> JSON.
//
// ENDPOINTS (prefixo /usuarios):
//   GET /usuarios | GET /usuarios/{id} | POST /usuarios | PUT /usuarios/{id}
//   PATCH /usuarios/{id}/status | DELETE /usuarios/{id}/excluir (exclusão LÓGICA: status = EXCLUIDO)
// Obs.: o login NÃO fica aqui; fica no AuthController (POST /auth/login).
// ==========================================================================
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

// @RestController: devolve JSON. @RequestMapping("/usuarios"): prefixo de URL (note o plural, diferente dos outros). @Tag: nome no Swagger.
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints responsáveis pelo gerenciamento de usuários do sistema TechSupport, permitindo consultar e cadastrar usuários.")
public class UsuarioController {

    // Injeção de dependência: o Spring cria o UsuarioRepository e entrega aqui, sem precisar de "new".
    @Autowired //injeção de dependencia
    private UsuarioRepository usuarioRepository;

    // GET /usuarios -> lista todos (SELECT * FROM usuario), convertidos para JSON.
    // Atenção: devolve também a senha de cada usuário, pois a entidade inteira é serializada.
    @GetMapping
    @Operation(summary = "Método de consulta de listas de usuários!", description = "Método reponsável em efetuar a consulta de todos os usuários sem filtro!")
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    // GET /usuarios/{id} -> busca um por id; 200 com o objeto ou 404 se não existir.
    @GetMapping("/{id}")
    @Operation(summary = "Método de buscar usuário por ID!", description = "Método responsável em efetuar busca de usuários existentes por ID")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        // orElse(null): se o Optional estiver vazio (não achou), devolve null.
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco != null) {
            return ResponseEntity.ok(usuarioBanco);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /usuarios -> cria. @RequestBody converte o JSON em Usuario; save() faz INSERT.
    // (O @ResponseStatus(CREATED) é sobrescrito pelo ResponseEntity.ok(): a resposta real sai 200.)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de usuários!", description = "Método reponsável em efetuar a criação de novos usuários!")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){

        var usuarioBanco = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioBanco);
    }

    // PATCH /usuarios/{id}/status -> altera só o status (ex.: BLOQUEADO), usando o campo statusUsuario do DTO.
    @PatchMapping("/{id}/status") //serve para atualizar um dado apenas
    @Operation(summary = "Método de atualizar o status de usuários!", description = "Método reponsável em atualizar os status de usuários!")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusRequest statusRequest){

        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco != null) {
            usuarioBanco.setStatus(statusRequest.statusUsuario());
            // save() com id existente = UPDATE.
            usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    // PUT /usuarios/{id} -> atualiza status, CPF, e-mail, nome e senha com os dados enviados no JSON.
    @PutMapping("/{id}")
    @Operation(summary = "Método de atualizar usuários!", description = "Método reponsável em atualizar os dados de usuários!")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){

        try {
            Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
            if ( usuarioBanco != null ){
                // Copia os valores novos para o objeto que veio do banco (o id permanece).
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

    // DELETE /usuarios/{id}/excluir -> EXCLUSÃO LÓGICA (soft delete): não apaga a linha, só marca EXCLUIDO.
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
