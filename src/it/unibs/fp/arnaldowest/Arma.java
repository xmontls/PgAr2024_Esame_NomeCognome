package it.unibs.fp.arnaldowest;

public class Arma extends Carta{
	
	@Override
	public String toString() {
		return "Arma [distanza=" + distanza + ", nome=" + nome + "]";
	}

	private int distanza;
	private String nome;
	
	public Arma(String tipo, String nome, int distanza) {
		super("Equipaggiabili");
		this.distanza = distanza;
		this.nome = nome;
	}
}
