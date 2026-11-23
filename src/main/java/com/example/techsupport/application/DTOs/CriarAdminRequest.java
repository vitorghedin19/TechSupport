package com.example.techsupport.application.DTOs;

// Corpo do cadastro de admin pelo desktop. secretKey precisa ser igual ao spring.secret.
public record CriarAdminRequest(String nome, String email, String senha, String cpf, String secretKey) {
}
