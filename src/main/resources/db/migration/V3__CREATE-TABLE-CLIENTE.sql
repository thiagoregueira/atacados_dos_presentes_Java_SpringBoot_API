CREATE TABLE IF NOT EXISTS tb_clientes(
    idCliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    dataNascimento DATE,
    PRIMARY KEY(idCliente),
    FOREIGN KEY(idUsuario) REFERENCES tb_usuarios(idUsuario)
);