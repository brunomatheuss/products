package com.bruno.products.services.impl;

import com.bruno.products.dtos.response.ProdutoResponseDTO;
import com.bruno.products.entities.ProdutoEntity;
import com.bruno.products.exceptions.GenericErrorException;
import com.bruno.products.repositories.ProdutoRepository;
import com.bruno.products.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoResponseDTO buscarProduto(Long codigo) {
        var produtoEntity = produtoRepository.findById(codigo).orElseThrow(() -> new GenericErrorException("Produto não encontrado!", HttpStatus.NOT_FOUND));
        return ProdutoResponseDTO.toProdutoResponseDTO(produtoEntity);
    }

    @Override
    public List<ProdutoResponseDTO> buscarProdutosPorAno(Integer ano) {
        List<ProdutoResponseDTO> produtoResponseDTOList = new ArrayList<>();
        var produtoEntityList = produtoRepository.findAllByAnoCompra(ano);
        if (produtoEntityList.isEmpty()) {
            throw new GenericErrorException("Não foram encontradas compras neste ano.", HttpStatus.NOT_FOUND);
        }
        for (ProdutoEntity produtoEntity : produtoEntityList) {
            produtoResponseDTOList.add(ProdutoResponseDTO.toProdutoResponseDTO(produtoEntity));
        }
        return produtoResponseDTOList;
    }
}
