package com.bruno.products.services;

import com.bruno.products.dtos.response.CompraResponseDTO;

import java.util.List;

public interface ComprasService {

    List<CompraResponseDTO> retornarComprasOrdenadas();

    CompraResponseDTO retornarMaiorCompraPorAno(Integer ano);

}
