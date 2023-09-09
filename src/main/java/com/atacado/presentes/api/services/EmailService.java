package com.atacado.presentes.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.atacado.presentes.api.model.Cliente;
import com.atacado.presentes.api.model.Pedido;
import com.atacado.presentes.api.model.Usuario;
import com.atacado.presentes.api.repository.ClienteRepository;
import com.atacado.presentes.api.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void enviarEmail(String para, String assunto, String conteudo) {
        log.info("Enviando email para notificação de mudança de status...");

        var menssagem = new SimpleMailMessage();

        menssagem.setTo(para);
        menssagem.setSubject(assunto);
        menssagem.setText(conteudo);

        javaMailSender.send(menssagem);
        log.info("Email enviado com sucesso!!");

    }

    public void validarMudancaStatus(Pedido pedido, Optional<Pedido> pedidoCadastrado) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(pedido.getCliente().getIdCliente());

        if (clienteOptional.isPresent()) {
            Optional<Usuario> usuarioOptional = usuarioRepository
                    .findById(clienteOptional.get().getUsuario().getIdUsuario());

            if (usuarioOptional.isPresent()) {
                if (!pedidoCadastrado.get().getStatus().toString().equals(pedido.getStatus().toString())) {
                    enviarEmail(usuarioOptional.get().getEmail(), "Notificação de mudança de status do pedido!",
                            "Obaaa, seu pedido está em " + pedido.getStatus()
                                    + " agora falta pouco para seu pedido chegar!");
                }
            }
        }
    }
}
