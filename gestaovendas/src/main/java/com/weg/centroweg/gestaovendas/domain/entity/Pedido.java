package com.weg.centroweg.gestaovendas.domain.entity;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @DateTimeFormat
    private LocalDateTime dataPedido;

    @Column
    private double valorTotal;

    @Enumerated (EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;


    public Pedido(LocalDateTime dataPedido, double valorTotal, StatusPedido status) {
        this.dataPedido = LocalDateTime.now();
        this.valorTotal = valorTotal;
        this.status = status;
    }
}
