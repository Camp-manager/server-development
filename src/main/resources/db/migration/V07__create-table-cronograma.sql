CREATE TABLE cronograma(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    data_inicial VARCHAR(10) NOT NULL,
    data_final VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL,

    id_acampamento INTEGER NOT NULL,

    CONSTRAINT fk_cronograma_acampamento FOREIGN KEY (id_acampamento) REFERENCES acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
);