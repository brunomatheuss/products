package com.bruno.products.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprasOrdenadasResponseDTO {

    private ClienteResponseDTO cliente;

    private ProdutoResponseDTO produto;

    private Integer quantidade;

    private Double valorTotal;

}
