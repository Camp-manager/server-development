CREATE TABLE equipe_dia_funcao(
    id BIGSERIAL PRIMARY KEY,
    funcao TEXT NOT NULL,
    data VARCHAR(10) NOT NULL,

    id_equipe BIGINT NOT NULL,

    CONSTRAINT fk_equipe_dia_funcao_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE
);