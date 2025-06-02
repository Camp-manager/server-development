CREATE TABLE cronograma(
    id BIGSERIAL PRIMARY KEY,
    data_inicial VARCHAR(10) NOT NULL,
    data_final VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL
);