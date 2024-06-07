package it.unibs.fp.arnaldowest;

public class GiocaScarta extends Carta {
	
	@Override
	public String toString() {
		return "GiocaScarta [nome=" + nome + "]";
	}

	private String nome;
	
	public GiocaScarta(String tipo, String nome) {
		super("GiocaScarta");
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
