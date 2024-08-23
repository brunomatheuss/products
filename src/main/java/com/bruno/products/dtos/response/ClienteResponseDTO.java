package com.bruno.products.dtos.response;

import com.bruno.products.entities.ClienteEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private String nome;

    private String cpf;

    public static ClienteResponseDTO toClienteResponseDTO(ClienteEntity clienteEntity) {
        return ClienteResponseDTO.builder()
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .build();
    }

}
