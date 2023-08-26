package com.atacado.presentes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atacado.presentes.api.model.ItensDoPedido;
import com.atacado.presentes.api.repository.ItensDoPedidoRepository;

@RestController
@RequestMapping(value = "/itens-pedido")
public class ItensDoPedidoController {

    @Autowired
    private ItensDoPedidoRepository itensDoPedidoRepository;

    @PostMapping
    public ResponseEntity<ItensDoPedido> cadastrarItemDoPedido(@RequestBody ItensDoPedido itensDoPedido) {
        return ResponseEntity.status(HttpStatus.OK).body(itensDoPedidoRepository.save(itensDoPedido));
    }

    @GetMapping
    public ResponseEntity<List<ItensDoPedido>> listarItens() {
        return ResponseEntity.status(HttpStatus.OK).body(itensDoPedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ItensDoPedido>> buscarItemPeloId(@PathVariable("id") Long idItem) {
        return ResponseEntity.status(HttpStatus.OK).body(itensDoPedidoRepository.findById(idItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensDoPedido> atualizarItem(
            @PathVariable("id") Long idItem,
            @RequestBody ItensDoPedido itensdoPedido) {
        Optional<ItensDoPedido> itemExistente = itensDoPedidoRepository.findById(idItem);

        if (itemExistente.isPresent()) {
            itemExistente.get().setQuantidade(itensdoPedido.getQuantidade());
            return ResponseEntity.status(HttpStatus.OK).body(itensDoPedidoRepository.save(itemExistente.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deletarItemPeloId(@PathVariable("id") Long id) {

        Optional<ItensDoPedido> itensDoPedido = itensDoPedidoRepository.findById(id);
        if (itensDoPedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado com Sucesso!");
    }

}
