// JSON usado nos 4 endpoints PATCH /{id}/status (usuario, chamado, equipamento, solicitante).
// Um DTO so serve pros quatro: cada controller le apenas o campo do seu tipo, os outros vem null.
package com.example.techsupport.application.DTOs;

public record AtualizarStatusRequest(
        EnumStatusUsuario statusUsuario,
        EnumStatusChamado statusChamado,
        EnumStatusEquipamento statusEquipamento,
        EnumStatusSolicitante statusSolicitante) {
}
