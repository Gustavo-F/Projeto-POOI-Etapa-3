package Kindle.entidade;

public class Escritor extends PessoaFisica{
	
	private int idEscritor;
	
	public Escritor() {
	}
	
	public Escritor(String nome, String sobrenome, String cpf, int idPessoa, String email) {
		super(nome, sobrenome, cpf, idPessoa, email);
	}
	
	public Escritor(String nome, String sobrenome, String cpf, int idPessoa, String email, int idEscritor) {
		super(nome, sobrenome, cpf, idPessoa, email);
		this.idEscritor = idEscritor;
	}
	
	public void setIdEscritor(int idEscritor) {
		this.idEscritor = idEscritor;
	}
	
	public int getIdEscritor() {
		return idEscritor;
	}
	
}
