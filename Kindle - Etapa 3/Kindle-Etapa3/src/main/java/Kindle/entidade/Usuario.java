package Kindle.entidade;

public class Usuario extends PessoaFisica{

	private String login;
	private String senha;
	private String permissao;
	private Biblioteca biblioteca;
	
	public Usuario() {
	}
	
	public Usuario(String login, String senha, String permissao) {
		this.login = login;
		this.senha = senha;
		this.permissao = permissao;
		this.biblioteca = new Biblioteca();
	}
	
	
	
	public Usuario(String nome, String sobrenome, String cpf, int idPessoa, String email, String login, String senha, String permissao) {
		super(nome, sobrenome, cpf, idPessoa, email);
		this.login = login;
		this.senha = senha;
		this.permissao = permissao;
		this.biblioteca = new Biblioteca();
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	public String getPermissao() {
		return permissao;
	}
	
	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}
	
}






























