package com.atacado.presentes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atacado.presentes.api.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
