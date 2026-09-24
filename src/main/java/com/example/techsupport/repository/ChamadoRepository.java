// ==========================================================================
// ARQUIVO: ChamadoRepository.java  -> camada de ACESSO AO BANCO para Chamado.
// É uma INTERFACE sem nenhum código: o Spring Data JPA cria a implementação sozinho em tempo de execução.
// ==========================================================================
package com.example.techsupport.repository;

import com.example.techsupport.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: marca como componente de acesso a dados (com JpaRepository já seria detectado, é só explícito).
@Repository
// JpaRepository<Chamado, Long>: <entidade gerenciada, tipo do @Id>. Ao herdar, já ganhamos pronto:
// findAll(), findById(id), save(obj) (insere se não tem id, atualiza se tem), deleteById(id), count(), existsById(id)...
// Os controllers só fazem @Autowired desta interface e chamam esses métodos; o SQL é gerado pelo Hibernate.
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
