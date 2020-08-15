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
	
}


























