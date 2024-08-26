package com.bruno.products.entities;

import com.bruno.products.dtos.CompraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPRA")
public class CompraEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "CLIENTE_CPF")
    private String clienteCpf;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    public static CompraEntity dtoToEntity(CompraDTO compraDTO, String clienteCpf) {
        return CompraEntity.builder()
                .codigo(compraDTO.getCodigo())
                .quantidade(compraDTO.getQuantidade())
                .clienteCpf(clienteCpf)
                .build();
    }

    public static List<CompraEntity> getCompraEntityList(List<CompraDTO> compraDTOList, String clienteCpf) {
        List<CompraEntity> compraEntityList = new ArrayList<>();
        compraDTOList.forEach(compraDTO -> {
            compraEntityList.add(dtoToEntity(compraDTO, clienteCpf));
        });
        return compraEntityList;
    }

}
