package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.exceptions.GenericErrorException;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Override
    public ClienteResponseDTO buscarClientePorCpf(String cpf) {
        var clienteEntity = clienteRepository.findById(cpf).orElseThrow(() -> new GenericErrorException("Cliente não encontrado!", HttpStatus.NOT_FOUND));
        return ClienteResponseDTO.toClienteResponseDTO(clienteEntity);
    }
}
