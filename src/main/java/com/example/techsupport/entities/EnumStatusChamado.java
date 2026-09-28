// Estados possiveis de um Chamado. ATRASADO e o status do SLA (chamado critico ha mais de 24h).
// EXCLUIDO e usado pelo endpoint de excluir chamado (exclusao logica, nao apaga do banco).
package com.example.techsupport.entities;

public enum EnumStatusChamado {
    ABERTO,
    EM_ANDAMENTO,
    RESOLVIDO,
    FECHADO,
    ATRASADO,
    EXCLUIDO
}
