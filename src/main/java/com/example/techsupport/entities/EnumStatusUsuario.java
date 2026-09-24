// ==========================================================================
// ARQUIVO: EnumStatusUsuario.java
// Estados de um Usuário (Técnico). EXCLUIDO = exclusão lógica; BLOQUEADO = usuário impedido de usar o sistema.
// Guardado como número (ATIVO=0, EXCLUIDO=1, BLOQUEADO=2).
// ==========================================================================
package com.example.techsupport.entities;

public enum EnumStatusUsuario {
    ATIVO,
    EXCLUIDO,
    BLOQUEADO
}
