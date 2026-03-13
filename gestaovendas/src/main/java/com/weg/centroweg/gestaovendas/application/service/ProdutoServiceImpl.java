package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    @Override
    public ProdutoResponseDto criarProduto(ProdutoRequestDto request) {
        return null;
    }

    @Override
    public ProdutoResponseDto buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<ProdutoResponseDto> listarProdutos() {
        return List.of();
    }

    @Override
    public List<ProdutoResponseDto> buscarPorNome(String nome) {
        return List.of();
    }

    @Override
    public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request) {
        return null;
    }

    @Override
    public void deletarProduto(UUID id) {

    }

    @Override
    public void atualizarEstoque(UUID produtoId, int quantidade) {

    }
}
