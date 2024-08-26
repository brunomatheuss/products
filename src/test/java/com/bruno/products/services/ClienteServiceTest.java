package com.bruno.products.services;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.dtos.response.ProdutoResponseDTO;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.entities.CompraEntity;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.services.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    ProdutoService produtoService;

    @Test
    void shouldBuscarClientesSuccess() {
        var clienteEntity = getClienteEntity(List.of(getCompraEntity()));
        when(clienteRepository.findAll()).thenReturn(List.of(clienteEntity));

        var response = clienteService.buscarClientes();

        assertEquals(List.of(clienteEntity), response);
    }

    @Test
    void shouldBuscarClientePorCpfSuccess() {
        var clienteEntity = getClienteEntity(List.of(getCompraEntity()));
        when(clienteRepository.findById(anyString())).thenReturn(Optional.ofNullable(clienteEntity));

        var clienteResponseDTO = ClienteResponseDTO.toClienteResponseDTO(clienteEntity);

        var response = clienteService.buscarClientePorCpf("11111111111");

        assertEquals(clienteResponseDTO.getCpf(), response.getCpf());
        assertEquals(clienteResponseDTO.getNome(), response.getNome());
    }

    @Test
    void shouldRetornarClientesFieisSuccess() {
        when(clienteRepository.findAll()).thenReturn(getClientesFieisList());
        when(produtoService.buscarProduto(any())).thenReturn(getProdutoResponseDTO());

        var response = clienteService.retornarClientesFieis();

        assertEquals(3, response.size());
        assertEquals("Cliente1", response.get(0).getNome());
        assertEquals("Cliente3", response.get(1).getNome());
        assertEquals("Cliente2", response.get(2).getNome());
    }

    @Test
    void shouldRetornarRecomendacaoVinhoSuccess() {
        var compraEntity = CompraEntity.builder()
                .clienteCpf("11111111111")
                .quantidade(2)
                .codigo("2")
                .build();
        List<CompraEntity> compraEntityList = new ArrayList<>();
        compraEntityList.add(getCompraEntity());
        compraEntityList.add(compraEntity);
        var clienteEntity = getClienteEntity(compraEntityList);
        when(clienteRepository.findById(anyString())).thenReturn(Optional.ofNullable(clienteEntity));
        when(produtoService.buscarProduto(any())).thenReturn(getProdutoResponseDTO());

        clienteService.retornarRecomendacaoVinho("11111111111");

        verify(produtoService, times(1)).buscarProduto(2L);
        verify(produtoService, times(0)).buscarProduto(1L);
    }

    private CompraEntity getCompraEntity() {
        return CompraEntity.builder()
                .clienteCpf("11111111111")
                .quantidade(1)
                .codigo("1")
                .build();
    }

    private ClienteEntity getClienteEntity(List<CompraEntity> compraEntityList) {
        return ClienteEntity.builder()
                .nome("Teste")
                .cpf("11111111111")
                .compras(compraEntityList)
                .build();
    }

    private ProdutoResponseDTO getProdutoResponseDTO() {
        return ProdutoResponseDTO.builder()
                .codigo(1L)
                .tipoVinho("Tinto")
                .preco(229.99)
                .safra(2017)
                .anoCompra(2018)
                .build();
    }

    private List<ClienteEntity> getClientesFieisList() {
        List<ClienteEntity> clienteEntityList = new ArrayList<>();
        var cliente1 = ClienteEntity.builder()
                .nome("Cliente1")
                .cpf("11111111111")
                .compras(List.of(CompraEntity.builder()
                        .clienteCpf("11111111111")
                        .quantidade(4)
                        .codigo("1")
                        .build()))
                .build();
        var cliente2 = ClienteEntity.builder()
                .nome("Cliente2")
                .cpf("22222222222")
                .compras(List.of(CompraEntity.builder()
                        .clienteCpf("22222222222")
                        .quantidade(2)
                        .codigo("1")
                        .build()))
                .build();
        var cliente3 = ClienteEntity.builder()
                .nome("Cliente3")
                .cpf("33333333333")
                .compras(List.of(CompraEntity.builder()
                        .clienteCpf("33333333333")
                        .quantidade(3)
                        .codigo("1")
                        .build()))
                .build();
        clienteEntityList.add(cliente1);
        clienteEntityList.add(cliente2);
        clienteEntityList.add(cliente3);
        return clienteEntityList;
    }

}
