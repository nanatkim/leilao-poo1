package modelo;

public class Pessoa {
	private String nome;
	private float qtdDinheiro;
	private float dinheiroAtual;
	private int qtdLances;
	private int qtdProdutosGanhos;
	private boolean queroParticipar = false;
	private boolean queroDarLance = false;

	public Pessoa() {

	}

	public Pessoa(String nome, float qtdDinheiro) {
		this.nome = nome;
		this.qtdDinheiro = qtdDinheiro;
		this.dinheiroAtual = qtdDinheiro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getQtdDinheiro() {
		return qtdDinheiro;
	}

	public void setQtdDinheiro(float qtdDinheiro) {
		this.qtdDinheiro = qtdDinheiro;
	}

	public float getDinheiroAtual() {
		return dinheiroAtual;
	}

	public void setDinheiroAtual(float dinheiroAtual) {
		this.dinheiroAtual = dinheiroAtual;
	}
	
	public int getQtdLances() {
		return qtdLances;
	}

	public void setQtdLances(int qtdLances) {
		this.qtdLances = qtdLances;
	}

	public int getQtdProdutosGanhos() {
		return qtdProdutosGanhos;
	}

	public void setQtdProdutosGanhos(int qtdProdutosGanhos) {
		this.qtdProdutosGanhos = qtdProdutosGanhos;
	}

	public boolean queroParticipar() {
		return queroParticipar;
	}

	public void setQueroParticipar(boolean queroParticipar) {
		this.queroParticipar = queroParticipar;
	}

	public boolean QueroDarLance() {
		return queroDarLance;
	}

	public void setQueroDarLance(boolean queroDarLance) {
		this.queroDarLance = queroDarLance;
	}

	public void showInfo() {
		System.out.println("Nome: " + this.getNome());
		System.out.println("Dinheiro: " + this.getDinheiroAtual());
		System.out.println("Produtos: " + this.getQtdProdutosGanhos());
		System.out.println("Lances: " + this.getQtdLances() + "\n");
	}
}
