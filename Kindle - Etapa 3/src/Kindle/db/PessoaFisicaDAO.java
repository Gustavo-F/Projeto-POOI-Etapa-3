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

	@Override
	public void adiciona(PessoaFisica pessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.adiciona(pessoa);
		int id = PessoaDAO.buscaID();
		
		pessoa.setIdPessoa(id);
		System.out.println(pessoa.getIdPessoa());
		
		String sql = ("INSERT INTO pessoa_fisica (id_pessoa, nome, sobrenome, cpf) VALUES (" + pessoa.getIdPessoa() + ", '" + pessoa.getNome() + "',"
				+ "'" + pessoa.getSobrenome() + "', '" + pessoa.getCPF() + "')");
		System.out.println(sql);
		UtilDB.alterarDB(sql);
	}

	@Override
	public void remove(PessoaFisica pessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		String sql = ("DELETE FROM pessoa_fisica WHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
		UtilDB.alterarDB(sql);
		pessoaDAO.remove(pessoa);
	}
	
	public static void atualiza(PessoaFisica pessoa, int i) {
		String sql;

		if(i == 1) {
			sql = ("UPDATE pessoa_fisica SET nome = '" + pessoa.getNome()  +"', sobrenome = '" + pessoa.getSobrenome() + "'"
					+ "\nWHERE id_pessoa = " + pessoa.getIdPessoa() + ";");
			UtilDB.alterarDB(sql);
		}else if(i == 2) {
			PessoaDAO.atualizaPessoa(pessoa);
			System.out.println("Pessoa Fisca teste");
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

	@Override
	public ArrayList<PessoaFisica> lista() {
		return null;
	}

	@Override
	public PessoaFisica busca(PessoaFisica referencia) {
		return null;
	}
	
}
