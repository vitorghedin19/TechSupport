package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Usuario;

public record UsuarioResponse (Long id, String nome, String cpf, String email, EnumStatusUsuario statusUsuario) {

        public UsuarioResponse (Usuario usuarioEntidade) {
            this(
                    usuarioEntidade.getId(),
                    usuarioEntidade.getNome(),
                    usuarioEntidade.getCpf().toString(),
                    usuarioEntidade.getEmail(),
                    usuarioEntidade.getStatus()
            );
        }

}
