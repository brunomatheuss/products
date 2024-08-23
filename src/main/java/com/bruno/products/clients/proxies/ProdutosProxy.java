package com.bruno.products.clients.proxies;

import com.bruno.products.clients.ProdutosClient;
import com.bruno.products.dtos.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutosProxy {

    private final ProdutosClient produtosClient;

    public List<ProdutoDTO> retornarProdutos() {
        return produtosClient.retornarProdutos();
    }

}
