package com.example.techsupport.application.DTOs;

// Corpo de criar/editar chamado. Empresa e solicitante nao vem aqui: saem do token.
public record ChamadoRequest(String titulo, String descricao, String prioridade, Long equipamentoId) {
}