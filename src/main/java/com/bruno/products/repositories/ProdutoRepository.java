package com.bruno.products.repositories;

import com.bruno.products.dtos.ProdutoDTO;
import com.bruno.products.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
