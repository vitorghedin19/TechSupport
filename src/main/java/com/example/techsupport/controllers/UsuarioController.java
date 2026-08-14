package com.example.techsupport.controllers;

import com.example.techsupport.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<?> listarTodos(){

        List<Usuario> usuarios = List.of(new Usuario(1L, "Vitor", "13325053920", "123456", "vitor@gmail.com"));

        return ResponseEntity.ok(usuarios);

    }

}