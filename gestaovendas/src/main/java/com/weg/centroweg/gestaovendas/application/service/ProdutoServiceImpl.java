package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
<<<<<<< HEAD
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import lombok.RequiredArgsConstructor;
=======
import com.weg.centroweg.gestaovendas.application.mapper.ProdutoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import com.weg.centroweg.gestaovendas.domain.repository.ProdutoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    @Override
    public ProdutoResponseDto criarProduto(ProdutoRequestDto request) {
        return null;
=======

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
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public ProdutoResponseDto buscarPorId(UUID id) {
<<<<<<< HEAD
        return null;
=======
        Produto produto = repository.findById(id).
                orElseThrow(()-> new BusinessException("O Produto não existe."));

        return mapper.toDto(produto);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<ProdutoResponseDto> listarProdutos() {
<<<<<<< HEAD
        return List.of();
=======
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<ProdutoResponseDto> buscarPorNome(String nome) {
<<<<<<< HEAD
        return List.of();
=======
        return repository.findByNomeContaining(nome)
                .stream()
                .map(mapper::toDto)
                .toList();
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request) {
<<<<<<< HEAD
        return null;
=======
        Produto produto = repository.findById(id).orElseThrow(()-> new BusinessException("O Produto não existe."));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());

        repository.save(produto);

        return mapper.toDto(produto);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public void deletarProduto(UUID id) {
<<<<<<< HEAD

=======
        Produto produto = repository.findById(id).orElseThrow(()-> new BusinessException("O Produto não existe."));

        repository.deleteById(id);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public void atualizarEstoque(UUID produtoId, int quantidade) {
<<<<<<< HEAD

=======
        Produto produto = repository.findById(produtoId)
                .orElseThrow(() -> new BusinessException("O Produto não existe."));

        int novoEstoque = produto.getQuantidadeEstoque() + quantidade;
        if (novoEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }

        produto.setQuantidadeEstoque(novoEstoque);
        repository.save(produto);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }
}
