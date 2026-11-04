package com.example.techsupport.application.DTOs;

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