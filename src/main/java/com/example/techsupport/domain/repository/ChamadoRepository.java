// Pasta repository: camada de acesso ao banco. Sao interfaces vazias, sem codigo escrito -
// o Spring Data JPA cria a implementacao sozinho (SELECT, INSERT, UPDATE, DELETE), so por causa
// do "extends JpaRepository<Entidade, TipoDoId>".
package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ja vem pronto: findAll(), findById(id), save(obj), deleteById(id)...
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
