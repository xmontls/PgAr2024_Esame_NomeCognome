package it.unibs.fp.arnaldowest;
 
import java.util.List;

public class Giocatore {

    private String ruolo;
    private List<Carta> carte;
    private List<Carta> carteEquipaggiate;
    private int PF;
    private int distanza;

    public Giocatore(String ruolo, int pF, int distanza) {
        this.ruolo = ruolo;
        this.PF = pF;
        this.distanza = distanza;
    }

    
    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public List<Carta> getCarte() {
        return carte;
    }

    public void setCarte(List<Carta> carteGiocatore) {
        this.carte = carteGiocatore;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int pF) {
        this.PF = pF;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }


	public List<Carta> getCarteEquipaggiate() {
		return carteEquipaggiate;
	}


	public void setCarteEquipaggiate(List<Carta> carteEquipaggiate) {
		this.carteEquipaggiate = carteEquipaggiate;
	}


	@Override
	public String toString() {
		return "Giocatore [ruolo=" + ruolo + ", carte=" + carte + ", carteEquipaggiate=" + carteEquipaggiate + ", PF="
				+ PF + ", distanza=" + distanza + "]";
	} 
}

