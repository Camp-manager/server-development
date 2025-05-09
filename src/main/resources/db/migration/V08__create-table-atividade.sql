CREATE TABLE atividade(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    horario TEXT NOT NULL,
    descricao TEXT,

    id_cronograma INTEGER NOT NULL,

    CONSTRAINT fk_atividade_cronograma FOREIGN KEY (id_cronograma) REFERENCES cronograma(id) ON DELETE RESTRICT ON UPDATE CASCADE
);