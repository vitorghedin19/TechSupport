package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Chamado;

// O que a API devolve de um chamado (inclui a solucao quando resolvido).
public record ChamadoResponse (Long id, String titulo, String descricao, String prioridade, EnumStatusChamado status, String solucao, Long equipamentoId, String equipamentoNome) {

    public ChamadoResponse (Chamado chamadoEntidade) {
        this(
                chamadoEntidade.getId(),
                chamadoEntidade.getTitulo(),
                chamadoEntidade.getDescricao(),
                chamadoEntidade.getPrioridade(),
                chamadoEntidade.getStatus(),
                chamadoEntidade.getSolucao(), // solucao aparece na resposta depois de resolvido
                chamadoEntidade.getEquipamento() != null ? chamadoEntidade.getEquipamento().getId() : null,
                chamadoEntidade.getEquipamento() != null ? chamadoEntidade.getEquipamento().getEquipamento() : null
        );
    }
}