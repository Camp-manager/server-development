CREATE TABLE cronograma(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    data_inicial VARCHAR(10) NOT NULL,
    data_final VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL
);