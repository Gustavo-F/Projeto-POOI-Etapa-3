// Desenvolvido por: Gustavo Ferreira Souza
// 3º etapa do projeto de POO I
package Kindle;

import Kindle.entidade.*;
import Kindle.db.*;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {

	@SuppressWarnings({ "resource", "static-access" })
	public static void main(String[] args) {
		
		
		Scanner leString = new Scanner(System.in);
		Scanner leInt = new Scanner(System.in);

		Usuario novoUsuario;
		Usuario usuarioLogado = null;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		EscritorDAO escritorDAO = new EscritorDAO();
		EditoraDAO editoraDAO = new EditoraDAO();
		LivroDAO livroDAO = new LivroDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
		
		ArrayList<Escritor> escritores = new ArrayList<Escritor>();
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ArrayList<Genero> generos = new ArrayList<Genero>();
		
		int loopInicio = 1 , id, indice, paginas;
		String login, senha, nome, sobrenome, cpf, email, razaoSocial, cnpj, titulo, genero, escolha;
		
		while(loopInicio == 1) {
			System.out.println("\n1 - Fazer login");
			System.out.println("2 - Criar conta");
			System.out.println("3 - Fazer login com conta administrativa");
			System.out.println("4 - Criar conta administrativa");
			System.out.println("5 - Encerrar o programa");
			
			System.out.println("Informe a opção escolhida:");
			int menuInicio = leInt.nextInt();
			
			switch(menuInicio) {
			case 1:
				
				System.out.println("Login:");
				login = leString.next();
				
				System.out.println("Senha:");
				senha = leString.next();
				
				if(usuarioDAO.verificaUsuario(login, senha) == 1) {
					
					usuarioLogado = usuarioDAO.buscaUsuario(login);
					
					int loopCliente = 1;
					
					while(loopCliente == 1) {
						//Inicio do while de cliente
						
						System.out.println("\n1 - Acessar biblioteca");
						System.out.println("2 - Buscar Livro");
						System.out.println("3 - Sair");
					
						System.out.println("Opção escolhida: ");
						int menuCliente = leInt.nextInt();
						
						switch(menuCliente) {
						case 1:
							
							int loopBiblioteca = 1;
							
							while(loopBiblioteca == 1) {
								// Inicio while biblioteca
								
								System.out.println("\n1 - Adicionar livro à biblioteca");
								System.out.println("2 - Buscar livro já adicionado");
								System.out.println("3 - Listar todos os livros da biblioteca");
								System.out.println("4 - Remover livro da biblioteca");
								System.out.println("5 - Voltar");
								
								System.out.println("Opção escolhida: ");
								int menuBiblioteca = leInt.nextInt();
								
								switch(menuBiblioteca) {
								
								case 1:
									
									System.out.println("Titulo do livro: ");
									titulo = leString.next();
									
									System.out.println(usuarioLogado.getLogin());
									System.out.println(livroDAO.getID(livroDAO.buscaPorTitulo(titulo)));
									bibliotecaDAO.adicionaLivroBiblioteca(usuarioLogado, livroDAO.buscaPorTitulo(titulo));
									
									break;
									
								case 2:
									
									break;
									
								case 3:
									
									Biblioteca teste = new Biblioteca();
									
									if(bibliotecaDAO.buscaBiblioteca(usuarioLogado).getLivros().size() == 0) {
										System.out.println("Biblioteca vazia.");
										break;
									}
									
									for(indice = 0; indice < bibliotecaDAO.buscaBiblioteca(usuarioLogado).getLivros().size(); indice++) {
										System.out.println("Titulo: " + bibliotecaDAO.buscaBiblioteca(usuarioLogado).getLivros().get(indice).getTitulo());
									}
									
									break;
									
								case 4:
									
									System.out.println("Titulo do livro:");
									titulo = leString.next();
									
									bibliotecaDAO.removeLivroBiblioteca(usuarioLogado, livroDAO.getID(livroDAO.buscaPorTitulo(titulo)));
									
									break;
									
								case 5:
									
									break;
								
								}
								
								// Fim while biblioteca
							}
							
							break;
							
						case 2:
							
							System.out.println("Titulo: ");
							titulo = leString.next();
							
							Livro livro = livroDAO.buscaPorTitulo(titulo);
							
							if(livro.getTitulo() == null) {
								System.out.println("Livro não encontrado.");
								break;
							}
							
							System.out.println("ID do livro: " + livro.getIdLivro());
							System.out.println("Titulo : " + livro.getTitulo());
							
							if(livro.getEscritores().size() > 0) {
								System.out.println("Escritores:");
								for(indice = 0; indice < livro.getEscritores().size(); indice++) {
									System.out.println(livro.getEscritores().get(indice).getNome());
								}
							}
							
							if(livro.getGeneros().size() > 0) {
								System.out.println("Generos:");
								for(indice = 0; indice < livro.getGeneros().size(); indice++) {
									System.out.println(livro.getGeneros().get(indice).getGenero());
								}
							}
							
							System.out.println("Editora: " + livro.getEditora().getRazaoSocial());
							System.out.println("Paginas: " + livro.getPaginas());
												
							System.out.println("\nAdicionar livro à biblioteca? (S/N)");
							escolha = leString.next();
							
							if(escolha.equalsIgnoreCase("S")) {
								bibliotecaDAO.adicionaLivroBiblioteca(usuarioLogado, livro);
							}
							
							break;
							
						case 3:
							
							System.out.println("Deseja realmente sair? (S/N)");
							escolha = leString.next();
							
							if(escolha.equalsIgnoreCase("S"))
								loopCliente = 0;
							
							break;
							
						default:
							
							break;
						}
						
						//Fim do while de cliente
					}
					
				}
				
				break;
				
			case 2:
				
				System.out.println("Nome:");
				nome = leString.next();
				
				System.out.println("Sobrenome:");
				sobrenome = leString.next();
				
				System.out.println("CPF:");
				cpf = leString.next();
				
				System.out.println("Email:");
				email = leString.next();
				
				System.out.println("Login:");
				login = leString.next();
				
				System.out.println("Senha:");
				senha = leString.next();
				
				novoUsuario = new Usuario(nome, sobrenome, cpf, 0, email, login, senha, "U");
				
				usuarioDAO.adiciona(novoUsuario);
				
				break;
				
			case 3:
				
				System.out.println("Login:");
				login = leString.next();
				
				System.out.println("Senha:");
				senha = leString.next();
				
				if(usuarioDAO.verificaUsuario(login, senha) == 2) {
					
					int loopADM= 1;
					
					while(loopADM== 1) {
						// Inicio while de ADM
						
						System.out.println("\n1 - Menu de livros");
						System.out.println("2 - Menu dos escritores");
						System.out.println("3 - Menu das editoras");
						System.out.println("4 - Menu de generos");
						System.out.println("5 - Sair");
						
						System.out.println("Opção escolhida:");
						int menuADM = leInt.nextInt();
						
						switch(menuADM) {
						
						case 1:
							
							int loopLivro = 1;
							
							while(loopLivro == 1) {
								// Inicio while dos livros
								
								System.out.println("\n1 - Cadastrar livro");
								System.out.println("2 - Remover livro");
								System.out.println("3 - Listar livros");
								System.out.println("4 - Buscar livro");
								System.out.println("5 - Atualizar livro");
								System.out.println("6 - Voltar");
								
								System.out.println("Opção escolhida");
								int menuLivro = leInt.nextInt();
								
								switch(menuLivro) {
								
								case 1:
									
									System.out.println("Titulo: ");
									titulo = leString.next();
									
									System.out.println("Editora: ");
									razaoSocial = leString.next();
									
									System.out.println("Número de páginas: ");
									paginas = leInt.nextInt();
									
									Livro novoLivro = new Livro(titulo, editoraDAO.buscaEditoraPorRazaoSocial(razaoSocial), paginas);
									
									livroDAO.adiciona(novoLivro);
									
									System.out.println("Adicionar escritores? (S/N)");
									if(leString.next().equalsIgnoreCase("S")) {
										adicionaEscritorLivro(novoLivro);
									}
									
									System.out.println("Adicionar generos? (S/N)");
									if(leString.next().equalsIgnoreCase("S")) {
										adicionaGeneroLivro(novoLivro);
									}
									
									break;
									
								case 2:
									
									System.out.println("Titulo do livro: ");
									titulo = leString.next();
									
									livroDAO.remove(livroDAO.buscaPorTitulo(titulo));
									
									break;
									
								case 3:
									
									livros = livroDAO.lista();
									
									if(livros.size() == 0) {
										System.out.println("Nenhum livro cadastrado.");
										break;
									}
									
									for(indice = 0; indice < livros.size(); indice++) {
										System.out.println("\nID do Livro: " + livros.get(indice).getIdLivro());
										System.out.println("Titulo: " + livros.get(indice).getTitulo());
										
										if(livros.get(indice).getEscritores().size() > 0) {
											System.out.println("Escritores:");
											for(int indiceZ = 0; indiceZ < livros.get(indice).getEscritores().size(); indiceZ++) {
												System.out.println(livros.get(indice).getEscritores().get(indiceZ).getNome() + " " + 
																   livros.get(indice).getEscritores().get(indiceZ).getSobrenome());
											}
										}
										
										if(livros.get(indice).getGeneros().size() > 0) {
											System.out.println("Generos:");
											for(int indiceY = 0; indiceY < livros.get(indice).getGeneros().size(); indiceY++) {
												System.out.println(livros.get(indice).getGeneros().get(indiceY).getGenero());
											}
										}
										
										System.out.println("Páginas: " + livros.get(indice).getPaginas());
										System.out.println("Editora: " + livros.get(indice).getEditora().getRazaoSocial());
									}
									
									break;
									
								case 4:
									
									System.out.println("Titulo do livro: ");
									titulo = leString.next();
									
									Livro livro = livroDAO.buscaPorTitulo(titulo);
									if(livro.getIdLivro() == 0) {
										System.out.println("Livro não cadastrado.");
										break;
									}
									
									System.out.println("\nID do livro: " + livro.getIdLivro());
									System.out.println("Titulo: " + livro.getTitulo());
									
									if(livro.getEscritores().size() > 0) {
										System.out.println("Escritores: ");
										for(indice = 0; indice < livro.getEscritores().size(); indice++) {
											System.out.println(livro.getEscritores().get(indice).getNome() + " " + livro.getEscritores().get(indice).getSobrenome());
										}
									}
									
									if(livro.getGeneros().size() > 0) {
										System.out.println("Generos: ");
										for(indice = 0; indice < livro.getGeneros().size(); indice++) {
											System.out.println(livro.getGeneros().get(indice).getGenero());
										}
									}
									
									System.out.println("Páginas: " + livro.getPaginas());
									System.out.println("Editora: " + livro.getEditora().getRazaoSocial());
									
									System.out.println("\nO que deseja fazer com o livro?");
									System.out.println("1 - Atualizar livro");
									System.out.println("2 - Remover livro");
									System.out.println("3 - Voltar");
									
									System.out.println("Informe a opção escolhida");
									int menuBuscaLivro = leInt.nextInt();
									
									switch (menuBuscaLivro) {
									case 1:
										atualizaLivro(livro);
										break;
										
									case 2:
										livroDAO.remove(livro);
										break;
										
									case 3:
										break;
										
									default:
										System.out.println("Opção indisponível.");
										break;
									}
									
									break;
									
								case 5:
									
									System.out.println("Titulo do livro: ");
									titulo = leString.next();
									
									atualizaLivro(livroDAO.buscaPorTitulo(titulo));
									break;
									
								case 6:
									
									System.out.println("Deseja realmente voltar ao menu anterior? (S/N)");
									escolha = leString.next();
									
									if(escolha.equalsIgnoreCase("S"))
										loopLivro = 0;
									
									break;
									
								default:
									System.out.println("Opção indisponível, tente novamente;");
									break;
								
								}
								
								// Fim while dos livros
							}
							
							break;
							
						case 2:
							
							int loopEscritor = 1;
							
							while(loopEscritor == 1) {
								// Inicio while dos escritores
								
								System.out.println("\n1 - Cadastrar escritor");
								System.out.println("2 - Remover escritor");
								System.out.println("3 - Listar escritores");
								System.out.println("4 - Buscar escritor");
								System.out.println("5 - Atualizar escritor");
								System.out.println("6 - Voltar");
								
								System.out.println("Opção escolhida:");
								int menuEscritor = leInt.nextInt();
								
								switch(menuEscritor) {
									
								case 1:
									
									System.out.println("Nome:");
									nome = leString.next();
									
									System.out.println("Sobrenome:");
									sobrenome = leString.next();
									
									System.out.println("CPF:");
									cpf = leString.next();
									
									System.out.println("Email:");
									email = leString.next();
									
									Escritor novoEscritor = new Escritor(nome, sobrenome, cpf, 0, email);
									escritorDAO.adiciona(novoEscritor);
									
									break;
									
								case 2:
									
									System.out.println("ID do escritor: ");
									id = leInt.nextInt();
									escritorDAO.remove(escritorDAO.buscaEscritor(id));
									
									break;
									
								case 3:
									
									escritores = escritorDAO.lista();
									
									if(escritores.size() == 0) {
										System.out.println("Nenhum escritor cadastrado.");
									}
								
									for(indice = 0; indice < escritores.size(); indice++) {
										System.out.println("\nID do escritor: " + escritores.get(indice).getIdEscritor());
										System.out.println("Nome: " + escritores.get(indice).getNome());
										System.out.println("Sobrenome: " + escritores.get(indice).getSobrenome());
										System.out.println("CPF: " + escritores.get(indice).getCPF());
										System.out.println("Email: " + escritores.get(indice).getEmail());
									}
								  	
									break;
									
								case 4:
									
									System.out.println("");
									
									break;
									
								case 5:
									
									System.out.println("ID do escritor:");
									id = leInt.nextInt();
									
									atualizaEscritor(escritorDAO.buscaEscritor(id));
									
									break;
									
								case 6:
									
									System.out.println("Deseja realmente voltar ao menu anterior? (S/N)");
									escolha = leString.next();
									
									if(escolha.equalsIgnoreCase("S"))
										loopEscritor = 0;
									
									break;
									
								default:
									break;
									
								}
								
								// Fim while dos escritores 
							}
							
							break;
							
						case 3:
							
							int loopEditora = 1;
							
							while(loopEditora == 1) {
								// Inicio while das editoras
								
								System.out.println("\n1 - Cadastrar editora");
								System.out.println("2 - Remover editora");
								System.out.println("3 - Listar editoras");
								System.out.println("4 - Buscar editora");
								System.out.println("5 - Atualizar editora");
								System.out.println("6 - Voltar");
								
								System.out.println("Opção escolhida");
								int menuEditora = leInt.nextInt();
								
								switch(menuEditora) {
								case 1:
									
									System.out.println("Razão Social: ");
									razaoSocial = leString.next();
									
									System.out.println("CNPJ: ");
									cnpj = leString.next();
									
									System.out.println("Email: ");
									email = leString.next();
									
									Editora novaEditora = new Editora(razaoSocial, cnpj, email);
									editoraDAO.adiciona(novaEditora);
									
									break;
									
								case 2:
									
									System.out.println("Razão Social: ");
									razaoSocial = leString.next();
									editoraDAO.remove(editoraDAO.buscaEditoraPorRazaoSocial(razaoSocial));
									
									break;
									
								case 3:
									
									editoras = editoraDAO.lista();
									
									if(editoras.size() == 0) {
										System.out.println("Nenhum editora cadastrada.");
										break;
									}
									
									for(indice = 0; indice < editoras.size(); indice++) {
										System.out.println("\nID: " + editoras.get(indice).getIdPessoa());
										System.out.println("Razão Social: " + editoras.get(indice).getRazaoSocial());
										System.out.println("CNPJ: " + editoras.get(indice).getCnpj());
										System.out.println("Email: " + editoras.get(indice).getEmail());
									}
									
									break;
									
								case 4:
									
									System.out.println("Razao Social:");
									razaoSocial = leString.next();
									
									Editora editora = editoraDAO.buscaEditoraPorRazaoSocial(razaoSocial);
									
									if(editora.getRazaoSocial() == null) {
										System.out.println("Editora não encontrada.");
									}
									
									System.out.println("\nID editora: " + editora.getIdPessoa());
									System.out.println("Razao Social: " + editora.getRazaoSocial());
									System.out.println("CNPJ: " + editora.getCnpj());
									System.out.println("Email: " + editora.getEmail());
										
									break;
									
								case 5:
									
									System.out.println("Razão social da editora: ");
									razaoSocial = leString.next();
									
									atualizaEditora(editoraDAO.buscaEditoraPorRazaoSocial(razaoSocial));
									
									break;
									
								case 6:
									
									System.out.println("Deseja realmente voltar ao menu anterior? (S/N)");
									escolha = leString.next();
									
									if(escolha.equalsIgnoreCase("S"))
										loopEditora = 0;
									
									break;
								}
								
								// Fim while das editoras
							}
							
							break;
							
						case 4:
							
							int loopGeneros = 1;
							
							while(loopGeneros == 1) {
								// Inicio do while de generos 
								
								System.out.println("\n1 - Cadastrar genero");
								System.out.println("2 - Remover genero");
								System.out.println("3 - Listar generos");
								System.out.println("4 - Buscar genero");
								System.out.println("5 - Atualizar genero");
								System.out.println("6 - Voltar");
								
								System.out.println("Opção escolhida: ");
								int menuGenero = leInt.nextInt();
								
								switch(menuGenero) {
								case 1:
									
									System.out.println("Genero: ");
									genero = leString.next();
									
									Genero novoGenero = new Genero(genero);
									generoDAO.adiciona(novoGenero);
									
									break;
									
								case 2:
									
									System.out.println("Genero: ");
									genero = leString.next();
									
									generoDAO.remove(generoDAO.buscaGenero(genero));
									
									break;
									
								case 3:
									
									generos = generoDAO.lista();
									
									if(generos.size() == 0) {
										System.out.println("Nenhum genero cadastrado.");
										break;
									}
									
									System.out.println("\nGeneros:");
									for(indice = 0; indice < generos.size(); indice++) {
										System.out.println(generos.get(indice).getGenero());
									}
									
									break;
									
								case 4:
									break;
									
								case 5:
									break;
									
								case 6:
									
									System.out.println("Deseja realmente voltar ao menu anterior? (S/N)");
									escolha = leString.next();
									
									if(escolha.equalsIgnoreCase("S"))
										loopGeneros = 0;
									
									break;
									
								default:
									break;
								}
								
								// Fim do while de generos
							}
							
							break;
							
						case 5:
							
							System.out.println("Deseja realmete do menu adninistrativo? (S/N)");
							escolha = leString.next();
							
							if(escolha.equalsIgnoreCase("S"))
								loopADM = 0;
							
							break;
							
						default:
							break;
						
						}
						
						// Fim while de ADM
					}
					
				}
				
				break;
				
			case 4:
				
				System.out.println("Nome:");
				nome = leString.next();
				
				System.out.println("Sobrenome:");
				sobrenome = leString.next();
				
				System.out.println("CPF:");
				cpf = leString.next();
				
				System.out.println("Email:");
				email = leString.next();
				
				System.out.println("Login:");
				login = leString.next();
				
				System.out.println("Senha:");
				senha = leString.next();
				
				novoUsuario = new Usuario(nome, sobrenome, cpf, 0, email, login, senha, "A");
				
				usuarioDAO.adiciona(novoUsuario);
				
				break;
				
			case 5:
				
				System.out.println("Deseja reamente sair do programa? (S/N)");
				escolha = leString.next();
				
				if(escolha.equalsIgnoreCase("S"))
					loopInicio = 0;
				
				break;
				
			default:
				
				System.out.println("Opção indisponível, tente novamente.");
				
				break;
			}
			
		}
		
		
	}
	
	@SuppressWarnings("resource")
	private static void adicionaEscritorLivro(Livro livro) {
		
		Scanner read = new Scanner(System.in);
		LivroDAO livroDAO = new LivroDAO();
		EscritorDAO escritorDAO = new EscritorDAO();
		int loop = 1;
		
		while(loop == 1) {
			System.out.println("ID do escritor: ");
			int id = read.nextInt();
			
			livroDAO.adicionaEscritor(livro, escritorDAO.buscaEscritor(id));
			
			System.out.println("Adicionar outro escritor? (S/N)");
			String escolha = read.next();
			
			if(!escolha.equalsIgnoreCase("S"))
				loop = 0;
		}
		
		return;
	}
	
	@SuppressWarnings("static-access")
	private static void adicionaGeneroLivro(Livro livro) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		LivroDAO livroDAO = new LivroDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		int loop = 1;
		
		while(loop == 1) {
			System.out.println("Genero: ");
			String genero = read.next();
			
			livroDAO.adicionaGenero(livroDAO.buscaPorTitulo(livro.getTitulo()), generoDAO.buscaGenero(genero));
			
			System.out.println("Adicionar outro genero? (S/N)");
			String escolha = read.next();
			
			if(!escolha.equalsIgnoreCase("S")) {
				loop = 0;
			}
		}
		
		return ;
	}
	
	private static void removeEscritorLivro(Livro livro) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		LivroDAO livroDAO = new LivroDAO();
		EscritorDAO escritorDAO = new EscritorDAO();
		int loop = 1;
		
		while(loop == 1) {
			System.out.println("ID do escritor: ");
			int id = read.nextInt();
			
			livroDAO.removeEscritor(livro, escritorDAO.buscaEscritor(id));
			
			System.out.println("Remover outro escritor? (S/N)");
			String escolha = read.next();
			
			if(!escolha.equalsIgnoreCase("S"))
				loop = 0;
		}
		
	}
	
	private static void removeGeneroLivro(Livro livro){
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		LivroDAO livroDAO = new LivroDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		int loop = 1;
		
		while(loop == 1) {
			System.out.println("Genero: ");
			String genero = read.next();
			
			livroDAO.removeGenero(livro, generoDAO.buscaGenero(genero));
			
			System.out.println("Remover outro genero? (S/N)");
			String escolha = read.next();
			
			if(!escolha.equalsIgnoreCase("S"))
				loop = 0;
		}
		
	}
	
	private static Pessoa cadastraPessoa() {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		
		System.out.println("Email: ");
		String email = read.next();
		
		Pessoa novaPessoa = new Pessoa(email);
		
		return novaPessoa;
	}
	
	@SuppressWarnings("resource")
	private static PessoaFisica cadastraPessoaFisica() {
		
		Scanner read = new Scanner(System.in);
		
		System.out.println("Nome: ");
		String nome = read.next();
		
		System.out.println("Sobrenome: ");
		String sobrenome = read.next();
		
		System.out.println("CPF: ");
		String cpf = read.next();
		
		System.out.println("Email: ");
		String email = read.next();
		
		PessoaFisica novaPessoaFisica = new PessoaFisica(nome, sobrenome, cpf, email);
		
		return novaPessoaFisica;
	}
	
	private static void atualizaEscritor(Escritor escritor) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		EscritorDAO escritorDAO = new EscritorDAO();
		
		System.out.println("\nO que deseja alterar?");
		System.out.println("1 - Alterar nome e sobrenome");
		System.out.println("2 - Alterar email");
		
		System.out.println("Informe a opção escolhida? (S/N)");
		int escolha = read.nextInt();
		
		switch(escolha) {
		
		case 1:
			
			System.out.println("Nome: ");
			escritor.setNome(read.next());
			
			System.out.println("Sobrenome: ");
			escritor.setSobrenome(read.next());
			
			escritorDAO.atualizaEscritor(escritor, 1);
			
			break;
			
		case 2:
			
			System.out.println("Email: ");
			escritor.setEmail(read.next());
			
			escritorDAO.atualizaEscritor(escritor, 2);
			
			break;
		
		default:
			System.out.println("Opção indisponível, tente novamente.");
			break;
		
		}
		
	}
	
	private static void atualizaEditora(Editora editora) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		EditoraDAO editoraDAO = new EditoraDAO();
		
		System.out.println("\nO que deseja alterar?");
		System.out.println("1 - Alterar razão social");
		System.out.println("2 - Alterar email");
		
		System.out.println("Informe a opção escolhida: ");
		int escolha = read.nextInt();
		
		switch(escolha) {
		case 1:
			
			System.out.println("Nova razão social: ");
			editora.setRazaoSocial(read.next());
			
			editoraDAO.atualizaEditora(editora, 1);
			
			break;
			
		case 2:
			
			System.out.println("Novo email: ");
			editora.setEmail(read.next());
			
			editoraDAO.atualizaEditora(editora, 2);
			
			break;
			
		default:
			System.out.println("Opção indisponível, tente novamente.");
			break;
		}
	}
	
	@SuppressWarnings("static-access")
	private static void atualizaLivro(Livro livro) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		LivroDAO livroDAO = new LivroDAO();
		EditoraDAO editoraDAO = new EditoraDAO();
		Editora editora = null;
		
		System.out.println("\nO que deseja alterar?");
		System.out.println("1 - Titulo");
		System.out.println("2 - Número de páginas");
		System.out.println("3 - Adicionar escritor");
		System.out.println("4 - Adicionar genero");
		System.out.println("5 - Remover escritor");
		System.out.println("6 - Remover genero");
		System.out.println("7 - Editora");
		
		System.out.println("Informe a opção escolhida: ");
		int escolha = read.nextInt();
		
		switch(escolha) {
		
		case 1:
			System.out.println("Novo titulo: ");
			livro.setTitulo(read.next());
			
			livroDAO.atualizaLivro(livro, 1);
			break;
			
		case 2:
			System.out.println("Novo número de páginas: ");
			livro.setPaginas(read.nextInt());
			
			livroDAO.atualizaLivro(livro, 2);
			break;
			
		case 3:
			adicionaEscritorLivro(livro);
			break;
		
		case 4:
			adicionaGeneroLivro(livro);
			break;
			
		case 5:
			removeEscritorLivro(livro);
			break;
			
		case 6:
			removeGeneroLivro(livro);
			break;
			
		case 7:
			System.out.println("Nova editora: ");
			String razaoSocial = read.next();
			
			editora = editoraDAO.buscaEditoraPorRazaoSocial(razaoSocial);
			if(editora.getIdPessoa() == 0) {
				System.out.println("Editora não cadastrada.");
				break;
			}
			
			livro.setEditora(editora);
			livroDAO.atualizaLivro(livro, 3);
			break;
			
		default:
			System.out.println("Opção indisponível, tente novamente.");
			break;
		}
		
	}

}
































