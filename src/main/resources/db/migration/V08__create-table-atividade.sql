CREATE TABLE atividade(
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(1) NOT NULL,
    horario TEXT NOT NULL,
    descricao TEXT,

    id_cronograma BIGINT NOT NULL,

    CONSTRAINT fk_atividade_cronograma FOREIGN KEY (id_cronograma) REFERENCES cronograma(id) ON DELETE RESTRICT ON UPDATE CASCADE
);