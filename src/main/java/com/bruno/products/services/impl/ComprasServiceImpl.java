package com.bruno.products.services.impl;

import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.services.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprasServiceImpl implements ComprasService {

    private final ClienteRepository clienteRepository;

    public void retornarComprasOrdenadas() {
        var clienteList = clienteRepository.findAll();
        ordenarClienteList(clienteList);
    }

    private List<ClienteEntity> ordenarClienteList(List<ClienteEntity> clienteEntityList) {
        clienteEntityList.forEach(clienteEntity -> {});
        return null;
    }

}
