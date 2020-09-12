package Kindle.db;

import Kindle.entidade.PessoaFisica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class PessoaFisicaDAO implements InterfaceDAO<PessoaFisica>{

	public void adiciona(PessoaFisica pessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.adiciona(pessoa);
		int id = PessoaDAO.buscaID();
		
		pessoa.setIdPessoa(id);
		
		String sql = ("INSERT INTO pessoa_fisica (id_pessoa, nome, sobrenome, cpf) VALUES (" + pessoa.getIdPessoa() + ", '" + pessoa.getNome() + "',"
				+ "'" + pessoa.getSobrenome() + "', '" + pessoa.getCPF() + "')");
		UtilDB.alterarDB(sql);
	}


	public void remove(PessoaFisica pessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		String sql = ("DELETE FROM pessoa_fisica WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
		pessoaDAO.remove(pessoa);
	}
	
	
	public ArrayList<PessoaFisica> lista() {
		return null;
	}
	
	public static void atualiza(PessoaFisica pessoa, int i) {
		String sql;

		if(i == 1) {
			sql = ("UPDATE pessoa_fisica SET nome = '" + pessoa.getNome()  +"', sobrenome = '" + pessoa.getSobrenome() + "'"
					+ "\nWHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
			UtilDB.alterarDB(sql);
		}else if(i == 2) {
			PessoaDAO.atualizaPessoa(pessoa);
			return;
		}
	}
	
	@SuppressWarnings("null")
	public static int buscaID(PessoaFisica pessoa) {
		
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT id_pessoa FROM pessoa_fisica WHERE cpf = '" + pessoa.getCPF() + "';");
			rs = pstm.executeQuery();
			
			return rs.getInt("id_pessoa");
		}catch(SQLException e) {
			System.err.println("Erro ao obter id.");
		}
		
		return (Integer) null;
	}

	public boolean verificaCPF(String cpf) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = UtilDB.getConexao().prepareStatement("SELECT cpf FROM pessoa_fisica;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("cpf").equals(cpf)) {
					System.err.println("CPF já cadastrado.");
					return true;
				}
			}
		}catch(SQLException e) {
			System.err.println("Erro ao verificar CPF!");
		}
		
		return false;
	}
	
}

















