package Kindle.entidade;

public class Editora extends Pessoa{

	private String razaoSocial;
	private String cnpj;
	
	public Editora() {
	}
	
	public Editora(String razaoSocial, String cnpj, String email) {
		super(email);
		this.razaoSocial = razaoSocial;
		this.cnpj  = cnpj;
	}
	
	public Editora(String razaoSocial, String cnpj, int idPessoa, String email) {
		super(idPessoa, email);
		this.razaoSocial = razaoSocial;
		this.cnpj  = cnpj;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
}
