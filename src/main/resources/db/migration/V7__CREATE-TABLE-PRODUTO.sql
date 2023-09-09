CREATE TABLE IF NOT EXISTS tb_produtos(
    idProduto BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    descricao TEXT,
    PRIMARY KEY(idProduto),
    FOREIGN KEY(idFornecedor) REFERENCES tb_fornecedores(idFornecedor),
    FOREIGN KEY(idCategoria) REFERENCES tb_categorias(idCategoria)

);