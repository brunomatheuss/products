package com.bruno.products.services;

import com.bruno.products.dtos.response.ProdutoResponseDTO;

public interface ProdutoService {

    ProdutoResponseDTO buscarProduto(Long codigo);

}
