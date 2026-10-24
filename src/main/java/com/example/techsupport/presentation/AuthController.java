// Pasta controllers: a "porta de entrada" da API. Cada metodo aqui e uma rota HTTP.
// AuthController cuida so do login (POST /auth/login).
//
// FLUXO DO LOGIN: front manda email+senha -> este controller confere no banco (via UsuarioRepository)
// -> se bater, pede pro TokenService gerar um token -> devolve o token pro front.
package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.LoginRequest;
import com.example.techsupport.application.services.SolicitanteService;
import com.example.techsupport.application.services.UsuarioService;
import com.example.techsupport.application.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@RestController
@RequestMapping("/auth")
@Tag(description = "Controller de autenticação", name = "Autenticação")
public class AuthController {

    // O Spring entrega esses objetos prontos (injecao de dependencia), sem precisar de "new".
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitanteService solicitanteService;

    @PostMapping("/login")
    @Operation(description = "Método de login", summary = "Autenticação de usuários")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        var resultado = usuarioService.validarUsuarioAutenticadoRetornaToken(loginRequest);

        if (resultado == null) { // NOVO: se não achou como Usuario, tenta como Solicitante
            resultado = solicitanteService.validarSolicitanteAutenticadoRetornaToken(loginRequest);
        }

        if (resultado != null){
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().body("Usuário ou senha Inválido!");
    }
}
