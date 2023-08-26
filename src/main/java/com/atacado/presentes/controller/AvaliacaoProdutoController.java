package com.atacado.presentes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public AvaliacaoProduto cadastrarNovaAvaliacao(@RequestBody AvaliacaoProduto avaliacaoproduto){
        return avaliacaoProdutoRepository.save(avaliacaoproduto);
    }

    @PutMapping("/{id}")
    public AvaliacaoProduto atualizarAvaliacaoProduto(
            @PathVariable("id") Long id,
            @RequestBody AvaliacaoProduto avaliacaoProduto) {
        Optional<AvaliacaoProduto> avaliacaoProdutoExistente = avaliacaoProdutoRepository.findById(id);

        if (avaliacaoProdutoExistente.isPresent()) {
            avaliacaoProdutoExistente.get().setPontuacao(avaliacaoProduto.getPontuacao());
            avaliacaoProdutoExistente.get().setComentario(avaliacaoProduto.getComentario());
            

            return avaliacaoProdutoRepository.save(avaliacaoProdutoExistente.get());
        }


    @Autowired
    private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

}
