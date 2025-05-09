CREATE TABLE item(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    quantidade INTEGER NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    validade VARCHAR(10),
    valor NUMERIC(10,2),

    id_estoque INTEGER NOT NULL,

    CONSTRAINT fk_item_estoque FOREIGN KEY (id_estoque)REFERENCES estoque(id)ON DELETE RESTRICT ON UPDATE CASCADE
);