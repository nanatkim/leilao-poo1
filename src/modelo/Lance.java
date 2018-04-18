package modelo;

public class Lance {
	private Pessoa pessoa;
	private float valor;
	
	public Lance() {
		
	}
	
	public Lance(Pessoa pessoa, float valor){
		this.pessoa = pessoa;
		this.valor = valor;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
