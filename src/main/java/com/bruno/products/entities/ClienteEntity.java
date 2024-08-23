package com.bruno.products.entities;

import com.bruno.products.dtos.ClienteDTO;
import com.bruno.products.dtos.CompraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(mappedBy = "clienteCpf", cascade = CascadeType.ALL)
    private List<CompraEntity> compras;

    public static ClienteEntity dtoToEntity(ClienteDTO clienteDTO) {
        var compraEntityList = CompraEntity.getCompraEntityList(clienteDTO.getCompras(), clienteDTO.getCpf());
        return ClienteEntity.builder()
                .cpf(clienteDTO.getCpf())
                .nome(clienteDTO.getNome())
                .compras(compraEntityList)
                .build();
    }

}
