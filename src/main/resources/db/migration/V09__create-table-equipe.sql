CREATE TABLE equipe(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(75) NOT NULL,
    tipo VARCHAR(1) NOT NULL,

    id_acampamento BIGINT NOT NULL,

    CONSTRAINT fk_equipe_acampamento FOREIGN KEY (id_acampamento) REFERENCES acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
);