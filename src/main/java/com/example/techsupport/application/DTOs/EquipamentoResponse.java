package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.EnumStatusEquipamento;
import com.example.techsupport.domain.entities.Equipamento;

public record EquipamentoResponse (Long id, String equipamento, String tipo, EnumStatusEquipamento statusEquipamento) {

    public EquipamentoResponse (Equipamento equipamentoEntidade) {
        this(
                equipamentoEntidade.getId(),
                equipamentoEntidade.getEquipamento(),
                equipamentoEntidade.getTipo(),
                equipamentoEntidade.getStatus()
        );
    }

}
