package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.ProdutoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import com.weg.centroweg.gestaovendas.domain.repository.ProdutoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoServiceImpl(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProdutoResponseDto criarProduto(ProdutoRequestDto request) {
        Produto produto = mapper.toEntity(request);

        repository.save(produto);

        return mapper.toDto(produto);
    }

    @Override
    public ProdutoResponseDto buscarPorId(UUID id) {
        Produto produto = repository.findById(id).
                orElseThrow(()-> new BusinessException("O Produto não existe."));

        return mapper.toDto(produto);
    }

    @Override
    public List<ProdutoResponseDto> listarProdutos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ProdutoResponseDto> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request) {
        Produto produto = repository.findById(id).orElseThrow(()-> new BusinessException("O Produto não existe."));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());

        repository.save(produto);

        return mapper.toDto(produto);
    }

    @Override
    public void deletarProduto(UUID id) {
        Produto produto = repository.findById(id).orElseThrow(()-> new BusinessException("O Produto não existe."));

        repository.deleteById(id);
    }

    @Override
    public void atualizarEstoque(UUID produtoId, int quantidade) {
        Produto produto = repository.findById(produtoId)
                .orElseThrow(() -> new BusinessException("O Produto não existe."));

        int novoEstoque = produto.getQuantidadeEstoque() + quantidade;
        if (novoEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }

        produto.setQuantidadeEstoque(novoEstoque);
        repository.save(produto);
    }
}
