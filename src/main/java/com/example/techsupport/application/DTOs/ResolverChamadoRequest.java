package com.example.techsupport.application.DTOs;

// DTO novo so pra essa acao: carrega a solucao que o Tecnico digita no front
// quando aperta "Resolver chamado".
public record ResolverChamadoRequest(String solucao) {
}