package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Chamado;
import com.example.techsupport.domain.entities.EnumStatusChamado;

public record ChamadoResponse (Long id, String titulo, String descricao, String prioridade, EnumStatusChamado statusChamado) {

    public ChamadoResponse (Chamado chamadoEntidade) {
        this(
                chamadoEntidade.getId(),
                chamadoEntidade.getTitulo(),
                chamadoEntidade.getDescricao(),
                chamadoEntidade.getPrioridade(),
                chamadoEntidade.getStatus()
        );
    }
}
