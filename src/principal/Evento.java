package principal;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.*;

public class Evento {

	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		ArrayList<Produto> produtos = new ArrayList<>();
		ArrayList<Leilao> leiloes = new ArrayList<>();

		boolean sim = true;
		int opcao, cont = 0;

		while (sim) {
			System.out.println("Digite o número da opção:");
			System.out.println("(1) Cadastrar produto");
			System.out.println("(2) Cadastrar pessoa");
			System.out.println("(3) Listar");
			System.out.println("(4) Criar leilão");
			System.out.println("(5) Iniciar evento");
			System.out.println("(6) Sair");
			opcao = s.nextInt();
			s.nextLine();

			if (opcao == 1) {
				cadastrarProduto(produtos);
			} else if (opcao == 2) {
				cadastrarPessoa(pessoas);
			} else if (opcao == 3) {
				System.out.println("Listar:");
				System.out.println("(1) Produtos");
				System.out.println("(2) Pessoas");
				int escolha = s.nextInt();
				s.nextLine();

				if (escolha == 1) {
					for (Produto produto : produtos) {
						produto.showInfo();
					}
				} else if (escolha == 2) {
					for (Pessoa pessoa : pessoas) {
						pessoa.showInfo();
					}
				}

			} else if (opcao == 4) {
				criarLeilao(produtos, leiloes);
			} else if (opcao == 5) {
				
				while (cont < leiloes.size()) {
					System.out.println("Qual leilão deseja realizar?");
					for (Leilao leilao : leiloes) {
						if (leilao.isGanhou() == true) {
							System.out.println(leiloes.indexOf(leilao) + ": Encerrado");
						} else {
							System.out.println(leiloes.indexOf(leilao) + ": " + leilao.getProduto().getNome() + " - R$ "
									+ leilao.getValorMinimo());
						}
					}
					int escolha = s.nextInt();
					s.nextLine();
					
					for (Leilao leilao : leiloes) {
						if (leiloes.indexOf(leilao) == escolha && leilao.isGanhou() == false) {
							setParticipantes(pessoas, leilao);
							cont++;
							break;
						}
					}
				}
				
				System.out.println("Não tem nenhum leilão disponível, crie mais ou encerre o evento");
			} else if (opcao == 6) {
				System.out.println("\nAcabou esse evento top! ヾ(〃^∇^)ﾉ\n");
				calcularEstatisticas(pessoas);
				sim = false;
			}
		}
	}

	
	/*
	 * função para calcular as estatísticas de um evento
	 */
	private static void calcularEstatisticas(ArrayList<Pessoa> pessoas) {
		Pessoa ganhouMais = pessoas.get(0);
		Pessoa gastouMais = pessoas.get(0);
		Pessoa gastouMenos = pessoas.get(0);
		Pessoa lancouMais = pessoas.get(0);
		for (Pessoa pessoa : pessoas) {
			if (pessoa.getQtdProdutosGanhos() > ganhouMais.getQtdProdutosGanhos()) {
				ganhouMais = pessoa;
			}
			if (pessoa.getQtdLances() > lancouMais.getQtdLances()) {
				lancouMais = pessoa;
			}
			if ((pessoa.getQtdDinheiro() - pessoa.getDinheiroAtual()) > (gastouMais.getQtdDinheiro()
					- gastouMais.getDinheiroAtual())) {
				gastouMais = pessoa;
			}
			if ((pessoa.getQtdDinheiro() - pessoa.getDinheiroAtual()) < (gastouMais.getQtdDinheiro()
					- gastouMais.getDinheiroAtual())) {
				gastouMenos = pessoa;
			}
		}
		System.out.println("Estatísticas:");
		System.out.println(ganhouMais.getNome() + " foi a pessoa que ganhou mais: " + ganhouMais.getQtdProdutosGanhos());
		System.out.println(lancouMais.getNome() + " foi a pessoa que fez mais lances: " + lancouMais.getQtdLances());
		float atual = gastouMais.getDinheiroAtual();
		float inicial = gastouMais.getQtdDinheiro();
		float mais = inicial - atual;
		System.out.println(gastouMais.getNome() + " foi a pessoa que gastou mais: " + mais);
		atual = gastouMenos.getDinheiroAtual();
		inicial = gastouMenos.getQtdDinheiro();
		float menos = inicial - atual;
		System.out.println(gastouMenos.getNome() + " foi a pessoa que gastou menos: " + menos);
	}

	/*
	 * função para cadastrar um produto em um evento
	 */
	private static void cadastrarProduto(ArrayList<Produto> produtos) {
		System.out.println("ID:");
		int id = s.nextInt();
		s.nextLine();

		System.out.println("Nome:");
		String nome = s.nextLine();

		Produto produto = new Produto(id, nome);
		produtos.add(produto);
	}

	/*
	 * função para cadastrar uma pessoa em um evento
	 */
	private static void cadastrarPessoa(ArrayList<Pessoa> pessoas) {
		System.out.println("Nome:");
		String nome = s.nextLine();

		System.out.println("Dinheiro:");
		float qtdDinheiro = s.nextFloat();
		s.nextLine();

		Pessoa pessoa = new Pessoa(nome, qtdDinheiro);
		pessoas.add(pessoa);
	}

	/*
	 * função para crir um leilão dentro de um evento
	 */
	private static void criarLeilao(ArrayList<Produto> produtos, ArrayList<Leilao> leiloes) {
		if(produtos.isEmpty()) {
			return;
		}
		
		for (Produto produto : produtos) {
			produto.showInfo();
		}
		
		System.out.println("Informe o ID do produto que deseja leiloar");
		int id = s.nextInt();
		s.nextLine();

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				System.out.println("Qual o valor mínimo desse produto?");
				float valor = s.nextFloat();
				Leilao leilao = new Leilao(valor, produto);
				leiloes.add(leilao);
				return;
			}
		}
		System.out.println("Não pode leiloar um produto que não existe\n");
	}

	/*
	 * função para iniciar um leilão
	 */
	private static void iniciarLeilao(Leilao leilao, ArrayList<Pessoa> participantes) {
		boolean ganhador = false;
		Lance maior = new Lance();
		maior.setValor(leilao.getValorMinimo());
		System.out.println("O valor inicial é: " + leilao.getValorMinimo());

		while (!ganhador) {
			ArrayList<Lance> lances = new ArrayList<>();
			System.out.println("Rodada número " + leilao.getRodada());

			for (Pessoa part : participantes) {
				if (part == maior.getPessoa()) {
					printGanhador(leilao, maior, part);
					leilao.setGanhou(true);
					ganhador = true;
					break;
				}

				System.out.println(part.getNome() + " quer fazer lance? (1) sim (2) nao");
				int opcao = s.nextInt();
				s.nextLine();

				if (opcao == 1) {

					if (part.getDinheiroAtual() < maior.getValor() + 5) {
						System.out.println("Você não pode mais dar lances, seu dinheiro acabou (╯°□°)╯\n");
						part.setQueroDarLance(false);
					} else {
						part.setQueroDarLance(true);
					}

				} else if (opcao == 2) {
					System.out.println(part.getNome() + " não vai fazer lance\n");
					part.setQueroDarLance(false);
				}

				if (part.QueroDarLance() == true) {
					maior = fazerLance(maior, lances, part);
				}
			}

			leilao.setRodada(leilao.getRodada() + 1);
		}
	}

	/*
	 * função para selecionar as pessoas que querem participar daquele leilão
	 */
	private static void setParticipantes(ArrayList<Pessoa> pessoas, Leilao leilao) {
		ArrayList<Pessoa> participantes = new ArrayList<>();
		System.out.println("Leilão do produto: " + leilao.getProduto().getNome());
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome() + " deseja participar? (1) sim (2) não");
			int opcao = s.nextInt();
			s.nextLine();

			if (opcao == 1) {
				pessoa.setQueroParticipar(true);
				participantes.add(pessoa);
			}
		}

		if (participantes.isEmpty()) {
			System.out.println("Ninguém quer participar desse leilão\n");
		} else {
			iniciarLeilao(leilao, participantes);
		}
	}

	/*
	 * print do ganhador
	 */
	private static void printGanhador(Leilao leilao, Lance maior, Pessoa part) {
		part.setQtdProdutosGanhos(part.getQtdProdutosGanhos() + 1);
		part.setDinheiroAtual(part.getDinheiroAtual() - maior.getValor());
		System.out.println("\n(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
		System.out.println(part.getNome() + " ganhou " + leilao.getProduto().getNome());
		System.out.println("Valor: " + maior.getValor() + "\n");
	}

	/*
	 * função para fazer e validar um lance
	 */
	private static Lance fazerLance(Lance maior, ArrayList<Lance> lances, Pessoa part) {
		float valor = 0;

		while (valor < maior.getValor() + 5) {
			System.out.println("Qual é o lance?");
			valor = s.nextFloat();
			s.nextLine();

			Lance lance = new Lance(part, valor);
			if (lance.getValor() > maior.getValor()) {
				maior = lance;
				part.setQtdLances(part.getQtdLances() + 1);
				lances.add(lance);
				break;
			} else {
				System.out.println("O lance precisa ser maior que " + maior.getValor() + " em pelo menos 5");
			}
		}

		return maior;
	}
}
