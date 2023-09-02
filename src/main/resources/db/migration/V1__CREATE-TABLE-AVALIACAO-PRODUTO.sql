CREATE TABLE IF NOT EXISTS tb_avaliacoes_produto (
  idAvaliacaoProduto BIGINT NOT NULL AUTO_INCREMENT,
  pontuacao INT(2) NULL,
  comentario VARCHAR(255) NULL,
  idCliente BIGINT NULL,
  idProduto BIGINT NULL,
  PRIMARY KEY (idAvaliacaoProduto),
  FOREIGN KEY (idCliente) REFERENCES tb_clientes(idCliente),
  FOREIGN KEY (idProduto)  REFERENCES tb_produtos(idProduto)
  );