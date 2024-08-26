package com.bruno.products.entities;

import com.bruno.products.dtos.ProdutoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class ProdutoEntity {

    @Id
    @Column(name = "CODIGO")
    private Long codigo;

    @Column(name = "TIPO_VINHO")
    private String tipoVinho;

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "SAFRA")
    private Integer safra;

    @Column(name = "ANO_COMPRA")
    private Integer anoCompra;

    public static ProdutoEntity dtoToEntity(ProdutoDTO produtoDTO) {
        return ProdutoEntity.builder()
                .codigo(produtoDTO.getCodigo())
                .tipoVinho(produtoDTO.getTipoVinho())
                .preco(produtoDTO.getPreco())
                .safra(produtoDTO.getSafra())
                .anoCompra(produtoDTO.getAnoCompra())
                .build();
    }

}
