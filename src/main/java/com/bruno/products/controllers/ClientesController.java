package com.bruno.products.controllers;

import com.bruno.products.clients.ProdutosClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ProdutosClient produtosClient;

    @GetMapping("clientes-fieis")
    public ResponseEntity<Object> retornarClientesFieis() {
        return ResponseEntity.of(Optional.ofNullable(produtosClient.retornarProdutos()));
    }

}
