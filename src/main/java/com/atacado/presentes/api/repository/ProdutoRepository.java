package com.atacado.presentes.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atacado.presentes.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM tb_produtos p JOIN p.categorias c, tb_avaliacoes_produto ap WHERE c.idCategoria = :idCategoria ORDER BY ap.pontuacao DESC")
    List<Produto>findByProdutosPorCategorias(@Param("idCategoria") Long idCategoria);

}
