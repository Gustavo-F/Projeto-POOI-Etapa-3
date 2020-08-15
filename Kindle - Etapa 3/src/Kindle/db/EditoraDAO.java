package Kindle.db;

import java.util.ArrayList;

import Kindle.entidade.Editora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class EditoraDAO implements InterfaceDAO<Editora>{

	@Override
	public void adiciona(Editora editora) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.adiciona(editora);
		editora.setIdPessoa(PessoaDAO.buscaID());
		
		String sql = ("INSERT INTO editora (id_pessoa, razao_social, cnpj) VALUES (" + editora.getIdPessoa() + ", '" + editora.getRazaoSocial() +"',"
				+ "'" + editora.getCnpj() + "');");
		UtilDB.alterarDB(sql);
		
	}
	
	@Override
	public void remove(Editora editora) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		String sql = ("DELETE FROM editora WHERE cnpj = '" + editora.getCnpj() + "';");
		UtilDB.alterarDB(sql);
		pessoaDAO.remove(editora);
	}
	
	public void atualizaEditora(Editora editora, int i) {
		
		String sql = null;		
		if(i == 1){
			sql = ("UPDATE editora SET razao_social = '" + editora.getRazaoSocial() + "' WHERE id_pessoa = " + editora.getIdPessoa() + ";");
			UtilDB.alterarDB(sql);
		}else if(i == 2) {
			PessoaDAO.atualizaPessoa(editora);
		}
		
	}

	@Override
	public ArrayList<Editora> lista() {
		
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			pstm = conexao.prepareStatement("SELECT edi.id_pessoa, razao_social, cnpj, email FROM editora AS edi "
					+ "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = edi.id_pessoa;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Editora editora = new Editora();
				
				editora.setIdPessoa(rs.getInt("id_pessoa"));
				editora.setRazaoSocial(rs.getString("razao_social"));
				editora.setCnpj(rs.getString("cnpj"));
				editora.setEmail(rs.getString("email"));
				
				editoras.add(editora);
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao listar editoras.");
		}
		
		return editoras;
	}
	
	public static Editora buscaEditoraPorRazaoSocial(String razaoSocial) {
		
		Editora editora = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try{
			pstm = conexao.prepareStatement("SELECT editora.id_pessoa, razao_social, cnpj, email FROM editora "
					+ "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = editora.id_pessoa WHERE razao_social = '" + razaoSocial + "';");
			rs = pstm.executeQuery();
			
			editora = new Editora();
			editora.setIdPessoa(rs.getInt("id_pessoa"));
			editora.setRazaoSocial(rs.getString("razao_social"));
			editora.setCnpj(rs.getString("cnpj"));
			editora.setEmail(rs.getString("email"));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar editora.");
		}
		
		return editora;
	}
	
	public static Editora buscaEditoraPorID(int id) {
		
		Editora editora = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try{
			pstm = conexao.prepareStatement("SELECT editora.id_pessoa, razao_social, cnpj, email FROM editora "
					+ "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = editora.id_pessoa WHERE editora.id_pessoa = " + id + ";");
			rs = pstm.executeQuery();
			
			editora = new Editora();
			editora.setIdPessoa(rs.getInt("id_pessoa"));
			editora.setRazaoSocial(rs.getString("razao_social"));
			editora.setCnpj(rs.getString("cnpj"));
			editora.setEmail(rs.getString("email"));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar editora.");
		}
		
		return editora;
	}
	
	@Override
	public Editora busca(Editora referencia) {
		
		
		
		return null;
	}

}




















