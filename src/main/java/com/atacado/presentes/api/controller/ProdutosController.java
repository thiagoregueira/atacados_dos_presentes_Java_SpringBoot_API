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

import com.atacado.presentes.api.model.Produtos;
import com.atacado.presentes.api.repository.ProdutosRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;

    @PostMapping
    public ResponseEntity<Produtos> cadastrarProduto (@RequestBody Produtos produtos) {

        Produtos produto = produtosRepository.save(produtos);

        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> listarProdutos() {

        List<Produtos> produtosList = produtosRepository.findAll();

        return ResponseEntity.ok().body(produtosList);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produtos>> listarProdutosPorId(@PathVariable("id") Long id) {

        Optional<Produtos> produto = produtosRepository.findById(id);

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizarProduto (@PathVariable("id") Long id, @RequestBody Produtos produtos) {
        
        Optional<Produtos> produtoExistente = produtosRepository.findById(id);

        if(produtoExistente.isPresent()) {
            produtoExistente.get().setNome(produtos.getNome());
            produtoExistente.get().setPreco(produtos.getPreco());
            produtoExistente.get().setDescricao(produtos.getDescricao());

            Produtos produto = produtosRepository.save(produtoExistente.get());

            return ResponseEntity.ok().body(produto);
        }

        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Produtos> deletarProduto(@PathVariable("id") Long id) {
        
        produtosRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}