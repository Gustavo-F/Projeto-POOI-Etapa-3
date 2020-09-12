package Kindle.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class UtilDB {

	private static Connection conexao;
	
	private static Connection abrirConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:Kindle.sqlite");
		}catch(SQLException e) {
			System.err.println("Erro ao abrir conexão.");
		}catch(ClassNotFoundException e2) {
			System.err.println("Biblioteca SQLite não está funcionando corretemente.");
		}
		
		return conexao;
	}
	
	public static Connection getConexao() {
		try {
			if(conexao == null)
				abrirConexao();
			
			if(conexao.isClosed())
				abrirConexao();
		}catch(SQLException e) {
			System.err.println("Erro ao estabelecer conexão com o banco de dados.");
		}
		
		return conexao;
	}
	
	public static void fecharConexao() {
		if(conexao == null)
			return;
		
		try {
			if(!conexao.isClosed())
				conexao.close();
		}catch(SQLException e) {
			System.err.println("Erro ao fechar conexão com banco de dados.");
		}
	}
	
	public static void alterarDB(String sql) {
		
		Connection conexao = getConexao();
		Statement stm;
		
		try {
			stm = conexao.createStatement();
			stm.executeUpdate(sql);
			stm.close();
			System.out.println("Ação realizada com sucesso!");
		}catch(SQLException e) {
			System.err.println("Erro ao executar query.");
		}
		
	}
	
	public static void initDB() {
		criaGenero();
		criaPessoa();
		criaPessoaFisica();
		criaEditora();
		criaUsuario();
		criaEscritor();
		criaLivro();
		criaEscritorLivro();
		criaGeneroLivro();
		criaUsuarioLivro();
		registrosIniciais();
	}
	
	private static void criaGenero() {
		String sql = ("DROP TABLE IF EXISTS genero;\n" + 
				"CREATE TABLE genero(\n" + 
				"	genero VARCHAR(50) NOT NULL PRIMARY KEY\n" + 
				");\n" + 
				"");
		alterarDB(sql);
	}
	
	private static void criaPessoa() {
		String sql = ("DROP TABLE IF EXISTS pessoa;\n" + 
				"CREATE TABLE pessoa(\n" + 
				"	id_pessoa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + 
				"	email VARCHAR(100) NOT NULL UNIQUE\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaPessoaFisica() {
		String sql = ("DROP TABLE IF EXISTS pessoa_fisica;\n" + 
				"CREATE TABLE pessoa_fisica(\n" + 
				"	id_pessoa INTEGER NOT NULL,\n" + 
				"	nome VARCHAR(30) NOT NULL,\n" + 
				"	sobrenome VARCHAR(30) NOT NULL,\n" + 
				"	cpf VARCHAR(11) NOT NULL UNIQUE,\n" + 
				"	PRIMARY KEY (id_pessoa),\n" + 
				"	FOREIGN KEY (id_pessoa)\n" + 
				"		REFERENCES pessoa (id_pessoa)\n" + 
				");\n");
		alterarDB(sql);
	}
	
	private static void criaEditora() {
		String sql = ("DROP TABLE IF EXISTS editora;\n" + 
				"CREATE TABLE editora(\n" + 
				"	id_editora INTEGER NOT NULL PRIMARY KEY,\n" + 
				"	razao_social VARCHAR(50) NOT NULL UNIQUE,\n" + 
				"	cnpj VARCHAR(14) NOT NULL UNIQUE,\n" + 
				"	FOREIGN KEY (id_editora)\n" + 
				"		REFERENCES pessoa (id_pessoa)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaUsuario() {
		String sql = ("DROP TABLE IF EXISTS usuario;\n" + 
				"CREATE TABLE usuario(\n" + 
				"	id_pessoa INTEGER NOT NULL,\n" + 
				"	login VARCHAR(30) NOT NULL UNIQUE,\n" + 
				"	senha VARCHAR(30) NOT NULL,\n" + 
				"	permissao CHAR NOT NULL,\n" + 
				"	PRIMARY KEY (login)\n" + 
				"	FOREIGN KEY (id_pessoa)\n" + 
				"		REFERENCES pessoa_fisica (id_pessoa)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaEscritor() {
		String sql = ("DROP TABLE IF EXISTS escritor;\n" + 
				"CREATE TABLE escritor(\n" + 
				"	id_pessoa INTEGER NOT NULL,\n" + 
				"	id_escritor INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + 
				"	FOREIGN KEY (id_pessoa)\n" + 
				"		REFERENCES pessoa_fisica (id_pessoa)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaLivro() {
		String sql = ("DROP TABLE IF EXISTS livro;\n" + 
				"CREATE TABLE livro(\n" + 
				"	id_livro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" + 
				"	titulo VARCHAR(50) NOT NULL UNIQUE,\n" + 
				"	n_paginas INTEGER,\n" + 
				"	id_editora INTEGER NOT NULL,\n" + 
				"	FOREIGN KEY (id_editora)\n" + 
				"		REFERENCES editora (id_editora)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaEscritorLivro() {
		String sql = ("DROP TABLE IF EXISTS escritor_livro;\n" + 
				"CREATE TABLE escritor_livro(\n" + 
				"	id_escritor INTEGER NOT NULL,\n" + 
				"	id_livro INTEGER NOT NULL,\n" + 
				"	PRIMARY KEY (id_escritor, id_livro),\n" + 
				"	FOREIGN KEY (id_escritor)\n" + 
				"		REFERENCES escritor	(id_escritor),\n" + 
				"	FOREIGN KEY (id_livro)\n" + 
				"		REFERENCES livro (id_livro)\n" + 
				"	\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaGeneroLivro() {
		String sql = ("DROP TABLE IF EXISTS genero_livro;\n" + 
				"CREATE TABLE genero_livro(\n" + 
				"	genero_nome VARCHAR(50) NOT NULL,\n" + 
				"	id_livro INTEGER NOT NULL,\n" + 
				"	PRIMARY KEY (genero_nome, id_livro),\n" + 
				"	FOREIGN KEY (genero_nome)\n" + 
				"		REFERENCES genero (genero),\n" + 
				"	FOREIGN KEY (id_livro)\n" + 
				"		REFERENCES livro (id_livro)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void criaUsuarioLivro() {
		String sql = ("DROP TABLE IF EXISTS usuario_livro;\n" + 
				"CREATE TABLE usuario_livro(\n" + 
				"	login_usuario VARCHAR(30) NOT NULL,\n" + 
				"	id_livro INTEGER NOT NULL,\n" + 
				"	PRIMARY KEY (login_usuario, id_livro),\n" + 
				"	FOREIGN KEY (login_usuario)\n" + 
				"		REFERENCES usuario (login),\n" + 
				"	FOREIGN KEY (id_livro)\n" + 
				"		REFERENCES livro (id_livro)\n" + 
				");");
		alterarDB(sql);
	}
	
	private static void registrosIniciais() {
		String sql = ("INSERT INTO genero VALUES\n" + 
				"('Ação'), ('Suspence'), ('Romance');\n" + 
				"\n" + 
				"INSERT INTO pessoa (email) VALUES\n" + 
				"('kindle@admin'), ('contato@saraiva.com.br'), ('mariaalice@hotmail.com'), ('marcelo@gmail.com'),\n" + 
				"('contato@editorapearson.com'), ('genival@hotmail.com');\n" + 
				"\n" + 
				"INSERT INTO editora (id_editora, razao_social, cnpj) VALUES\n" + 
				"(2, 'Saraiva', '14785236912345'),\n" + 
				"(5, 'Pearson', '85236915935748');\n" + 
				"\n" + 
				"INSERT INTO pessoa_fisica (id_pessoa, nome, sobrenome, cpf) VALUES \n" + 
				"(1, 'Admin', 'admin', '00000000000'),\n" + 
				"(3, 'Maria', 'Alice', '15935741815'),\n" + 
				"(4, 'Marcelo', 'Ribeiro', '02204409901'), (6, 'Genival', 'Perereira', '08807706600');\n" + 
				"\n" + 
				"INSERT INTO usuario (id_pessoa, login, senha, permissao) VALUES\n" + 
				"(1, 'admin', 'admin', 'A'),\n" + 
				"(3, 'maria', 'senha123', 'U');\n" + 
				"\n" + 
				"INSERT INTO escritor (id_pessoa) VALUES\n" + 
				"(4), (6);\n" + 
				"\n" + 
				"INSERT INTO livro (titulo, n_paginas, id_editora) VALUES \n" + 
				"('Estrelas', 280, 5),\n" + 
				"('Céu', 788, 2),\n" + 
				"('Aurora', 666, 2);\n" + 
				"\n" + 
				"INSERT INTO escritor_livro VALUES\n" + 
				"(1, 1), (2, 1), (2, 2);");
		alterarDB(sql);
	}
	
}


























