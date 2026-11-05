package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.SolicitanteLogado;
import com.example.techsupport.application.DTOs.UsuarioLogado;
import com.example.techsupport.domain.entities.Empresa;
import com.example.techsupport.domain.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextoSegurancaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Object getLogado() {
        var autenticacao = SecurityContextHolder.getContext().getAuthentication();
        return autenticacao == null ? null : autenticacao.getPrincipal();
    }

    public boolean isAdmin() {
        return getLogado() instanceof UsuarioLogado usuario
                && usuario.authorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public Long getEmpresaId() {
        Object logado = getLogado();
        if (logado instanceof UsuarioLogado usuario) {
            return usuario.empresa_id();
        }
        if (logado instanceof SolicitanteLogado solicitante) {
            return solicitante.empresaId();
        }
        return null;
    }

    public Empresa getEmpresaDoLogado() {
        Long empresaId = getEmpresaId();
        return empresaId == null ? null : empresaRepository.getReferenceById(empresaId);
    }

    public boolean podeAcessar(Empresa empresa) {
        if (isAdmin()) {
            return true;
        }
        Long empresaId = getEmpresaId();
        return empresa != null && empresaId != null && empresa.getId().equals(empresaId);
    }
}