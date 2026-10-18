// Entidade Equipamento -> tabela "equipamento".
package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.EnumStatusEquipamento;
import com.example.techsupport.application.DTOs.EquipamentoRequest;
import com.example.techsupport.application.DTOs.SolicitanteRequest;
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
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String equipamento;
    private String tipo;
    private EnumStatusEquipamento status = EnumStatusEquipamento.ATIVO;

    public Equipamento (EquipamentoRequest equipamento){
        this.setEquipamento(equipamento.equipamento());
        this.setTipo(equipamento.tipo());
    }

}
