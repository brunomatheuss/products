package com.bruno.products.services;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.entities.ClienteEntity;

import java.util.List;

public interface ClienteService {

    List<ClienteEntity> buscarClientes();

    ClienteResponseDTO buscarClientePorCpf(String cpf);

}
