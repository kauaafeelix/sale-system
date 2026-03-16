package com.weg.centroweg.gestaovendas.domain.entity;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private RoleUsuario role;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private UUID criadoPor;

    public Usuario(String nome, String email, String senha, RoleUsuario role, LocalDateTime dataCriacao, UUID criadoPor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.dataCriacao = dataCriacao;
        this.criadoPor = criadoPor;
    }
}
