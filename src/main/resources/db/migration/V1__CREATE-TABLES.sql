CREATE TABLE IF NOT EXISTS tb_usuarios(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    perfil VARCHAR(50),
    telefone VARCHAR(50),
    PRIMARY KEY(idUsuario)
);

CREATE TABLE IF NOT EXISTS tb_clientes(
    idCliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    dataNascimento DATE,
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf VARCHAR(2),
    pontoDeReferencia VARCHAR(255),
    idUsuario BIGINT NOT NULL,
    PRIMARY KEY(idCliente),
    FOREIGN KEY(idUsuario) REFERENCES tb_usuarios(idUsuario)
);

CREATE TABLE IF NOT EXISTS tb_fornecedores(
    idFornecedor BIGINT NOT NULL AUTO_INCREMENT,
    nomeFantasia VARCHAR(255) NOT NULL,
    razaoSocial VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf VARCHAR(2),
    pontoDeReferencia VARCHAR(255),
    idUsuario BIGINT NOT NULL,
    PRIMARY KEY(idFornecedor),
    FOREIGN KEY (idUsuario) REFERENCES tb_usuarios(idUsuario)
);

CREATE TABLE IF NOT EXISTS tb_categorias (
    idCategoria BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50),
    descricao TEXT,
    PRIMARY KEY(idCategoria)
);

CREATE TABLE IF NOT EXISTS tb_pedidos(
    idPedido BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    status VARCHAR(50),
    PRIMARY KEY(idPedido)
);

CREATE TABLE IF NOT EXISTS tb_produtos(
    idProduto BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    descricao TEXT,
    idFornecedor BIGINT NOT NULL,
    idCategoria BIGINT NOT NULL,
    PRIMARY KEY(idProduto),
    FOREIGN KEY(idFornecedor) REFERENCES tb_fornecedores(idFornecedor),
    FOREIGN KEY(idCategoria) REFERENCES tb_categorias(idCategoria)
);

CREATE TABLE IF NOT EXISTS tb_itens_pedido(
    idItem BIGINT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    idPedido BIGINT NOT NULL,
    idProduto BIGINT NOT NULL,
    PRIMARY KEY(idItem),
    FOREIGN KEY(idPedido) REFERENCES tb_pedidos(idPedido),
    FOREIGN KEY(idProduto) REFERENCES tb_produtos(idProduto)
);

CREATE TABLE IF NOT EXISTS tb_avaliacoes_produto (
    idAvaliacaoProduto BIGINT NOT NULL AUTO_INCREMENT,
    pontuacao INT(2) NULL,
    comentario VARCHAR(255) NULL,
    idCliente BIGINT NOT NULL,
    idProduto BIGINT NOT NULL,
    PRIMARY KEY (idAvaliacaoProduto),
    FOREIGN KEY (idCliente) REFERENCES tb_clientes(idCliente),
    FOREIGN KEY (idProduto) REFERENCES tb_produtos(idProduto)
);

-- CRIAR tb_produtos_tb_categorias