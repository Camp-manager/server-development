CREATE TABLE imagem(
    id BIGSERIAL PRIMARY KEY,
    path_localizacao TEXT NOT NULL,
    data VARCHAR(10),

    id_acampamento BIGINT NOT NULL,

    CONSTRAINT fk_imagem_acampamento FOREIGN KEY (id_acampamento) REFERENCES acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
);