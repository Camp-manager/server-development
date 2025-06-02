CREATE TABLE acampamento(
    id BIGSERIAL PRIMARY KEY,
    limite_campistas INTEGER NOT NULL,
    limite_funcionario INTEGER,
    codigo_registro VARCHAR(6) NOT NULL,

    id_tema BIGINT  NOT NULL,
    id_tipo_acampamento BIGINT NOT NULL,
    id_cronograma BIGINT,

    CONSTRAINT fk_acampamento_tema FOREIGN KEY (id_tema) REFERENCES tema(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_acampamento_tipo_acampamento FOREIGN KEY (id_tipo_acampamento) REFERENCES tipo_acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_acampamento_cronograma FOREIGN KEY (id_cronograma) REFERENCES cronograma(id) ON DELETE CASCADE ON UPDATE CASCADE
);