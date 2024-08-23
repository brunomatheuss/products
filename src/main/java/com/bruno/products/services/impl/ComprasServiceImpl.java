package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.dtos.response.ComprasOrdenadasResponseDTO;
import com.bruno.products.dtos.response.ProdutoResponseDTO;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.entities.CompraEntity;
import com.bruno.products.services.ClienteService;
import com.bruno.products.services.ComprasService;
import com.bruno.products.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprasServiceImpl implements ComprasService {

    private final ProdutoService produtoService;

    private final ClienteService clienteService;

    public List<ComprasOrdenadasResponseDTO> retornarComprasOrdenadas() {
        var clienteList = clienteService.buscarClientes();
        return ordenarClienteList(clienteList);
    }

    private List<ComprasOrdenadasResponseDTO> ordenarClienteList(List<ClienteEntity> clienteEntityList) {
        List<ComprasOrdenadasResponseDTO> listaDeCompras = new ArrayList<>();
        clienteEntityList.forEach(clienteEntity -> {
            var clienteResponseDTO = ClienteResponseDTO.toClienteResponseDTO(clienteEntity);
            for (CompraEntity compraEntity : clienteEntity.getCompras()) {
                var produtoResponseDTO = produtoService.buscarProduto(Long.valueOf(compraEntity.getCodigo()));
                listaDeCompras.add(gerarResponse(clienteResponseDTO, produtoResponseDTO, compraEntity));
            }
        });
        return ordenar(listaDeCompras);
    }

    private Double calcularvalorTotal(ProdutoResponseDTO produto, CompraEntity compra) {
        return produto.getPreco() * compra.getQuantidade();
    }

    private ComprasOrdenadasResponseDTO gerarResponse(ClienteResponseDTO clienteResponseDTO, ProdutoResponseDTO produtoResponseDTO, CompraEntity compraEntity) {
        return ComprasOrdenadasResponseDTO.builder()
                .cliente(clienteResponseDTO)
                .produto(produtoResponseDTO)
                .quantidade(compraEntity.getQuantidade())
                .valorTotal(calcularvalorTotal(produtoResponseDTO, compraEntity))
                .build();
    }

    private List<ComprasOrdenadasResponseDTO> ordenar(List<ComprasOrdenadasResponseDTO> lista) {
        lista.sort(Comparator.comparing(ComprasOrdenadasResponseDTO::getValorTotal));
        return lista;
    }

}
