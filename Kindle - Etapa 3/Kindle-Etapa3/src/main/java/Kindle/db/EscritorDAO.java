package Kindle.db;

import Kindle.entidade.Escritor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class EscritorDAO implements InterfaceDAO<Escritor>{


	public void adiciona(Escritor escritor) {
		if(PessoaDAO.verificaEmail(escritor.getEmail()))
			return;
		
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		if(pessoaFisicaDAO.verificaCPF(escritor.getCPF()))
			return;
		
		pessoaFisicaDAO.adiciona(escritor);
		escritor.setIdPessoa(PessoaFisicaDAO.buscaID(escritor));
		
		String sql = ("INSERT INTO escritor (id_pessoa) VALUES ('" + escritor.getIdPessoa() + "');");
		UtilDB.alterarDB(sql);
		
	}


	public void remove(Escritor escritor) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		
		String sql = ("DELETE FROM escritor WHERE id_escritor = " + escritor.getIdEscritor() + ";");
		UtilDB.alterarDB(sql);
		pessoaFisicaDAO.remove(escritor);
	}
	
	public void atualizaEscritor(Escritor escritor, int i) {
		PessoaFisicaDAO.atualiza(escritor, i);
	}


	public ArrayList<Escritor> lista() {
		
		ArrayList<Escritor> escritores = new ArrayList<Escritor>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conexao.prepareStatement("SELECT pf.id_pessoa, id_escritor, nome, sobrenome, cpf, email \nFROM escritor AS es"
					  + "\nINNER JOIN pessoa_fisica AS pf ON pf.id_pessoa = es.id_pessoa"
					  + "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = pf.id_pessoa;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Escritor novoEscritor = new Escritor();
				novoEscritor.setIdPessoa(rs.getInt("id_pessoa"));
				novoEscritor.setIdEscritor(rs.getInt("id_escritor"));
				novoEscritor.setNome(rs.getString("nome"));
				novoEscritor.setSobrenome(rs.getString("sobrenome"));
				novoEscritor.setCPF(rs.getString("cpf"));
				novoEscritor.setEmail(rs.getString("email"));
				
				escritores.add(novoEscritor);
			}
		}catch(SQLException e) {
			System.err.println("Erro ao listar escritores");
		}
		
		return escritores;
	}

	public Escritor buscaEscritor(int idEscritor) {
		
		Escritor retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT pf.id_pessoa, id_escritor, nome, sobrenome, cpf, email \nFROM escritor AS es"
					  + "\nINNER JOIN pessoa_fisica AS pf ON pf.id_pessoa = es.id_pessoa"
					  + "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = pf.id_pessoa"
					  + "\nWHERE id_escritor = " + idEscritor + ";");
			rs = pstm.executeQuery();
			
			retorno = new Escritor();
			retorno.setIdPessoa(rs.getInt("id_pessoa"));
			retorno.setIdEscritor(rs.getInt("id_escritor"));
			retorno.setNome(rs.getString("nome"));
			retorno.setSobrenome(rs.getString("sobrenome"));
			retorno.setCPF(rs.getString("cpf"));
			retorno.setEmail(rs.getString("email"));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar escritor.");
		}
		
		return retorno;
	}

}
