// ==========================================================================
// ARQUIVO: Solicitante.java (ENTIDADE JPA) -> tabela "solicitante".
// Representa quem ABRE o chamado (o cliente/funcionário que pede suporte).
// ==========================================================================
package com.example.techsupport.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity = vira tabela; @Data / @NoArgsConstructor / @AllArgsConstructor = Lombok gera getters, setters e construtores.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante {

    // Chave primária gerada pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    // Setor/departamento do solicitante.
    private String setor;
    // Status (enum salvo como número); todo solicitante novo nasce ATIVO.
    private EnumStatusSolicitante status = EnumStatusSolicitante.ATIVO;
}
