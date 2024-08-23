package com.bruno.products.services;

import com.bruno.products.dtos.response.ComprasOrdenadasResponseDTO;

import java.util.List;

public interface ComprasService {

    List<ComprasOrdenadasResponseDTO> retornarComprasOrdenadas();

}
