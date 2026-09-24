// ==========================================================================
// ARQUIVO: EnumStatusEquipamento.java
// Estados de um Equipamento. EXCLUIDO é usado na "exclusão lógica": o registro NÃO some do banco,
// só muda de status (veja EquipamentoController.excluir). Guardado como número (ATIVO=0, EXCLUIDO=1).
// ==========================================================================
package com.example.techsupport.entities;

public enum EnumStatusEquipamento {
    ATIVO,
    EXCLUIDO
}
