package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.produto.request.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.response.ProdutoResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoResponseDto criarProduto(ProdutoRequestDto request);

    ProdutoResponseDto buscarPorId(UUID id);

    List<ProdutoResponseDto> listarProdutos();

    List<ProdutoResponseDto> buscarPorNome(String nome);

    void deletarProduto(UUID id);

    void atualizarEstoque(UUID produtoId, int quantidade);
}
