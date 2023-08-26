package com.atacado.presentes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atacado.presentes.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
