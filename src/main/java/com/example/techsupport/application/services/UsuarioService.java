package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.domain.entities.Usuario;
import com.example.techsupport.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Value("${spring.secret}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public LoginResponse validarUsuarioAutenticadoRetornaToken (LoginRequest request) {

        if (usuarioRepository.existsUsuarioByEmailAndSenha(request.email(), request.senha())){
            var token = tokenService.gerarToken(request.email());
            return new LoginResponse(token);
        }
        return null;
    }


    public List<UsuarioResponse> listarTodosUsuarioTable(){

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::new)
                .toList();

    }

    public CriarAdminResponse criarAdmin(CriarAdminRequest criarAdminRequest) {

        if (!criarAdminRequest.secretKey().equals(secret)){
            return new CriarAdminResponse(0L, "Usuario Salvo com sucesso!");
        }

        Usuario usuarioAdminSalvar = new Usuario(criarAdminRequest);

        usuarioRepository.save(usuarioAdminSalvar);

        return new CriarAdminResponse(usuarioAdminSalvar.getId(), "Usuário salvo com sucesso!");

    }
}