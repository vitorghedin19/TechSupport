package com.example.techsupport.application.DTOs;

// Corpo do PATCH /chamado/{id}/status.
public record AtualizarStatusChamadoRequest(EnumStatusChamado status) {
}
