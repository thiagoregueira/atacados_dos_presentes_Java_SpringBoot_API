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

import com.atacado.presentes.api.model.Categoria;
import com.atacado.presentes.api.repository.CategoriaRepository;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<Categoria>> listarCategorias(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarCategoriaPeloId(@PathVariable("id") Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            categoriaExistente.get().setNome(categoria.getNome());
            categoriaExistente.get().setDescricao(categoria.getDescricao());

            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoriaExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoriaPeloId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        categoriaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria deletada com sucesso!");
    }
}
