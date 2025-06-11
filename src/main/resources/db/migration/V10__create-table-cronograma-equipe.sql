CREATE TABLE cronograma_equipe(
    id BIGSERIAL PRIMARY KEY,
    data_inicial VARCHAR(10) NOT NULL,
    data_final VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL,

    id_equipe BIGINT,

    CONSTRAINT fk_cronograma_equipe_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE
);