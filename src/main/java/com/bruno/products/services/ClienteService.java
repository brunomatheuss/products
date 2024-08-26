package com.bruno.products.services;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.dtos.response.ClientesFieisResponseDTO;
import com.bruno.products.dtos.response.VinhoRecomendadoReponseDTO;
import com.bruno.products.entities.ClienteEntity;

import java.util.List;

public interface ClienteService {

    List<ClienteEntity> buscarClientes();

    ClienteResponseDTO buscarClientePorCpf(String cpf);

    List<ClientesFieisResponseDTO> retornarClientesFieis();

    VinhoRecomendadoReponseDTO retornarRecomendacaoVinho(String clienteCpf);

}
