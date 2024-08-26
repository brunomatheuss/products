package com.bruno.products.services;

import com.bruno.products.dtos.response.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO buscarProduto(Long codigo);

    List<ProdutoResponseDTO> buscarProdutosPorAno(Integer ano);

}
