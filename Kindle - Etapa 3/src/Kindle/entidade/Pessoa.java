package Kindle.entidade;

public class Pessoa {

	private int idPessoa;
	private String email;
	
	public Pessoa() {
	}
	
	public Pessoa(String email) {
		this.email = email;
	}
	
	public Pessoa(int idPessoa, String email) {
		this.email = email;
		this.idPessoa = idPessoa;
	}
	
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
}
