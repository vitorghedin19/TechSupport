// Estados de um Chamado. EXCLUIDO = exclusao logica (nao apaga do banco). RESOLVIDO so via resolver(), com a solucao.
// ATRASADO existe no enum, mas nenhum codigo aplica esse status ainda (SLA nao implementado).
package com.example.techsupport.application.DTOs;

public enum EnumStatusChamado {
    ABERTO,
    EM_ANDAMENTO,
    RESOLVIDO,
    FECHADO,
    ATRASADO,
    EXCLUIDO
}
