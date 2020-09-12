package Kindle.entidade;

import java.util.ArrayList;

public class Biblioteca {

	private ArrayList<Livro> livros;
	
	public Biblioteca() {
		this.livros = new ArrayList<Livro>();
	}
	
	public void adicionaLivro(Livro livro) {
		for(int indice = 0; indice < livros.size(); indice++) {
			if(livros.contains(livro)) {
				System.out.println("Este livro já está em sua biblioteca!");
				return;
			}
		}
		
		livros.add(livro);
	}
	
	public void removeLivro(Livro livro) {
		for(int indice = 0; indice < livros.size(); indice++) {
			if(livros.get(indice).getIdLivro() == livro.getIdLivro()) {
				livros.remove(indice);
				System.out.println("Livro removido com sucesso.");
				return;
			}
		}
		
		System.out.println("Erro ao remover livro.");
	}
	
	public ArrayList<Livro> getLivros() {
		return livros;
	}
	
}
