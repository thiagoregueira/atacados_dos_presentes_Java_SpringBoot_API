package com.atacado.presentes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atacado.presentes.api.Categorias;

public interface categoriasRepository extends JpaRepository<Categorias, Long> {

}
