package com.atacado.presentes.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atacado.presentes.api.model.Usuario;
import com.atacado.presentes.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository acao;

    // cadastrar
    @PostMapping
    public ResponseEntity<?> cadastrarCategoria(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(acao.save(usuario));
    }

    // listar todos
    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(acao.findAll(paginacao));
    }

    // buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPeloId(@PathVariable("id") Integer id) {
        Optional<Usuario> usuario = acao.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    // atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = acao.findById(id);

        if (usuarioExistente.isPresent()) {
            usuarioExistente.get().setEmail(usuario.getEmail());
            usuarioExistente.get().setSenha(usuario.getSenha());
            usuarioExistente.get().setPerfil(usuario.getPerfil());
            usuarioExistente.get().setTelefone(usuario.getTelefone());

            return ResponseEntity.status(HttpStatus.OK).body(acao.save(usuarioExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // remover por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerPeloId(@PathVariable Integer id) {
        Optional<Usuario> usuario = acao.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        acao.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletada com sucesso!");
    }
}
