package com.atacado.presentes.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario obj) {
        return ResponseEntity.ok(acao.save(obj));
    }

    // listar todos
    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(acao.findAll());

    }

    // buscar por id
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(acao.findById(id));
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
