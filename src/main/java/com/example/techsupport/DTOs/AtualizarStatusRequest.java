package com.example.techsupport.DTOs;

import com.example.techsupport.entities.EnumStatusChamado;
import com.example.techsupport.entities.EnumStatusEquipamento;
import com.example.techsupport.entities.EnumStatusSolicitante;
import com.example.techsupport.entities.EnumStatusUsuario;

public record AtualizarStatusRequest(
        EnumStatusUsuario statusUsuario,
        EnumStatusChamado statusChamado,
        EnumStatusEquipamento statusEquipamento,
        EnumStatusSolicitante statusSolicitante) {
}
