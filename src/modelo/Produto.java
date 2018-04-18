package modelo;

public class Produto {
	private int id;
	private String nome;
	
	public Produto(int id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void showInfo() {
		System.out.println("ID: " + this.getId()+" - Nome: " + this.getNome());
	}
}
