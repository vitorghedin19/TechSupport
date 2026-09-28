// Pasta entities: cada classe aqui vira uma TABELA no banco (o Hibernate cria sozinho).
// Chamado e a entidade principal do sistema: titulo, descricao, prioridade e status.
package com.example.techsupport.entities;

import ch.qos.logback.core.status.Status;
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
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private EnumStatusChamado status = EnumStatusChamado.ABERTO;
}
