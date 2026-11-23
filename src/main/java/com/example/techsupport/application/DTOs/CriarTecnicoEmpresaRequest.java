package com.example.techsupport.application.DTOs;

// Corpo do cadastro de tecnico + empresa pelo desktop. secretKey precisa ser igual ao spring.secret.
public record CriarTecnicoEmpresaRequest(
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String nome,
        String email,
        String senha,
        String cpf,
        String secretKey) {
}