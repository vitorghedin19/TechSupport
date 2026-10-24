package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.EnumSetor;
import com.example.techsupport.application.DTOs.EnumStatusSolicitante;
import com.example.techsupport.application.DTOs.SolicitanteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String role = "ROLE_SOLICITANTE";

    @Enumerated(EnumType.STRING)
    private EnumSetor setor;

    @Enumerated(EnumType.STRING)
    private EnumStatusSolicitante status = EnumStatusSolicitante.ATIVO;

    public void setEmail(String email) {
        if(!email.isEmpty() && !email.contains("@")){
            throw new IllegalArgumentException("E-mail Inválido!");
        }
        this.email = email;
    }

    public Solicitante (SolicitanteRequest solicitante) {

        this.setNome(solicitante.nome());
        this.setSenha(solicitante.senha());
        this.setEmail(solicitante.email());
        this.setSetor(solicitante.setor());
        this.setRole("ROLE_SOLICITANTE");

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}