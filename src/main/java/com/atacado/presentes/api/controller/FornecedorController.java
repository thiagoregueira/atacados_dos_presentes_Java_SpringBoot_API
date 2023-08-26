package com.atacado.presentes.api.controller;

import java.util.List;
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

import com.atacado.presentes.api.model.Fornecedor;
import com.atacado.presentes.api.repository.FornecedorRepository;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController {

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorRepository.save(fornecedor));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarFornecedores(Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarFornecedorPeloId(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(fornecedor.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable("id") Long id,
            @RequestBody Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorExistente = fornecedorRepository.findById(id);

        if (fornecedorExistente.isPresent()) {
            fornecedorExistente.get().setNomeFantasia(fornecedor.getNomeFantasia());
            fornecedorExistente.get().setRazaoSocial(fornecedor.getRazaoSocial());
            fornecedorExistente.get().setCnpj(fornecedor.getCnpj());

            return ResponseEntity.status(HttpStatus.OK).body(fornecedorRepository.save(fornecedorExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFornecedorPeloId(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

        if (fornecedor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        fornecedorRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deletado com sucesso!");
    }

    @Autowired
    private FornecedorRepository fornecedorRepository;
}
