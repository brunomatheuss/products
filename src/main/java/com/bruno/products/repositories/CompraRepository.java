package com.bruno.products.repositories;

import com.bruno.products.entities.CompraEntity;
import com.bruno.products.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, String> {

}
