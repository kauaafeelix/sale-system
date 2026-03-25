package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.produto.request.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.response.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.ProdutoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import com.weg.centroweg.gestaovendas.domain.repository.ProdutoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;


    @Override
    public ProdutoResponseDto criarProduto(ProdutoRequestDto request) {

        Produto produto = mapper.toEntity(request);

        repository.save(produto);

        return mapper.toDto(produto);
    }

    @Override
    public ProdutoResponseDto buscarPorId(UUID id) {

        Produto produto = repository.findById(id)
                .orElseThrow(()-> new BusinessException("O ID do produto não existe"));

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
    public void deletarProduto(UUID id) {

        	Produto produto = repository.findById(id)
                    .orElseThrow(()-> new BusinessException("O ID do produto não existe"));

            repository.delete(produto);
    }

    @Override
    public void atualizarEstoque(UUID produtoId, int quantidade) {

        Produto produto = repository.findById(produtoId)
                .orElseThrow(()-> new BusinessException("O ID do produto não existe"));

        int novoEstoque = produto.getEstoque() + quantidade;

        if (novoEstoque < 0) {
            throw new BusinessException("Estoque insuficiente para a operação.");
        }

        produto.setEstoque(novoEstoque);

        repository.save(produto);

    }
}
