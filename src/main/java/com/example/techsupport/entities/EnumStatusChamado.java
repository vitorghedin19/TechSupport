// ==========================================================================
// ARQUIVO: EnumStatusChamado.java
// Enum com os estados possíveis de um Chamado (ciclo de vida do chamado).
// ATRASADO é o status do diferencial de SLA (chamado crítico aberto há mais de 24h).
// ATENÇÃO: como o campo "status" em Chamado não tem @Enumerated, o banco guarda o NÚMERO da posição
// (ABERTO=0, EM_ANDAMENTO=1, RESOLVIDO=2, FECHADO=3, ATRASADO=4). Por isso NÃO se deve reordenar
// nem inserir valores no meio depois que já existem dados no banco. No JSON da API, aparece o nome ("ABERTO").
// ==========================================================================
package com.example.techsupport.entities;

public enum EnumStatusChamado {
    ABERTO,
    EM_ANDAMENTO,
    RESOLVIDO,
    FECHADO,
    ATRASADO
}
