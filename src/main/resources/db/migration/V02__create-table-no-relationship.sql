CREATE TABLE endereco(
    id BIGSERIAL PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    rua TEXT NOT NULL,
    numero TEXT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    ponto_referencia TEXT NOT NULL
);

CREATE TABLE medicamento(
    id BIGSERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    quantidade TEXT NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    valor NUMERIC(10,2)
);

CREATE TABLE carteirinha(
    id BIGSERIAL PRIMARY KEY,
    texto_apresentacao TEXT NOT NULL
);

CREATE TABLE estoque(
    id BIGSERIAL PRIMARY KEY,
    local VARCHAR(1) NOT NULL,
    quantidade INTEGER NOT NULL,
    limite INTEGER NOT NULL
);

CREATE TABLE tema(
    id BIGSERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    design TEXT NOT NULL,
    preco_camiseta NUMERIC(10,2),
    preco_acampamento NUMERIC(10,2)
);

CREATE TABLE tipo_acampamento(
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    categoria VARCHAR(1) NOT NULL
);


