package com.atacado.presentes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.atacado.presentes.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
