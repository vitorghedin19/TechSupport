// Acesso ao banco para Solicitante (mesma ideia do ChamadoRepository).
package com.example.techsupport.domain.repository;

import com.example.techsupport.application.DTOs.EnumStatusSolicitante;
import com.example.techsupport.domain.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {

    // Versao antiga do login (sem checar status); hoje o login usa a versao com status abaixo.
    boolean existsSolicitanteByEmailAndSenha(String email, String senha);

    // Login so de solicitante com o status informado (ATIVO): bloqueado/excluido nao entra.
    boolean existsSolicitanteByEmailAndSenhaAndStatus(String email, String senha, EnumStatusSolicitante status);

    // Solicitantes de uma empresa.
    List<Solicitante> findByEmpresa_Id(Long empresaId);
}