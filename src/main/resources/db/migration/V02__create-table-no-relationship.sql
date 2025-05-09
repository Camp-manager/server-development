CREATE TABLE endereco(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    cep VARCHAR(9) NOT NULL,
    rua TEXT NOT NULL,
    numero TEXT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    ponto_referencia TEXT NOT NULL
);

CREATE TABLE medicamento(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    nome TEXT NOT NULL,
    quantidade TEXT NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    valor NUMERIC(10,2)
);

CREATE TABLE carteirinha(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    texto_apresentacao TEXT NOT NULL
);

CREATE TABLE estoque(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    local VARCHAR(1) NOT NULL,
    quantidade INTEGER NOT NULL,
    limite INTEGER NOT NULL
);

CREATE TABLE tema(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    design TEXT NOT NULL,
    preco_camiseta NUMERIC(10,2),
    preco_acampamento NUMERIC(10,2)
);

CREATE TABLE tipo_acampamento(
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    categoria VARCHAR(1) NOT NULL
);


