CREATE TABLE doacao(
    id BIGSERIAL PRIMARY KEY,
    nome_doador VARCHAR(75) NOT NULL,
    possui_validade VARCHAR(1) NOT NULL,
    data_validade VARCHAR(10),
    tipo VARCHAR(1) NOT NULL,
    descricao TEXT,

    id_estoque BIGINT NOT NULL,

    CONSTRAINT fk_doacao_estoque FOREIGN KEY (id_estoque) REFERENCES estoque(id) ON DELETE RESTRICT ON UPDATE CASCADE
);