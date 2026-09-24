// ==========================================================================
// ARQUIVO: AtualizarStatusRequest.java (DTO)
// JSON recebido nos endpoints  PATCH /{id}/status  de TODOS os controllers (usuario, chamado, equipamento, solicitante).
// Um único DTO serve para os quatro: cada controller usa só o campo do seu tipo. Exemplo de corpo para chamado:
//     { "statusChamado": "EM_ANDAMENTO" }
// Os outros campos ficam null e são ignorados. O JSON precisa usar exatamente o nome dos valores do enum (ex.: "FECHADO").
// ==========================================================================
package com.example.techsupport.DTOs;

import com.example.techsupport.entities.EnumStatusChamado;
import com.example.techsupport.entities.EnumStatusEquipamento;
import com.example.techsupport.entities.EnumStatusSolicitante;
import com.example.techsupport.entities.EnumStatusUsuario;

// Cada componente do record vira um campo do JSON e um método de leitura (ex.: statusRequest.statusChamado()).
public record AtualizarStatusRequest(
        EnumStatusUsuario statusUsuario,
        EnumStatusChamado statusChamado,
        EnumStatusEquipamento statusEquipamento,
        EnumStatusSolicitante statusSolicitante) {
}
