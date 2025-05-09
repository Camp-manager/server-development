CREATE TABLE campista(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    usa_medicamento VARCHAR(1) NOT NULL,
    tem_alergia VARCHAR(1) NOT NULL,
    alergias TEXT,
    ja_fez_acampamento VARCHAR(1) NOT NULL,
    acampamentos_feitos TEXT,
    tem_barraca VARCHAR(1) NOT NULL,

    id_camiseta INTEGER NOT NULL,
    id_pessoa INTEGER NOT NULL,
    id_equipe INTEGER NOT NULL,

    CONSTRAINT fk_campista_camiseta FOREIGN KEY (id_camiseta) REFERENCES camiseta(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_campista_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_campista_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE
);