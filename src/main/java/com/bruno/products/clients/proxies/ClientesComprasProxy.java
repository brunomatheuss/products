package com.bruno.products.clients.proxies;

import com.bruno.products.clients.ClientesComprasClient;
import com.bruno.products.dtos.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientesComprasProxy {

    private final ClientesComprasClient clientesComprasClient;

    public List<ClienteDTO> retornarClientesCompras() {
        return clientesComprasClient.retornarClientesCompras();
    }

}
