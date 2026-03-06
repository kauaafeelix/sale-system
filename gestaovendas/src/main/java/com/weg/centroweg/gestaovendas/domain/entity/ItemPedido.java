package com.weg.centroweg.gestaovendas.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

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
}
