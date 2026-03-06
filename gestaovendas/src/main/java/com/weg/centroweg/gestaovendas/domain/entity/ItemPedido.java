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
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID pedidoId;

    @Column
    private UUID produtoId;

    @Column
    private int quantidade;

    @Column
    private double precoUnitario;

    public ItemPedido(UUID pedidoId, UUID produtoId, int quantidade, double precoUnitario) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}
