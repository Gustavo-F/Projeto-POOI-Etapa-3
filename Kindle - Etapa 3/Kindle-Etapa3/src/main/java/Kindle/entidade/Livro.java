package Kindle.entidade;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Livro {

	private int idLivro;
	private String titulo;
	private ArrayList<Escritor> escritores;
	private ArrayList<Genero> generos;
	private Editora editora;
	private int paginas;
	
	public Livro() {
	}
	
	public Livro(int idLivro, String titulo) {
		this.idLivro = idLivro;
		this.titulo = titulo;
		this.escritores = new ArrayList<Escritor>();
		this.generos = new ArrayList<Genero>();
		this.paginas = 0;
	}
	
	public Livro(String titulo, Editora editora, int paginas) {
		this.titulo = titulo;
		this.editora = editora;
		this.paginas = paginas;
	}
	
	public Livro(int idLivro, String titulo, int paginas, Editora editora) {
		this.idLivro = idLivro;
		this.titulo = titulo;
		this.escritores = new ArrayList<Escritor>();
		this.generos = new ArrayList<Genero>();
		this.editora = editora;
		this.paginas = paginas;
	}
	
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	
	public int getIdLivro() {
		return idLivro;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setEscritores(ArrayList<Escritor> escritores) {
		this.escritores = escritores;
	}
	
	public void adicionaEscritor(Escritor escritor) {
		for(int indice = 0; indice < escritores.size(); indice++) {
			if(escritores.get(indice).getIdPessoa() == escritor.getIdPessoa()) {
				return;
			}	
		}
		
		escritores.add(escritor);
	}
	
	public void removeEscritor(Escritor escritor) {
		for(int indice = 0; indice < escritores.size(); indice++) {
			if(escritores.get(indice).getIdPessoa() == escritor.getIdPessoa()) {
				escritores.remove(indice);
				System.out.println("Escritor removido com sucesso.");
				return;
			}
		}
		
		System.out.println("Escritor não atribuido à este livro.");
	}
	
	public ArrayList<Escritor> getEscritores(){
		return escritores;
	}
	
	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}
	
	public void adicionaGenero(Genero genero) {
		for(int indice = 0; indice < generos.size(); indice++) {
			if(generos.contains(genero)) {
				System.out.println("Este livro já possui este genero.");
				return;
			}
		}
		
		generos.add(genero);
	}
	
	public void removeGenero(Genero genero) {
		for(int indice = 0; indice < generos.size(); indice++) {
			if(generos.contains(genero)) {
				generos.remove(genero);
				System.out.println("Genero removido com sucesso.");
				return;
			}
		}
		
		System.out.println("Genero não atribuido à este livro.");
	}
	
	public ArrayList<Genero> getGeneros(){
		return generos;
	}
	
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public Editora getEditora() {
		return editora;
	}
	
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
	public int getPaginas() {
		return paginas;
	}
	
	
	
}























