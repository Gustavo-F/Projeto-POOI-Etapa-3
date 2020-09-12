package Kindle.db;

import Kindle.entidade.Pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class PessoaDAO implements InterfaceDAO<Pessoa>{


	public void adiciona(Pessoa pessoa) {
		String sql = ("INSERT INTO pessoa (email) VALUES ('" + pessoa.getEmail() + "');");
		UtilDB.alterarDB(sql);
	}


	public void remove(Pessoa pessoa) {
		String sql = ("DELETE FROM pessoa WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
	}
	

	public ArrayList<Pessoa> lista() {
		return null;
	}
	
	public static void atualizaPessoa(Pessoa pessoa) {
		String sql = ("UPDATE pessoa SET email = '" + pessoa.getEmail() + "' WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
	}
	
	public static boolean verificaEmail(String email) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = UtilDB.getConexao().prepareStatement("SELECT email FROM pessoa;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("email").equalsIgnoreCase(email)) {
					System.err.println("Este email já está cadastrado, tente novamente.");
					return true;
				}
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao verificar email!");
		}
		
		return false;
	}
	
	public static int buscaID() {
		
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT MAX(id_pessoa) FROM pessoa;");
			rs = pstm.executeQuery();
			
			return rs.getInt("MAX(id_pessoa)");
		}catch(SQLException e) {
			System.err.println("Erro ao obter id.");
		}
		
		return 0;
	}
	
}
