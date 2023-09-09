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

import com.atacado.presentes.api.model.Pedido;
import com.atacado.presentes.api.repository.PedidoRepository;
import com.atacado.presentes.api.services.EmailService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> listarPedido(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPeloId(@PathVariable("id") Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        Optional<Pedido> pedidoCadastrado = pedidoRepository.findById(id);

        if (pedidoCadastrado.isPresent()) {
            emailService.validarMudancaStatus(pedido, pedidoCadastrado);

            pedidoCadastrado.get().setData(pedido.getData());
            pedidoCadastrado.get().setStatus(pedido.getStatus());

            return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.save(pedidoCadastrado.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedidoPeloId(@PathVariable("id") Long id) {
        Optional<Pedido> pedidoCadastrado = pedidoRepository.findById(id);

        if (pedidoCadastrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        pedidoRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pedido excluido com sucesso!");
    }

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmailService emailService;

}
