CREATE TABLE campista(
    id BIGSERIAL PRIMARY KEY,
    usa_medicamento VARCHAR(1) NOT NULL,
    tem_alergia VARCHAR(1) NOT NULL,
    alergias TEXT,
    ja_fez_acampamento VARCHAR(1) NOT NULL,
    codigo_acampamento VARCHAR(6) NOT NULL,
    acampamentos_feitos TEXT,
    tem_barraca VARCHAR(1) NOT NULL,

    id_camiseta BIGINT,
    id_pessoa BIGINT,
    id_equipe BIGINT,

    CONSTRAINT fk_campista_camiseta FOREIGN KEY (id_camiseta) REFERENCES camiseta(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_campista_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_campista_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE
);