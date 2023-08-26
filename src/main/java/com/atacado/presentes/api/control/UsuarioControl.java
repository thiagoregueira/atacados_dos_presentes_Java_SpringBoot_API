package com.atacado.presentes.api.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.atacado.presentes.api.model.Usuario;
import com.atacado.presentes.api.repository.UsuarioRepository;
import com.atacado.presentes.api.service.UsuarioService;

@RestController
public class UsuarioControl {

    @Autowired
    private UsuarioRepository acao;

    @Autowired
    private UsuarioService servico;

    // cadastrar
    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario obj) {
        return servico.cadastrar(obj);
    }

    // listar todos
    @GetMapping("/api")
    public ResponseEntity<?> listarTodos() {
        return servico.listarTodos();

    }

    // buscar por id
    @GetMapping("/api/{id}")
    public ResponseEntity<?> selecionarPorId(@PathVariable int id) {
        return servico.selecionarPeloId(id);
    }

    // atualizar
    @PutMapping("/api")
    public ResponseEntity<?> atualizar(@RequestBody Usuario obj) {
        return servico.atualizar(obj);
    }

    // remover por id
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> removerPorId(@PathVariable int id) {
        return servico.removerPorId(id);
    }

}
