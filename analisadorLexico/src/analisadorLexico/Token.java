package analisadorLexico;

public class Token {
	
	private String nome;
	private String atributo;
	private int indice;
	
	public Token(String nome, String atributo) {
		
		this.nome = nome;
		this.atributo = atributo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	
	
	
	

}
