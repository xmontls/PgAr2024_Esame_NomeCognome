package it.unibs.fp.arnaldowest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazzo {

    private ArrayList<Carta> mazzo;

    public Mazzo() {
        this.mazzo = new ArrayList<>();
        creaMazzo();
    }

    private void creaMazzo() {
        // Aggiungi 74 carte GiocaScarta, 50 bang e 24 mancato
        for (int i = 0; i < 50; i++) {
            GiocaScarta cartaGiocaScarta = new GiocaScarta("GiocaScarta", "BANG!");
            mazzo.add(cartaGiocaScarta);
        }
        for (int i = 0; i < 24; i++) {
            GiocaScarta cartaGiocaScarta = new GiocaScarta("GiocaScarta", "Mancato!");
            mazzo.add(cartaGiocaScarta);
        }

        // Aggiungi 6 carte Equipaggiabili, 3 Schofield, 1 Remington, 1 Rev. Carabine e 1 Winchester
        for (int i = 0; i < 3; i++) {
            Arma Schofield = new Arma("Equipaggiabili", "Schofield", 2);
            mazzo.add(Schofield);
        }
        Arma Remington = new Arma("Equipaggiabili", "Remington", 3);
        mazzo.add(Remington);
        Arma RevCarabine = new Arma("Equipaggiabili", "Rev. Carabine", 4);
        mazzo.add(RevCarabine);
        Arma Winchester = new Arma("Equipaggiabili", "Winchester", 5);
        mazzo.add(Winchester);
    }

    public void mescola() {
        Collections.shuffle(mazzo);
    }

    public List<Carta> pescaCarteCasuali(int numeroCarte) {
        if (numeroCarte > mazzo.size()) {
            throw new IllegalArgumentException("Il numero di carte richieste supera il numero di carte nel mazzo.");
        }
        List<Carta> carteCasuali = new ArrayList<>();
        for (int i = 0; i < numeroCarte; i++) {
            carteCasuali.add(mazzo.remove(0));
        }
        return carteCasuali;
    }
    
    public ArrayList<Carta> getMazzo() {
        return mazzo;
    }

	public void setMazzo(ArrayList<Carta> mazzo) {
		this.mazzo = mazzo;
	}

	@Override
	public String toString() {
		return "Mazzo [mazzo=" + mazzo + "]";
	}
    
}

