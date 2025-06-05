CREATE TABLE funcionario(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(75) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    codigo_acampamento VARCHAR(6) NOT NULL,
    habilidade TEXT,

    id_camiseta BIGINT NOT NULL,
    id_equipe BIGINT NOT NULL,
    id_carteirinha BIGINT NOT NULL,

    CONSTRAINT fk_funcionario_camiseta FOREIGN KEY (id_camiseta) REFERENCES camiseta(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_funcionario_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_funcionario_carteirinha FOREIGN KEY (id_carteirinha) REFERENCES carteirinha(id) ON DELETE RESTRICT ON UPDATE CASCADE
);