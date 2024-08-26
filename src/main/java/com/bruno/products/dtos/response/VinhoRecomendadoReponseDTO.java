package com.bruno.products.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VinhoRecomendadoReponseDTO {

    private Integer quantidadeCompras;

    private ProdutoResponseDTO vinho;

    public static VinhoRecomendadoReponseDTO toVinhoRecomendadoReponseDTO(Integer quantidadeCompras, ProdutoResponseDTO vinho) {
        return VinhoRecomendadoReponseDTO.builder()
                .quantidadeCompras(quantidadeCompras)
                .vinho(vinho)
                .build();
    }

}
