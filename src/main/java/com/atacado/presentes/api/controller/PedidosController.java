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

import com.atacado.presentes.api.model.Pedidos;
import com.atacado.presentes.api.repository.PedidosRepository;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosController {

    //Cadastrar tarefa
    @PostMapping
    public ResponseEntity<Pedidos> cadastrarPedidos(@RequestBody Pedidos pedidos){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosRepository.save(pedidos));
    }

    //Listar todos
    @GetMapping
    public ResponseEntity<Page<Pedidos>> listarPedidos(Pageable paginacao){
        return ResponseEntity.status(HttpStatus.OK).body(pedidosRepository.findAll(paginacao));
    }

    //Listar pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> buscarPedidoPeloId(@PathVariable("id") Long id){
    Optional<Pedidos> pedido = pedidosRepository.findById(id);
    if(pedido.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }

    //Atualizar Pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> atualizarPedidos(@PathVariable("id") Long id, @RequestBody Pedidos pedidos){
        Optional<Pedidos> pedidosCadastrado = pedidosRepository.findById(id);

            if(pedidosCadastrado.isPresent()){
                pedidosCadastrado.get().setData(pedidos.getData());
                pedidosCadastrado.get().setStatus(pedidos.getStatus());
                return ResponseEntity.status(HttpStatus.OK).body(pedidosRepository.save(pedidosCadastrado.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Excluir Pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedidoPeloId(@PathVariable("id") Long id){
        Optional<Pedidos> pedidosCadastrado = pedidosRepository.findById(id);

        if(pedidosCadastrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Pedido excluido com sucesso!");
    }

    @Autowired
    private PedidosRepository pedidosRepository;
}
