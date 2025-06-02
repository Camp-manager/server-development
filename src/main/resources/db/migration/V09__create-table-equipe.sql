CREATE TABLE equipe(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(75) NOT NULL,
    tipo VARCHAR(1) NOT NULL,

    id_cronograma BIGINT NOT NULL,
    id_acampamento BIGINT NOT NULL,

    CONSTRAINT fk_equipe_cronograma FOREIGN KEY (id_cronograma) REFERENCES cronograma(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_equipe_acampamento FOREIGN KEY (id_acampamento) REFERENCES acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
);