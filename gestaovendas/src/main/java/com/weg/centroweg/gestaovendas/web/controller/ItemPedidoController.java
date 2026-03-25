package com.weg.centroweg.gestaovendas.web.controller;


import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAnyRole;

@RequiredArgsConstructor
@RestController
@RequestMapping("/itens")
public class ItemPedidoController {

    private final ItemPedidoService service;

    @PreAuthorize("hasAnyRole('ADMIN','VENDEDOR')")
    @PostMapping
    public ResponseEntity<ItemPedidoResponseDto> save (
            @Valid @RequestBody ItemPedidoRequestDto request
    ){
        ItemPedidoResponseDto response = service.criarItemPedido(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponseDto>> findAll(){
        List<ItemPedidoResponseDto> response = service.listarItemPedidos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDto> findById (@PathVariable UUID id){
    ItemPedidoResponseDto response = service.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody ItemPedidoRequestDto request
    ) {
        ItemPedidoResponseDto response = service.atualizarItemPedido(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deletarItemPedido(id);
        return ResponseEntity.noContent().build();
    }
}
