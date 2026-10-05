// Entidade Solicitante -> tabela "solicitante". E quem abre o chamado (cliente/funcionario).
package com.example.techsupport.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String setor;
    private EnumStatusSolicitante status = EnumStatusSolicitante.ATIVO;
}
