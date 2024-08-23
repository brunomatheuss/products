package com.bruno.products.config;

import com.bruno.products.clients.proxies.ClientesComprasProxy;
import com.bruno.products.clients.proxies.ProdutosProxy;
import com.bruno.products.entities.ClienteEntity;
import com.bruno.products.entities.ProdutoEntity;
import com.bruno.products.repositories.ClienteRepository;
import com.bruno.products.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ProdutoRepository produtoRepository;

    private final ProdutosProxy produtosProxy;

    private final ClientesComprasProxy clientesComprasProxy;

    private final ClienteRepository clienteRepository;

    @Bean
    boolean popularTabelaProduto() {
        List<ProdutoEntity> produtoEntityList = new ArrayList<>();
        var produtoDTOList = produtosProxy.retornarProdutos();
        produtoDTOList.forEach(produtoDTO -> {
            produtoEntityList.add(ProdutoEntity.dtoToEntity(produtoDTO));
        });
        produtoRepository.saveAll(produtoEntityList);
        return true;
    }

    @Bean
    boolean popularTabelasClienteCompra() {
        List<ClienteEntity> clienteEntityList = new ArrayList<>();
        var clienteDTOList = clientesComprasProxy.retornarClientesCompras();
        clienteDTOList.forEach(clienteDTO -> {
            clienteEntityList.add(ClienteEntity.dtoToEntity(clienteDTO));
        });
        clienteRepository.saveAll(clienteEntityList);
        return true;
    }

}
