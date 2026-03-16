package com.weg.centroweg.gestaovendas.web.controller;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService servive;


    @PostMapping
    public ResponseEntity<ProdutoResponseDto>save(@Valid @RequestBody ProdutoRequestDto request){
        ProdutoResponseDto response = servive.criarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
