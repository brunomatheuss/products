package com.bruno.products.clients;

import com.bruno.products.dtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "produtos-client", url = "${mocks.produtos.url}")
public interface ProdutosClient {

    @GetMapping
    List<ProdutoDTO> retornarProdutos();

}
