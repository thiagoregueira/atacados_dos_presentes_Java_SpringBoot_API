package com.atacado.presentes.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atacado.presentes.api.dto.EnderecoDto;
import com.atacado.presentes.api.model.Endereco;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @PostMapping("")
    public ResponseEntity<Endereco> getEndereco(@RequestBody EnderecoDto enderecoDto) {
        RestTemplate restTemplate = new RestTemplate();

        EnderecoDto resposta = restTemplate.getForObject("https://viacep.com.br/ws/" + enderecoDto.getCep() + "/json",
                EnderecoDto.class);

        if (resposta != null) {

            Endereco endereco = new Endereco();
            endereco.setCep(resposta.getCep());
            endereco.setLogradouro(resposta.getLogradouro());
            endereco.setComplemento(resposta.getComplemento());
            endereco.setBairro(resposta.getBairro());
            endereco.setCidade(resposta.getLocalidade());
            endereco.setUf(resposta.getUf());

            return ResponseEntity.ok().body(endereco);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
