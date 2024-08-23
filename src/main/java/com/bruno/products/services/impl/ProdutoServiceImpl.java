package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.ProdutoResponseDTO;
import com.bruno.products.repositories.ProdutoRepository;
import com.bruno.products.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoResponseDTO buscarProduto(Long codigo) {
        var produtoEntity = produtoRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        return ProdutoResponseDTO.toProdutoResponseDTO(produtoEntity);
    }
}
