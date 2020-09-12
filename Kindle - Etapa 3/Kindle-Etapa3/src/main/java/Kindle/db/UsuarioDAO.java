package Kindle.db;

import Kindle.entidade.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class UsuarioDAO implements InterfaceDAO<Usuario>{

	public void adiciona(Usuario usuario) {
		if(PessoaDAO.verificaEmail(usuario.getEmail()))
			return;
		
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		if(pessoaFisicaDAO.verificaCPF(usuario.getCPF()))
			return;
		
		if(verificaLogin(usuario.getLogin()))
			return;
		
		
		
		pessoaFisicaDAO.adiciona(usuario);
		usuario.setIdPessoa(PessoaFisicaDAO.buscaID(usuario));
		
		String sql = ("INSERT INTO usuario (id_pessoa, login, senha, permissao) VALUES ('" + usuario.getIdPessoa() + "',"
				+ "'" + usuario.getLogin() + "', '" + usuario.getSenha() + "', '" + usuario.getPermissao() + "');");
		UtilDB.alterarDB(sql);
		
	}

	public void remove(Usuario usuario) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		
		String sql = ("DELETE FROM usuario WHERE login = '" + usuario.getLogin() + "';");
		UtilDB.alterarDB(sql);
		
		pessoaFisicaDAO.remove(usuario);
	}
	
	public void atualizaUsuario() {
		
	}

	public ArrayList<Usuario> lista() {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setIdPessoa(rs.getInt("id_pessoa"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
			}
			
		}catch(SQLException e) {
			System.err.println("Erro ao listar usuários.");
		}
		
		return usuarios;
	}
	
	public Usuario buscaUsuario(String login) {
		
		Usuario usuario = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT pe.id_pessoa, login, senha, email, permissao, nome, sobrenome, cpf"
					+ "\nFROM usuario AS us"
					+ "\nINNER JOIN pessoa_fisica AS pf ON pf.id_pessoa = us.id_pessoa"
					+ "\nINNER JOIN pessoa AS pe ON pe.id_pessoa = pf.id_pessoa"
					+ "\nWHERE login = '" + login + "';");
			rs = pstm.executeQuery();
			
			usuario = new Usuario();
			usuario.setIdPessoa(rs.getInt("id_pessoa"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setEmail(rs.getString("email"));
			usuario.setPermissao(rs.getString("permissao"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSobrenome(rs.getString("sobrenome"));
			usuario.setCPF(rs.getString("cpf"));
			//usuario.setBiblioteca(BibliotecaDAO.buscaBiblioteca(usuario));
			
		}catch(SQLException e) {
			System.err.println("Erro ao buscar dados do usuario.");
		}
		
		return usuario;
	}
	
	public boolean verificaLogin(String login) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = UtilDB.getConexao().prepareStatement("SELECT login FROM usuario;");
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("login").equals(login)) {
					System.err.println("Este nome de login já está em uso, tente novamente");
					return true;
				}
			}
		}catch(SQLException e) {
			System.err.println("Erro ao verificar login!");
		}
		
		return false;
	}
	
	public int verificaUsuario(String login, String senha) {
		
		Usuario retorno = null;
		Connection conexao = UtilDB.getConexao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = conexao.prepareStatement("SELECT * FROM usuario WHERE login = '" + login + "'");
			rs = pstm.executeQuery();
			
			retorno = new Usuario();
			retorno.setLogin(rs.getString("login"));
			retorno.setSenha(rs.getString("senha"));
			retorno.setPermissao(rs.getString("permissao"));
			
			if(retorno.getSenha().equals(senha) && retorno.getPermissao().equals("U")) {
				return 1;
			}if(retorno.getSenha().equals(senha) && retorno.getPermissao().equals("A")) {
				return 2;
			}else{
				System.out.println("Senha incorrete tente novamente!");
			}
			
		}catch(SQLException e) {
			System.err.println("Usuário inexistente!");
		}
		
		return 0;
	}
	
}
