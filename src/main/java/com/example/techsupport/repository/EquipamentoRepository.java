// ==========================================================================
// ARQUIVO: EquipamentoRepository.java -> acesso ao banco para Equipamento (mesma ideia do ChamadoRepository).
// ==========================================================================
package com.example.techsupport.repository;

import com.example.techsupport.entities.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface vazia: os métodos findAll, findById, save, deleteById... vêm do JpaRepository<Equipamento, Long>.
@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
