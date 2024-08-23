package com.bruno.products.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long codigo;

    @JsonAlias("tipo_vinho")
    private String tipoVinho;

    private Double preco;

    private Integer safra;

    @JsonAlias("ano_compra")
    private Integer anoCompra;

}
