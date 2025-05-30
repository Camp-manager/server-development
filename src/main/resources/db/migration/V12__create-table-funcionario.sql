CREATE TABLE funcionario(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    nome VARCHAR(75) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    habilidade TEXT,

    id_camiseta INTEGER NOT NULL,
    id_equipe INTEGER NOT NULL,
    id_carteirinha INTEGER NOT NULL,

    CONSTRAINT fk_funcionario_camiseta FOREIGN KEY (id_camiseta) REFERENCES camiseta(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_funcionario_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_funcionario_carteirinha FOREIGN KEY (id_carteirinha) REFERENCES carteirinha(id) ON DELETE RESTRICT ON UPDATE CASCADE
);