CREATE TABLE familiar(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(75) NOT NULL,
    parentesco VARCHAR(2) NOT NULL,
    telefone VARCHAR (20) NOT NULL,

    id_endereco BIGINT NOT NULL,

    CONSTRAINT fk_familiar_endereco FOREIGN KEY (id_endereco)REFERENCES endereco(id)ON DELETE RESTRICT ON UPDATE CASCADE
);