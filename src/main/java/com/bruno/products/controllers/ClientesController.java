package com.bruno.products.controllers;

import com.bruno.products.dtos.response.ClientesFieisResponseDTO;
import com.bruno.products.dtos.response.VinhoRecomendadoReponseDTO;
import com.bruno.products.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService clienteService;

    @GetMapping("clientes-fieis")
    public ResponseEntity<List<ClientesFieisResponseDTO>> retornarClientesFieis() {
        return ResponseEntity.ok(clienteService.retornarClientesFieis());
    }

    @GetMapping("recomendacao/{clienteCpf}/tipo")
    public ResponseEntity<VinhoRecomendadoReponseDTO> retornarRecomendacaoVinho(@PathVariable("clienteCpf") String clienteCpf) {
        return ResponseEntity.ok(clienteService.retornarRecomendacaoVinho(clienteCpf));
    }

}
