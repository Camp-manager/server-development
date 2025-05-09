CREATE TABLE equipe_dia_funcao(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    funcao TEXT NOT NULL,
    data VARCHAR(10) NOT NULL,

    id_equipe INTEGER NOT NULL,

    CONSTRAINT fk_equipe_dia_funcao_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE
);