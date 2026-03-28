package com.weg.centroweg.gestaovendas.domain.repository;

import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository <Cliente, UUID> {
    boolean existsByEmail(String email);

}
