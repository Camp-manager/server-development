CREATE TABLE pessoa_medicamento_controller(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    id_pessoa INTEGER NOT NULL,
    id_medicamento INTEGER NOT NULL,

    CONSTRAINT fk_pessoa_medicamento_controller_pessoa FOREIGN KEY (id_pessoa)REFERENCES pessoa(id)ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_pessoa_medicamento_controller_medicamento FOREIGN KEY (id_medicamento)REFERENCES medicamento(id)ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE acampamento_estoque_controller(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    id_acampamento INTEGER NOT NULL,
    id_estoque INTEGER NOT NULL,

    CONSTRAINT fk_acampamento_estoque_controller_acampamento FOREIGN KEY (id_acampamento)REFERENCES acampamento(id)ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_acampamento_estoque_controller_estoque FOREIGN KEY (id_estoque)REFERENCES estoque(id)ON DELETE RESTRICT ON UPDATE CASCADE
);