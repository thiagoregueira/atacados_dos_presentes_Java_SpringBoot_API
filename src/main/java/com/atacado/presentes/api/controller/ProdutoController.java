package com.atacado.presentes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<Produto>> listarProdutos(
            @PageableDefault(size = 10, page = 0) Pageable paginacao) {

        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> listarProdutosPorId(@PathVariable("id") Long id) {

        Optional<Produto> produto = produtosRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produtos) {

        Optional<Produto> produtoExistente = produtosRepository.findById(id);

        if (produtoExistente.isPresent()) {
            produtoExistente.get().setNome(produtos.getNome());
            produtoExistente.get().setPreco(produtos.getPreco());
            produtoExistente.get().setDescricao(produtos.getDescricao());
            produtoExistente.get().setFornecedor(produtos.getFornecedor());

            Produto produto = produtosRepository.save(produtoExistente.get());

            return ResponseEntity.ok().body(produto);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable("id") Long id) {

        Optional<Produto> produto = produtosRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        produtosRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto deletado com sucesso!");

    }

    @GetMapping("/categorias/{idCategoria}")
    public ResponseEntity<List<Produto>> produtosPorCategoria(@PathVariable("idCategoria") Long idCategoria){

        List<Produto> produtos = produtosRepository.findByProdutosPorCategorias(idCategoria);

        if(produtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }  

}