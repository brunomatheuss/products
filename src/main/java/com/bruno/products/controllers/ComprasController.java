package com.bruno.products.controllers;

import com.bruno.products.dtos.response.CompraResponseDTO;
import com.bruno.products.services.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComprasController {

    private final ComprasService service;

    @GetMapping("compras")
    public ResponseEntity<List<CompraResponseDTO>> retornarComprasOrdenadas() {
        return ResponseEntity.ok().body(service.retornarComprasOrdenadas());
    }

    @GetMapping("maiorCompra/{ano}")
    public ResponseEntity<CompraResponseDTO> retornarMaiorCompraPorAno(@PathVariable("ano") Integer ano) {
        return ResponseEntity.ok().body(service.retornarMaiorCompraPorAno(ano));
    }

}
