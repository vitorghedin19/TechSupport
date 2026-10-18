package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Chamado;

public record ChamadoResponse (Long id, String titulo, String descricao, String prioridade, EnumStatusChamado statusChamado, String solucao) {

    public ChamadoResponse (Chamado chamadoEntidade) {
        this(
                chamadoEntidade.getId(),
                chamadoEntidade.getTitulo(),
                chamadoEntidade.getDescricao(),
                chamadoEntidade.getPrioridade(),
                chamadoEntidade.getStatus(),
                chamadoEntidade.getSolucao() // NOVO: pra solucao aparecer na resposta depois de resolvido
        );
    }
}