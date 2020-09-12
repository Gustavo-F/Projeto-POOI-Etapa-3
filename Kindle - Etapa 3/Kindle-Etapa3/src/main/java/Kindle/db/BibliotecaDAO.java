package Kindle.db;

import Kindle.entidade.Biblioteca;
import Kindle.entidade.Usuario;
import Kindle.entidade.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class BibliotecaDAO {

	public static void adicionaLivroBiblioteca(Usuario usuario, Livro livro) {
		String sql = ("INSERT INTO usuario_livro (login_usuario, id_livro) VALUES ('" + usuario.getLogin() + "', " + livro.getIdLivro() + ");");
		UtilDB.alterarDB(sql);
	}
	
	public static void removeLivroBiblioteca(Usuario usuario, int idLivro) {
		String sql = ("DELETE FROM usuario_livro WHERE login_usuario = '" + usuario.getLogin() + "' AND id_livro = " + idLivro + ";");
		UtilDB.alterarDB(sql);
	}
	
	public static Biblioteca buscaBiblioteca(Usuario usuario) {
		
		Biblioteca biblioteca = new Biblioteca();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT l.id_livro, titulo, n_paginas, id_editora\r\n" + 
					"FROM usuario_livro AS ul\r\n" + 
					"INNER JOIN livro AS l ON l.id_livro = ul.id_livro\r\n" + 
					"WHERE login_usuario = '" + usuario.getLogin() + "';");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setIdLivro(rs.getInt("id_livro"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setPaginas(rs.getInt("n_paginas"));
				livro.setEscritores(LivroDAO.listaEscritoresLivro(livro));
				livro.setEditora(EditoraDAO.buscaEditoraPorID(rs.getInt("id_editora")));
				livro.setGeneros(LivroDAO.listaGenerosLivro(livro.getIdLivro()));
				
				biblioteca.adicionaLivro(livro);
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar dados da biblioteca.");
		}
		
		return biblioteca;
	}
	
	public static Livro buscaLivroBiblioteca(Usuario usuario, Livro livro) {
		
		Livro retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT id_livro, titulo, paginas, id_editora \n"
										  + "FROM usuario_livro AS ul \n"
										  + "INNER JOIN livro AS l ON l.id_livro = ul.id_livro \n"
										  + "WHERE id_livro = " + livro.getIdLivro() + " AND login_usuario = '" + usuario.getLogin() +"';");
			rs = pstm.executeQuery();
			
			livro = new Livro();
			
			livro.setIdLivro(rs.getInt("id_livro"));
			livro.setTitulo(rs.getString("titulo"));
			livro.setPaginas(rs.getInt("paginas"));
			livro.setEscritores(LivroDAO.listaEscritoresLivro(livro));
			livro.setGeneros(LivroDAO.listaGenerosLivro(livro.getIdLivro()));
			livro.setEditora(EditoraDAO.buscaEditoraPorID(rs.getInt("id_editora")));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar livro na biblioteca!");
		}
		
		return livro;
	}

}
