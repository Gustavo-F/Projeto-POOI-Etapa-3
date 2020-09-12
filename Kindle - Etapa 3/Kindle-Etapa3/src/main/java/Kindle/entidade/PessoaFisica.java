package Kindle.entidade;
@SuppressWarnings("unused")
public class PessoaFisica extends Pessoa{

	private String nome;
	private String sobrenome;
	private String cpf;

	public PessoaFisica() {
	}
	
	public PessoaFisica(String nome, String sobrenome, String cpf, String email) {
		super(email);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
	}
	
	public PessoaFisica(String nome, String sobrenome, String cpf, int idPessoa, String email) {
		super(idPessoa, email);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCPF() {
		return cpf;
	}
	
}

































