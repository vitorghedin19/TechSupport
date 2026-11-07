package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Chamado;

public record ChamadoResponse (Long id, String titulo, String descricao, String prioridade, EnumStatusChamado status, String solucao, Long equipamentoId, String equipamentoNome) {

    public ChamadoResponse (Chamado chamadoEntidade) {
        this(
                chamadoEntidade.getId(),
                chamadoEntidade.getTitulo(),
                chamadoEntidade.getDescricao(),
                chamadoEntidade.getPrioridade(),
                chamadoEntidade.getStatus(),
                chamadoEntidade.getSolucao(), // NOVO: pra solucao aparecer na resposta depois de resolvido
                chamadoEntidade.getEquipamento() != null ? chamadoEntidade.getEquipamento().getId() : null,
                chamadoEntidade.getEquipamento() != null ? chamadoEntidade.getEquipamento().getEquipamento() : null
        );
    }
}