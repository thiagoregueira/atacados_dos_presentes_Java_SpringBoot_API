package com.atacado.presentes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Produtos cadastrarProduto (@RequestBody Produtos produtos) {
        return produtosRepository.save(produtos);
    }

    @GetMapping
    public List<Produtos> listarProdutos() {
        return produtosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produtos> listarProdutosPorId(@PathVariable("id") Long id) {
        return produtosRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Produtos atualizarProduto (@PathVariable("id") Long id, @RequestBody Produtos produtos) {
        Optional<Produtos> produtoExistente = produtosRepository.findById(id);

        if(produtoExistente.isPresent()) {
            produtoExistente.get().setNome(produtos.getNome());
            produtoExistente.get().setPreco(produtos.getPreco());
            produtoExistente.get().setDescricao(produtos.getDescricao());

            return produtosRepository.save(produtoExistente.get());
        }

        return null;
    }
    
    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        
        produtosRepository.deleteById(id);

        return "Produto deletado com sucesso!";
    }
}