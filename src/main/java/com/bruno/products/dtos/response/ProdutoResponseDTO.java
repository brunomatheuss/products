package com.bruno.products.dtos.response;

import com.bruno.products.entities.ProdutoEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private String tipoVinho;

    private Double preco;

    private Integer safra;

    private Integer anoCompra;

    public static ProdutoResponseDTO toProdutoResponseDTO(ProdutoEntity produtoEntity) {
        return ProdutoResponseDTO.builder()
                .tipoVinho(produtoEntity.getTipoVinho())
                .preco(produtoEntity.getPreco())
                .safra(produtoEntity.getSafra())
                .anoCompra(produtoEntity.getAnoCompra())
                .build();
    }

}
