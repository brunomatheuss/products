package com.bruno.products.dtos.response;

import com.bruno.products.entities.ClienteEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesFieisResponseDTO {

    private String nome;

    private String cpf;

    private Integer quantidadeCompras;

    private Double valorTotal;

    public static ClientesFieisResponseDTO toClientesFieisResponseDTO(ClienteEntity clienteEntity, Integer quantidadeCompras, Double valorTotal) {
        return ClientesFieisResponseDTO.builder()
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .quantidadeCompras(quantidadeCompras)
                .valorTotal(valorTotal)
                .build();
    }

}
