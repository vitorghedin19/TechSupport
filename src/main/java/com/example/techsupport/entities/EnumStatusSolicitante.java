// ==========================================================================
// ARQUIVO: EnumStatusSolicitante.java
// Estados de um Solicitante. EXCLUIDO = exclusão lógica (o registro continua no banco).
// Guardado como número (ATIVO=0, EXCLUIDO=1, BLOQUEADO=2).
// ==========================================================================
package com.example.techsupport.entities;

public enum EnumStatusSolicitante {
    ATIVO,
    EXCLUIDO,
    BLOQUEADO
}
