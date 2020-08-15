package Kindle.entidade;

@SuppressWarnings("unused")
public class PessoaJuridica extends Pessoa{

	private String razaoSocial;
	private String cnpj;
	
	public PessoaJuridica() {
	}
	
	public PessoaJuridica(String razaoSocial, String cnpj, int idPessoa, String email) {
		super(idPessoa, email);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
}
