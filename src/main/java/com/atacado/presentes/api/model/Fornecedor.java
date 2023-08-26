package com.atacado.presentes.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecedor;

    @Column(nullable = false, length = 255)
    private String nomeFantasia;

    @Column(nullable = false, length = 255)
    private String razaoSocial;

    @Column(nullable = false, length = 18)
    @JsonFormat(pattern = "\\d{2}.\\d{3}.\\d{3}/0001-\\d{2}")
    private String cnpj;

}
