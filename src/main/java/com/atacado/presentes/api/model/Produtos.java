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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_produtos")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @Column(nullable = false, length = 255)
    private String  nome;

    @Column(nullable = false)
    private double preco;

    @Column(columnDefinition = "TEXT")
    private String descricao;

}