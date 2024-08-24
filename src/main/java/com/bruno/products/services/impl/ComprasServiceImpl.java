package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.ClienteResponseDTO;
import com.bruno.products.dtos.response.CompraResponseDTO;
import com.bruno.products.dtos.response.ProdutoResponseDTO;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.entities.CompraEntity;
import com.bruno.products.repositories.CompraRepository;
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

    private final CompraRepository compraRepository;

    @Override
    public List<CompraResponseDTO> retornarComprasOrdenadas() {
        var clienteList = clienteService.buscarClientes();
        return extrairListaComprasPorValor(clienteList);
    }

    @Override
    public CompraResponseDTO retornarMaiorCompraPorAno(Integer ano) {
        CompraResponseDTO maiorCompra = null;
        var produtoResponseList = produtoService.buscarProdutosPorAno(ano);
        var codigoProdutoList = produtoResponseList.stream().map(produto -> produto.getCodigo().toString()).toList();
        var compraPorCodigoList = compraRepository.findAllByCodigoIn(codigoProdutoList);
        for (CompraEntity compraEntity : compraPorCodigoList) {
            var produto = produtoResponseList.stream().filter(p -> p.getCodigo().toString().equals(compraEntity.getCodigo())).findFirst().orElseThrow();
            var total = calcularvalorTotal(produto, compraEntity);
            var cliente = clienteService.buscarClientePorCpf(compraEntity.getClienteCpf());
            if ((maiorCompra == null) || (maiorCompra.getValorTotal() < total)) {
                maiorCompra = CompraResponseDTO.builder()
                        .cliente(cliente)
                        .produto(produto)
                        .quantidade(compraEntity.getQuantidade())
                        .valorTotal(total)
                        .build();
            }
        }
        return maiorCompra;
    }

    private List<CompraResponseDTO> extrairListaComprasPorValor(List<ClienteEntity> clienteEntityList) {
        List<CompraResponseDTO> listaDeCompras = new ArrayList<>();
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

    private CompraResponseDTO gerarResponse(ClienteResponseDTO clienteResponseDTO, ProdutoResponseDTO produtoResponseDTO, CompraEntity compraEntity) {
        return CompraResponseDTO.builder()
                .cliente(clienteResponseDTO)
                .produto(produtoResponseDTO)
                .quantidade(compraEntity.getQuantidade())
                .valorTotal(calcularvalorTotal(produtoResponseDTO, compraEntity))
                .build();
    }

    private List<CompraResponseDTO> ordenar(List<CompraResponseDTO> lista) {
        lista.sort(Comparator.comparing(CompraResponseDTO::getValorTotal));
        return lista;
    }

}
