// Acesso ao banco para Equipamento (mesma ideia do ChamadoRepository).
package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
