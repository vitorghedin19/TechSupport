// Pasta entities: cada classe aqui vira uma TABELA no banco (o Hibernate cria sozinho).
// Chamado e a entidade principal do sistema: titulo, descricao, prioridade e status.
package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.ChamadoRequest;
import com.example.techsupport.application.DTOs.EnumStatusChamado;
import com.example.techsupport.application.DTOs.SolicitanteRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // -> marca a classe como uma tabela do banco.
@Data // -> gera getters, setters, toString, equals e hashCode.
@NoArgsConstructor // -> gera construtor vazio (Hibernate exige isso pra criar objetos do banco).
@AllArgsConstructor // -> gera construtor com todos os campos.
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private EnumStatusChamado status = EnumStatusChamado.ABERTO;

    public Chamado (ChamadoRequest chamado){
        this.setTitulo(chamado.titulo());
        this.setDescricao(chamado.descricao());
        this.setPrioridade(chamado.prioridade());
    }

}
