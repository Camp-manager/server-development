CREATE TABLE pessoa(
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(75) NOT NULL,
    data_nascimento VARCHAR(10) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    peso NUMERIC(3,2) NOT NULL,
    altura NUMERIC(3,2) NOT NULL,
    alimento_predileto VARCHAR(50) NOT NULL,
    foi_batizada VARCHAR(1) NOT NULL,
    tem_primeira_comunhao VARCHAR(1) NOT NULL,

    id_endereco BIGINT NOT NULL,
    id_familiar BIGINT NOT NULL,

    CONSTRAINT fk_pessoa_endereco FOREIGN KEY (id_endereco) REFERENCES endereco(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_pessoa_familiar FOREIGN KEY (id_familiar) REFERENCES familiar(id) ON DELETE RESTRICT ON UPDATE CASCADE
);