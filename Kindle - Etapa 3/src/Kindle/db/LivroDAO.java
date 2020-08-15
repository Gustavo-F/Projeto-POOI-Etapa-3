package Kindle.db;

import java.util.ArrayList;

import Kindle.entidade.Livro;
import Kindle.entidade.Escritor;
import Kindle.entidade.Genero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class LivroDAO implements InterfaceDAO<Livro>{

	@Override
	public void adiciona(Livro livro) {
		
		String sql = ("INSERT INTO livro (titulo, n_paginas, id_editora) VALUES ('" + livro.getTitulo() + "', " + livro.getPaginas() + ", "
				+ "" + livro.getEditora().getIdPessoa() + ");");
		UtilDB.alterarDB(sql);
	}
	
	@Override
	public void remove(Livro livro) {
		
		String sql = ("DELETE FROM escritor_livro WHERE id_livro = " + livro.getIdLivro()  + ";");
		UtilDB.alterarDB(sql);
		
		sql = ("DELETE FROM genero_livro WHERE id_livro = " + livro.getIdLivro() + ";");
		UtilDB.alterarDB(sql);
		
		sql = ("DELETE FROM livro WHERE id_livro = " + livro.getIdLivro() + ";");
		UtilDB.alterarDB(sql);
	}
	
	public void atualizaLivro(Livro livro, int i) {
		String sql = null;
		
		if(i == 1) {
			sql = ("UPDATE livro SET titulo = '" + livro.getTitulo() + "' WHERE id_livro = " + livro.getIdLivro() + ";");
		}else if(i == 2) {
			sql = ("UPDATE livro SET n_paginas = " + livro.getPaginas() + " WHERE id_livro = " + livro.getIdLivro() + ";");
		}else if(i == 3) {
			sql = ("UPDATE livro SET id_editora = " + livro.getEditora().getIdPessoa() + " WHERE id_livro = " + livro.getIdLivro() + ";");
		}else {
			return;
		}
		
		System.out.println(sql);
		UtilDB.alterarDB(sql);
	}
	
	public void adicionaEscritor(Livro livro, Escritor escritor) {
		String sql = ("INSERT INTO escritor_livro (id_escritor, id_livro) VALUES (" + escritor.getIdEscritor() + ", " + livro.getIdLivro() + ");");
		UtilDB.alterarDB(sql);
	}
	
	public void removeEscritor(Livro livro, Escritor escritor) {
		String sql = ("DELETE FROM escritor_livro WHERE id_escritor = " + escritor.getIdEscritor() + " AND id_livro = " + livro.getIdLivro() + ";");
		UtilDB.alterarDB(sql);
	}
	
	public static ArrayList<Escritor> listaEscritoresLivro(Livro livro) {
		
		ArrayList<Escritor> escritores = new ArrayList<Escritor>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT pf.id_pessoa, es.id_escritor, nome, sobrenome, cpf, email"
					+ "\nFROM escritor_livro AS el"
					+ "\nINNER JOIN escritor AS es ON es.id_escritor = el.id_escritor"
					+ "\nINNER JOIN pessoa_fisica AS pf ON pf.id_pessoa = es.id_pessoa"
					+ "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = pf.id_pessoa"
					+ "\nWHERE id_livro = " + livro.getIdLivro() + ";");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Escritor escritor = new Escritor();
				escritor.setIdPessoa(rs.getInt("id_pessoa"));
				escritor.setIdEscritor(rs.getInt("id_escritor"));
				escritor.setNome(rs.getString("nome"));
				escritor.setSobrenome(rs.getString("sobrenome"));
				escritor.setCPF(rs.getString("cpf"));
				escritor.setEmail(rs.getString("email"));
				
				escritores.add(escritor);	
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar escritores do livro.");
		}
		
		return escritores;
	}
	
	public void adicionaGenero(Livro livro, Genero genero) {
		String sql = ("INSERT INTO genero_livro (genero_nome, id_livro) VALUES ('" + genero.getGenero() + "', " + livro.getIdLivro() + ");");
		UtilDB.alterarDB(sql);
	}
	
	public void removeGenero(Livro livro, Genero genero) {
		String sql = ("DELETE FROM genero_livro WHERE genero_nome = '" +genero.getGenero() + "' AND id_livro = " +livro.getIdLivro() + ";");
		UtilDB.alterarDB(sql);
	}
	
	public static ArrayList<Genero> listaGenerosLivro(int id_livro) {
		
		ArrayList<Genero> generos = new ArrayList<Genero>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			pstm = conexao.prepareStatement("SELECT genero_nome FROM genero_livro WHERE id_livro = " + id_livro + ";");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Genero genero = new Genero();
				genero.setGenero(rs.getString("genero_nome"));
				generos.add(genero);
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar generos do livro.");
		}
		
		return generos;
	}

	public static int getID(Livro livro) {
		
		int id = 0;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT id_livro FROM livro WHERE titulo = '" + livro.getTitulo() + "';");
			rs = pstm.executeQuery();
			
			id =  rs.getInt("id_livro");
			
		}catch(SQLException e) {
			System.err.println("Erro ao obter ID do livro.");
		}
		
		return id;
	}

	@Override
	public ArrayList<Livro> lista() {
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM livro");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Livro livro = new Livro();
				livro.setIdLivro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setEscritores(listaEscritoresLivro(livro));
				livro.setGeneros(listaGenerosLivro(livro.getIdLivro()));
				livro.setEditora(EditoraDAO.buscaEditoraPorID(rs.getInt("id_editora")));
				livro.setPaginas(rs.getInt("n_paginas"));
				
				livros.add(livro);
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao listar livros.");
		}
		
		return livros;
	}

	@Override
	public Livro busca(Livro livro) {
		
		Livro retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM livro WHERE id_livro = " + livro.getIdLivro() + " OR titulo = '" + livro.getTitulo() + "';");
			rs = pstm.executeQuery();
			
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar livro.");
		}
		
		return retorno;
	}
	
	
	public static Livro buscaPorTitulo(String titulo) {
		
		Livro retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM livro WHERE titulo = '" + titulo + "';");
			rs = pstm.executeQuery();
			
			retorno = new Livro();
			retorno.setIdLivro(rs.getInt("id_livro"));
			retorno.setTitulo(rs.getString("titulo"));
			retorno.setEscritores(listaEscritoresLivro(retorno));
			retorno.setGeneros(listaGenerosLivro(getID(retorno)));
			retorno.setEditora(EditoraDAO.buscaEditoraPorID(rs.getInt("id_editora")));
			retorno.setPaginas(rs.getInt("n_paginas"));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar livro.");
		}
		
		return retorno;
	}

}


























