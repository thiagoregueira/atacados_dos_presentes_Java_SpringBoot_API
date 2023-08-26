package com.atacado.presentes.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_fornecedores")

public class Fornecedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecedores;

    @Column(nullable = false, length = 255)
    private String nomeFantasia;

    @Column(nullable = false, length = 255)
    private String razaoSocial;

    @Column(nullable = false, length = 18)
    private String cnpj;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String senha;

    @Column(nullable = true, length = 50)
    private String telefone;

    @Column(nullable = true, length = 255)
    private String logradouro;

    @Column(nullable = true, length = 10)
    private String numero;

    @Column(nullable = true, length = 50)
    private String complemento;

    @Column(nullable = true, length = 50)
    private String bairro;

    @Column(nullable = true, length = 50)
    private String cidade;

    @Column(nullable = true, length = 2)
    private String uf;

    @Column(nullable = true, length = 255)
    private String pontoReferencia;

}
