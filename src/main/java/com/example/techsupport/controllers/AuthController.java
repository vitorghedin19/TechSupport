// ==========================================================================
// ARQUIVO: AuthController.java -> AUTENTICAÇÃO (login).
// Endpoint: POST /auth/login   Corpo: { "email": "...", "senha": "..." }
// Resposta: 200 com { "token": "..." } se email+senha existirem | 401 se não.
// FLUXO: front envia credenciais -> controller confere no banco -> TokenService gera o JWT -> front guarda o token.
// ==========================================================================
package com.example.techsupport.controllers;

import com.example.techsupport.DTOs.LoginRequest;
import com.example.techsupport.DTOs.LoginResponse;
import com.example.techsupport.repository.UsuarioRepository;
import com.example.techsupport.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

// @RestController: classe que recebe requisições HTTP e devolve o retorno já como JSON (não como página HTML).
// @RequestMapping("/auth"): prefixo de URL de todos os métodos desta classe.
// @Tag: só documentação - agrupa e nomeia este controller no Swagger.
@RestController
@RequestMapping("/auth")
@Tag(description = "Controller de autenticação", name = "Autenticação")
public class AuthController {

    // Injeção de dependência: o Spring entrega os objetos prontos (sem usar "new").
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // @PostMapping("/login") => POST /auth/login. @Operation é só texto para o Swagger.
    @PostMapping("/login")
    @Operation(description = "Método de login", summary = "Autenticação de usuários")
    // @RequestBody: o Spring lê o JSON do corpo da requisição e o converte para um LoginRequest.
    // ResponseEntity<?>: permite controlar o status HTTP e o corpo da resposta; "?" = corpo de tipo variável.
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        // Consulta o banco: existe algum usuário com este email E esta senha? (método derivado do UsuarioRepository)
        // Obs.: não verifica o status do usuário, então mesmo BLOQUEADO/EXCLUIDO conseguiria logar.
        if (usuarioRepository.existsUsuarioByEmailAndSenha(loginRequest.email(), loginRequest.senha())){

            // Credenciais corretas: gera o JWT tendo o e-mail como "subject".
            var token = tokenService.gerarToken(loginRequest.email());

            // 200 OK com o JSON { "token": "..." }.
            return ResponseEntity.ok(new LoginResponse(token));
        }
        // Credenciais erradas: 401 (Unauthorized) sem corpo. HttpURLConnection.HTTP_UNAUTHORIZED é só a constante 401.
        return ResponseEntity.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
    }

}
