package com.bruno.products.services.impl;

import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteEntity> buscarClientes() {
        return clienteRepository.findAll();
    }
}
