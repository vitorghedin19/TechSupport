// JSON usado nos 4 endpoints PATCH /{id}/status (usuario, chamado, equipamento, solicitante).
// Um DTO so serve pros quatro: cada controller le apenas o campo do seu tipo, os outros vem null.
package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.EnumStatusChamado;
import com.example.techsupport.domain.entities.EnumStatusEquipamento;
import com.example.techsupport.domain.entities.EnumStatusSolicitante;
import com.example.techsupport.domain.entities.EnumStatusUsuario;

public record AtualizarStatusRequest(
        EnumStatusUsuario statusUsuario,
        EnumStatusChamado statusChamado,
        EnumStatusEquipamento statusEquipamento,
        EnumStatusSolicitante statusSolicitante) {
}
