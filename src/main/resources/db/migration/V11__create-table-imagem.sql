CREATE TABLE imagem(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    path_localizacao TEXT NOT NULL,
    data VARCHAR(10),

    id_acampamento INTEGER NOT NULL,

    CONSTRAINT fk_imagem_acampamento FOREIGN KEY (id_acampamento) REFERENCES acampamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
);