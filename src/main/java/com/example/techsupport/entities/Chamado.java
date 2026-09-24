// ==========================================================================
// ARQUIVO: Chamado.java (ENTIDADE JPA)
// Cada objeto desta classe = uma linha da tabela "chamado" no PostgreSQL. O Hibernate cria a tabela sozinho
// (ddl-auto=update no application.properties) com uma coluna para cada atributo.
//
// OBSERVAÇÕES para a apresentação:
//  - Por ora o Chamado só tem: id, titulo, descricao, prioridade e status. NÃO existem aqui campos para a data de
//    abertura, para a solução adotada/histórico, nem ligação com Técnico, Equipamento e Solicitante (@ManyToOne).
//    Esses campos seriam necessários para a regra "só fecha com solução registrada" e para o SLA de 24h.
//  - "prioridade" é String livre (ex.: "Crítico"), não um enum.
// ==========================================================================
package com.example.techsupport.entities;

// Import automático que sobrou e NÃO é usado no código (pode ser removido sem efeito).
import ch.qos.logback.core.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity: diz ao JPA/Hibernate que esta classe é mapeada para uma tabela do banco.
@Entity
// @Data (Lombok): gera em tempo de compilação getters, setters, toString, equals e hashCode.
// É por isso que existem chamado.getTitulo() / setStatus(...) sem que eles estejam escritos aqui.
@Data
// @NoArgsConstructor (Lombok): construtor vazio. O JPA e o Jackson (JSON -> objeto) precisam dele.
@NoArgsConstructor
// @AllArgsConstructor (Lombok): construtor com todos os campos.
@AllArgsConstructor
public class Chamado {

    // @Id: chave primária da tabela.
    @Id
    // @GeneratedValue IDENTITY: quem gera o id é o próprio banco (auto incremento). Não enviamos o id ao criar.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    // Sem @Enumerated, o JPA salva o enum pela POSIÇÃO (número). O "= ABERTO" é o valor padrão:
    // se o JSON do POST não trouxer status, todo chamado novo nasce ABERTO.
    private EnumStatusChamado status = EnumStatusChamado.ABERTO;
}
