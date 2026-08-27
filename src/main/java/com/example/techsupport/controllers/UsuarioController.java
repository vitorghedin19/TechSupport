package com.example.techsupport.controllers;

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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Método de consulta de listas de usuários!", description = "Método reponsável em efetuar a consulta de todos os usuários sem filtro!")
    public ResponseEntity<?> listarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Método de criação de usuários!", description = "Método reponsável em efetuar a criação de novos usuários!")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){

        var usuarioBanco = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioBanco);
    }

}