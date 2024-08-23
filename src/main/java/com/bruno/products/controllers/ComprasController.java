package com.bruno.products.controllers;

import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ComprasController {

    @GetMapping("compras")
    public ResponseEntity<Object> retornarComprasOrdenadas() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("maiorCompra/{ano}")
    public ResponseEntity<Object> retornarMaiorCompraPorAno(@PathVariable("ano") String ano) {
        return ResponseEntity.noContent().build();
    }


}
