package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.*;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.entities.CompraEntity;
import com.bruno.products.exceptions.GenericErrorException;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.services.ClienteService;
import com.bruno.products.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final ProdutoService produtoService;

    @Override
    public List<ClienteEntity> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteResponseDTO buscarClientePorCpf(String cpf) {
        var clienteEntity = clienteRepository.findById(cpf).orElseThrow(() -> new GenericErrorException("Cliente não encontrado!", HttpStatus.NOT_FOUND));
        return ClienteResponseDTO.toClienteResponseDTO(clienteEntity);
    }

    @Override
    public List<ClientesFieisResponseDTO> retornarClientesFieis() {
        List<ClientesFieisResponseDTO> tresClientesFieis = new ArrayList<>();
        var clienteEntityList = buscarClientes();
        for (int i = 0; i < 3; i++) {
            ClientesFieisResponseDTO maior = calcularClienteFiel(clienteEntityList);
            tresClientesFieis.add(maior);
            clienteEntityList.removeIf(cliente -> cliente.getCpf().equals(maior.getCpf()));
        }
        ordenar(tresClientesFieis);
        return tresClientesFieis;
    }

    @Override
    public VinhoRecomendadoReponseDTO retornarRecomendacaoVinho(String cpfCliente) {
        var clienteEntity = clienteRepository.findById(cpfCliente).orElseThrow(() -> new GenericErrorException("Não foi encontrado um cliente com o CPF informado.", HttpStatus.NOT_FOUND));
        Integer quantidadeCompras = null;
        String codigo = null;
        for (CompraEntity compraEntity : clienteEntity.getCompras()) {
            if (quantidadeCompras == null) {
                quantidadeCompras = compraEntity.getQuantidade();
                codigo = compraEntity.getCodigo();
            } else {
                if (compraEntity.getQuantidade() > quantidadeCompras) {
                    quantidadeCompras = compraEntity.getQuantidade();
                    codigo = compraEntity.getCodigo();
                }
            }
        }
        var produto = produtoService.buscarProduto(Long.valueOf(codigo));
        return VinhoRecomendadoReponseDTO.toVinhoRecomendadoReponseDTO(quantidadeCompras, produto);
    }

    private void ordenar(List<ClientesFieisResponseDTO> tresClientesFieis) {
        tresClientesFieis.sort(Comparator.comparing(ClientesFieisResponseDTO::getQuantidadeCompras).thenComparing(ClientesFieisResponseDTO::getValorTotal).reversed());
    }

    private ClientesFieisResponseDTO calcularClienteFiel(List<ClienteEntity> clienteEntityList) {
        ClientesFieisResponseDTO maior = null;
        for (ClienteEntity clienteEntity : clienteEntityList) {
            var quantidadeCompras = clienteEntity.getCompras().size();
            var valorTotal = calcularValorTotalClienteFiel(clienteEntity.getCompras());
            if (maior == null) {
                maior = atualizarMaior(clienteEntity, quantidadeCompras, valorTotal);
            } else if (quantidadeCompras > maior.getQuantidadeCompras()) {
                if (valorTotal > maior.getValorTotal()) {
                    maior = atualizarMaior(clienteEntity, quantidadeCompras, valorTotal);
                }
            }
        }
        return maior;
    }

    private static ClientesFieisResponseDTO atualizarMaior(ClienteEntity clienteEntity, int quantidadeCompras, Double valorTotal) {
        ClientesFieisResponseDTO maior;
        maior = ClientesFieisResponseDTO.toClientesFieisResponseDTO(clienteEntity, quantidadeCompras, valorTotal);
        return maior;
    }

    private Double calcularValorTotalClienteFiel(List<CompraEntity> compraEntityList) {
        var valorTotal = 0.0;
        for (CompraEntity compraEntity : compraEntityList) {
            var produto = produtoService.buscarProduto(Long.valueOf(compraEntity.getCodigo()));
            valorTotal += ComprasServiceImpl.calcularvalorTotal(produto, compraEntity);
        }
        return valorTotal;
    }

}
