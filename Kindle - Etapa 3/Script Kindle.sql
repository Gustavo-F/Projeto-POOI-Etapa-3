DROP TABLE IF EXISTS genero;
CREATE TABLE genero(
	genero VARCHAR(50) NOT NULL PRIMARY KEY
);

DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa(
	id_pessoa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	email VARCHAR(100) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS pessoa_fisica;
CREATE TABLE pessoa_fisica(
	id_pessoa INTEGER NOT NULL,
	nome VARCHAR(30) NOT NULL,
	sobrenome VARCHAR(30) NOT NULL,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	PRIMARY KEY (cpf),
	FOREIGN KEY (id_pessoa)
		REFERENCES pessoa (id_pessoa)
);

DROP TABLE IF EXISTS editora;
CREATE TABLE editora(
	id_pessoa INTEGER NOT NULL,
	razao_social VARCHAR(50) NOT NULL UNIQUE,
	cnpj VARCHAR(14) NOT NULL UNIQUE,
	PRIMARY KEY (cnpj),
	FOREIGN KEY (id_pessoa)
		REFERENCES pessoa (id_pessoa)
);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
	id_pessoa INTEGER NOT NULL,
	login VARCHAR(30) NOT NULL UNIQUE,
	senha VARCHAR(30) NOT NULL,
	permissao CHAR NOT NULL,
	PRIMARY KEY (login)
	FOREIGN KEY (id_pessoa)
		REFERENCES pessoa_fisica (id_pessoa)
);

	DROP TABLE IF EXISTS escritor;
	CREATE TABLE escritor(
		id_pessoa INTEGER NOT NULL UNIQUE,
		id_escritor INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
		FOREIGN KEY (id_pessoa)
			REFERENCES pessoa_fisica (id_pessoa)
	);

DROP TABLE IF EXISTS livro;
CREATE TABLE livro(
	id_livro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	titulo VARCHAR(50) NOT NULL UNIQUE,
	n_paginas INTEGER,
	id_editora INTEGER NOT NULL,
	FOREIGN KEY (id_editora)
		REFERENCES editora (id_pessoa)
);

DROP TABLE IF EXISTS escritor_livro;
CREATE TABLE escritor_livro(
	id_escritor INTEGER NOT NULL,
	id_livro INTEGER NOT NULL,
	PRIMARY KEY (id_escritor, id_livro),
	FOREIGN KEY (id_escritor)
		REFERENCES escritor	(id_escritor),
	FOREIGN KEY (id_livro)
		REFERENCES livro (id_livro)
	
);

DROP TABLE IF EXISTS genero_livro;
CREATE TABLE genero_livro(
	genero_nome VARCHAR(50) NOT NULL,
	id_livro INTEGER NOT NULL,
	PRIMARY KEY (genero_nome, id_livro),
	FOREIGN KEY (genero_nome)
		REFERENCES genero (genero),
	FOREIGN KEY (id_livro)
		REFERENCES livro (id_livro)
);

DROP TABLE IF EXISTS usuario_livro;
CREATE TABLE usuario_livro(
	login_usuario VARCHAR(30) NOT NULL,
	id_livro INTEGER NOT NULL,
	PRIMARY KEY (login_usuario, id_livro),
	FOREIGN KEY (login_usuario)
		REFERENCES usuario (login),
	FOREIGN KEY (id_livro)
		REFERENCES livro (id_livro)
);