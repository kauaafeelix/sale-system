package com.weg.centroweg.gestaovendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
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
    private BigDecimal preco;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens;


    public Produto(String nome, String descricao, BigDecimal preco, int quantidadeEstoque, List<ItemPedido> itens) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.itens = itens;
    }
}
