package com.bruno.products.clients;

import com.bruno.products.dtos.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "clientes-compras-client", url = "${mocks.clientes-compras.url}")
public interface ClientesComprasClient {

    @GetMapping
    List<ClienteDTO> retornarClientesCompras();

}
