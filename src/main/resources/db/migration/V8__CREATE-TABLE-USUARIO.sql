CREATE TABLE IF NOT EXISTS tb_usuarios(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    perfil VARCHAR,
    telefone VARCHAR(50),
    PRIMARY KEY(idUsuario)
);