package com.example.techsupport.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    @OneToMany(mappedBy = "empresa")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Usuario> usuarios;
}