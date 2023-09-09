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
import com.atacado.presentes.api.model.Usuario;
import com.atacado.presentes.api.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientePeloId(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Usuario usuario = cliente.getUsuario();
        cliente.setUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            clienteExistente.get().setNome(cliente.getNome());
            clienteExistente.get().setSobrenome(cliente.getSobrenome());
            clienteExistente.get().setDataNascimento(cliente.getDataNascimento());
            clienteExistente.get().setEndereco(cliente.getEndereco());

            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarcClientePeloId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        clienteRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente deletado com sucesso!");
    }

}
