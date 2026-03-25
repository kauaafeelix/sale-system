package com.weg.centroweg.gestaovendas.web.controller;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PreAuthorize("hasAnyRole('VENDEDOR')")
    @PostMapping
    public ResponseEntity<PedidoResponseDto>save (@Valid @RequestBody PedidoRequestDto request){

        PedidoResponseDto response = service.criarPedido(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> findAll (){

        List<PedidoResponseDto> responses = service.listarPedidos();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> findById (@PathVariable UUID id){

        PedidoResponseDto response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<PedidoResponseDto>> findByClienteId(@PathVariable("id") UUID clienteId){

        List<PedidoResponseDto> response = service.listarPedidosPorCliente(clienteId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<PedidoResponseDto>> findByUsuarioId(@PathVariable("id") UUID usuarioId){

        List<PedidoResponseDto> response = service.listarPedidosPorUsuario(usuarioId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update (@PathVariable UUID id){

       service.cancelarPedido(id);
       return ResponseEntity.noContent().build();
    }

}
