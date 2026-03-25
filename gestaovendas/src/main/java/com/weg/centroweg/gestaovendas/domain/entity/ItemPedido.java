package com.weg.centroweg.gestaovendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private int quantidade;

    @Column
    private double precoUnitario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;


    public ItemPedido(int quantidade, double precoUnitario) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}
