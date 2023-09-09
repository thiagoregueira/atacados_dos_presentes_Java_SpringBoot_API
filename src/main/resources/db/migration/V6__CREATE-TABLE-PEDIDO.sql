CREATE TABLE IF NOT EXISTS tb_pedidos(
    idPedido BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    status VARCHAR(50),
    PRIMARY KEY(idPedido)
);