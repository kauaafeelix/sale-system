package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoResponseDto criarProduto(ProdutoRequestDto request);

    ProdutoResponseDto buscarPorId(UUID id);

    List<ProdutoResponseDto> listarProdutos();

    List<ProdutoResponseDto> buscarPorNome(String nome);

    ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request);

    void deletarProduto(UUID id);

    void atualizarEstoque(UUID produtoId, int quantidade);
}
