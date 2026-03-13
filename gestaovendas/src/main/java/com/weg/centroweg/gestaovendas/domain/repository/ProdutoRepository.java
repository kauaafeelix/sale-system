package com.weg.centroweg.gestaovendas.domain.repository;

import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findByNomeContaining (String nome);
}
