CREATE TABLE IF NOT EXISTS tb_fonecedores(
    idFornecedor BIGINT NOT NULL AUTO_INCREMENT,
    nomeFantasia VARCHAR(255) NOT NULL,
    razaoSocial VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    PRIMARY KEY(idFornecedor),
    FOREIGN KEY (idUsuario) REFERENCES tb_usuarios(idUsuario)
);