package com.atacado.presentes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atacado.presentes.api.model.Produto;
import com.atacado.presentes.api.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtosRepository;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produtos) {

        Produto produto = produtosRepository.save(produtos);

        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produtosList = produtosRepository.findAll();

        return ResponseEntity.ok().body(produtosList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> listarProdutosPorId(@PathVariable("id") Long id) {

        Optional<Produto> produto = produtosRepository.findById(id);

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produtos) {

        Optional<Produto> produtoExistente = produtosRepository.findById(id);

        if (produtoExistente.isPresent()) {
            produtoExistente.get().setNome(produtos.getNome());
            produtoExistente.get().setPreco(produtos.getPreco());
            produtoExistente.get().setDescricao(produtos.getDescricao());

            Produto produto = produtosRepository.save(produtoExistente.get());

            return ResponseEntity.ok().body(produto);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable("id") Long id) {

        produtosRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}