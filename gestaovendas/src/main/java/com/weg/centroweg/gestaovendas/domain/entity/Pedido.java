package com.weg.centroweg.gestaovendas.domain.entity;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID clienteId;

    @Column
    private UUID usuarioId;

    @DateTimeFormat
    private LocalDateTime dataPedido;

    @Column
    private double valorTotal;

    @Enumerated (EnumType.STRING)
    private StatusPedido status;


    public Pedido(UUID clienteId, UUID usuarioId, LocalDateTime dataPedido, double valorTotal, StatusPedido status) {
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.status = status;
    }

}
