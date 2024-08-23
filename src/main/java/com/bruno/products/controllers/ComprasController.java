package com.bruno.products.controllers;

import com.bruno.products.services.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComprasController {

    private final ComprasService service;

    @GetMapping("compras")
    public ResponseEntity<Object> retornarComprasOrdenadas() {
        return ResponseEntity.ok().body(service.retornarComprasOrdenadas());
    }

    @GetMapping("maiorCompra/{ano}")
    public ResponseEntity<Object> retornarMaiorCompraPorAno(@PathVariable("ano") String ano) {
        return ResponseEntity.noContent().build();
    }

}
