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

	@Override
	public void adiciona(Pessoa pessoa) {
		String sql = ("INSERT INTO pessoa (email) VALUES ('" + pessoa.getEmail() + "');");
		UtilDB.alterarDB(sql);
	}

	@Override
	public void remove(Pessoa pessoa) {
		String sql = ("DELETE FROM pessoa WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
	}
	
	public static void atualizaPessoa(Pessoa pessoa) {
		String sql = ("UPDATE pessoa SET email = '" + pessoa.getEmail() + "' WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
	}
	
	@Override
	public ArrayList<Pessoa> lista() {
		return null;
	}

	@Override
	public Pessoa busca(Pessoa referencia) {
		return null;
	}
	
	public static int buscaID() {
		
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT MAX(id_pessoa) FROM pessoa;");
			rs = pstm.executeQuery();
			
			System.out.println(rs.getInt("MAX(id_pessoa)"));
			return rs.getInt("MAX(id_pessoa)");
		}catch(SQLException e) {
			System.err.println("Erro ao obter id.");
		}
		
		return 0;
	}
	
}
