package com.atacado.presentes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atacado.presentes.api.component.Mensagem;
import com.atacado.presentes.api.model.Usuario;
import com.atacado.presentes.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private UsuarioRepository acao;

    public ResponseEntity<?> cadastrar(Usuario obj) {

        if (obj == null) {
            mensagem.setMensagem("O objeto Usuario não pode ser nulo");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        if (obj.getEmail().equals("")) {
            mensagem.setMensagem("O email precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else if (obj.getSenha().equals("")) {
            mensagem.setMensagem("A senha precisa ser preenchida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }

    }

    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloId(int id) {

        if (acao.countByIdUsuario(id) == 0) {
            mensagem.setMensagem("Nenhum usuário encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(acao.findByIdUsuario(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> removerPorId(int id) {

        if (acao.countByIdUsuario(id) == 0) {
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        } else {

            Usuario obj = acao.findByIdUsuario(id);
            acao.delete(obj);

            mensagem.setMensagem("Usuário removido com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> atualizarPeloId(Usuario id) {

        if (acao.countByIdUsuario(id.getIdUsuario()) == 0) {
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        } else if (id.getEmail().equals("")) {
            mensagem.setMensagem("O email precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else if (id.getSenha().equals("")) {
            mensagem.setMensagem("A senha precisa ser preenchida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else {

            Usuario usuarioAtualizado = acao.save(id);
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        }
    }

}
