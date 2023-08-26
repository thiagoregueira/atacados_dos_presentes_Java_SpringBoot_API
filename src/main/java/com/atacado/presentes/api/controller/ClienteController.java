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

import com.atacado.presentes.api.model.Cliente;
import com.atacado.presentes.api.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    
    @Autowired
    ClienteRepository repository;


    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(paginacao));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = repository.findById(id);

        if (clienteExistente.isPresent()) {
            clienteExistente.get().setNome(cliente.getNome());
            clienteExistente.get().setSobrenome(cliente.getSobrenome());
            clienteExistente.get().setDataNascimento(cliente.getDataNascimento());
            

            return ResponseEntity.status(HttpStatus.OK).body(repository.save(clienteExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarcClientePeloId(@PathVariable Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }

}
