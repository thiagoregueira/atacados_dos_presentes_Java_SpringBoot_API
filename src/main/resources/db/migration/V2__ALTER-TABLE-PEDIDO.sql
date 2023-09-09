ALTER TABLE tb_pedidos
    ADD idCliente BIGINT,
    ADD FOREIGN KEY (idCliente) REFERENCES tb_clientes(idCliente)
