package modelo;

public class Leilao {
	private float valorMinimo;
	private int rodada = 1;
	private Produto produto;
	private boolean ganhou = false;

	public Leilao(float valorMinimo, Produto produto){
		this.valorMinimo = valorMinimo;
		this.produto = produto;
	}
	
	public float getValorMinimo() {
		return valorMinimo;
	}
	
	public void setValorMinimo(float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	
	public int getRodada() {
		return rodada;
	}
	
	public void setRodada(int rodada) {
		this.rodada = rodada;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean isGanhou() {
		return ganhou;
	}

	public void setGanhou(boolean ganhou) {
		this.ganhou = ganhou;
	}
}
