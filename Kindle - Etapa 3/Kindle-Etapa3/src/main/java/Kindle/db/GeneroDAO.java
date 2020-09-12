package Kindle.db;

import Kindle.entidade.Genero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class GeneroDAO implements InterfaceDAO<Genero>{


	public void adiciona(Genero genero) {
		
		String sql = ("INSERT INTO genero VALUES ('" + genero.getGenero() + "');");
		UtilDB.alterarDB(sql);
		
	}


	public void remove(Genero genero) {
		String sql = ("DELETE FROM genero WHERE genero = '" + genero.getGenero() + "';");
		UtilDB.alterarDB(sql);
	}	


	public ArrayList<Genero> lista() {
		
		ArrayList<Genero> generos = new ArrayList<Genero>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM genero");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Genero genero = new Genero();
				genero.setGenero(rs.getString("genero"));
				
				generos.add(genero);
			}
		}catch(SQLException e) {
			System.err.println("Erro ao listar generos.");
		}
		
		return generos;
	}

	public Genero buscaGenero(String genero) {
		
		Genero retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM genero WHERE genero = '" + genero + "';");
			rs = pstm.executeQuery();
			
			retorno = new Genero();
			retorno.setGenero(rs.getString("genero"));
		}catch(SQLException e) {
			System.err.println("Erro ao buscar genero.");
		}
		
		return retorno;
	}
	
	public void atualizaGenero(Genero genero, String novoGenero) {
		String sql = ("UPDATE genero SET genero = '" + novoGenero + "' WHERE genero = '" + genero.getGenero() + "';");
		UtilDB.alterarDB(sql);
	}
	
}
