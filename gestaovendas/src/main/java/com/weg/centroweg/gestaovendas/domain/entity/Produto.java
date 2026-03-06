package com.weg.centroweg.gestaovendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column (nullable = false)
    private double preco;

    @Column(nullable = false)
    private int quantidadeEstoque;


    public Produto(String nome, String descricao, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
