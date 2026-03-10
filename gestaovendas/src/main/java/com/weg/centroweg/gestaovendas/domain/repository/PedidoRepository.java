package com.weg.centroweg.gestaovendas.domain.repository;

import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByIdUsuario (UUID idUsuario);
    List <Pedido> findByIdCliente (UUID idCliente);

}
