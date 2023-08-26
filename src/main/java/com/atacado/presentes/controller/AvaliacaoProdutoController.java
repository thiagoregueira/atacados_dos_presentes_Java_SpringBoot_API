package com.atacado.presentes.controller;

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

import com.atacado.presentes.model.AvaliacaoProduto;
import com.atacado.presentes.repository.AvaliacaoProdutoRepository;

@RestController
@RequestMapping(value = "/AvaliacaoProduto")
public class AvaliacaoProdutoController {

    @PostMapping
    public ResponseEntity<AvaliacaoProduto> cadastrarNovaAvaliacao(@RequestBody AvaliacaoProduto avaliacaoproduto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoProdutoRepository.save(avaliacaoproduto));
    }

    @GetMapping("/{idAvaliacao}")
    public ResponseEntity<AvaliacaoProduto> buscarAvaliacaoPeloId(@PathVariable("id") Long id) {
        Optional<AvaliacaoProduto> avaliacaoProduto = avaliacaoProdutoRepository.findById(id);

        if(avaliacaoProduto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoProduto.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoProduto> atualizarAvaliacaoProduto(
            @PathVariable("id") Long id,
            @RequestBody AvaliacaoProduto avaliacaoProduto) {
        Optional<AvaliacaoProduto> avaliacaoProdutoExistente = avaliacaoProdutoRepository.findById(id);

        if (avaliacaoProdutoExistente.isPresent()) {
            avaliacaoProdutoExistente.get().setPontuacao(avaliacaoProduto.getPontuacao());
            avaliacaoProdutoExistente.get().setComentario(avaliacaoProduto.getComentario());
            return ResponseEntity.status(HttpStatus.OK).body(avaliacaoProdutoRepository.save(avaliacaoProdutoExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAvaliacaoPeloId(@PathVariable("id") Long id) {
       Optional<AvaliacaoProduto> avaliacaoProduto = avaliacaoProdutoRepository.findById(id);
       if(avaliacaoProduto.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
    }
       return ResponseEntity.status(HttpStatus.OK).body("Avaliação deletada com sucesso");
    }
    @Autowired
    private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

}
