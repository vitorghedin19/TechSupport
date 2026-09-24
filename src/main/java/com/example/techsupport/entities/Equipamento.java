// ==========================================================================
// ARQUIVO: Equipamento.java (ENTIDADE JPA) -> tabela "equipamento".
// Representa um equipamento que pode ter problema (ex.: notebook, impressora).
// ==========================================================================
package com.example.techsupport.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// @Entity = vira tabela; @Data = Lombok gera getters/setters/toString/equals/hashCode;
// @NoArgsConstructor e @AllArgsConstructor = Lombok gera os construtores (o vazio é exigido por JPA/Jackson).
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {

    // Chave primária gerada pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nome/identificação do equipamento (o atributo tem o mesmo nome da classe, é só um campo String).
    private String equipamento;
    // Categoria do equipamento.
    private String tipo;
    // Status (enum salvo como número); todo equipamento novo nasce ATIVO.
    private EnumStatusEquipamento status = EnumStatusEquipamento.ATIVO;
}
