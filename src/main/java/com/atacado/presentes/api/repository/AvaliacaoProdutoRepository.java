package com.atacado.presentes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atacado.presentes.api.model.AvaliacaoProduto;

public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {

    @Query("SELECT t FROM tb_avaliacoes_produto t WHERE t.produto.idProduto = :id")
    Optional<List<AvaliacaoProduto>> findByProduto(@Param("id") Long idProduto);

}
