CREATE TABLE IF NOT EXISTS tb_itens_pedido(
    idItem BIGINT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    PRIMARY KEY(idItem),
    FOREIGN KEY(idPedido) REFERENCES tb_pedidos(idPedido),
    FOREIGN KEY(idProduto) REFERENCES tb_produtos(idProduto)
);