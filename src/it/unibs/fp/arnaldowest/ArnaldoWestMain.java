package it.unibs.fp.arnaldowest;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.Collections;

public class ArnaldoWestMain {

    public static void main(String[] args) {

        System.out.println("Benvenuti nell'ArnaldoWest!");
        System.out.println();
        System.out.println("Iniziamo una nuova partita!");
        int numGiocatori = InputDati.leggiIntero("Quanti giocatori vogliono partecipare alla partita? ", 4, 7);

        // Creazione dei giocatori
        ArrayList<Giocatore> giocatori = new ArrayList<>();
        Giocatore sceriffo = new Sceriffo("Sceriffo", 5);
        giocatori.add(sceriffo);

        // Genera una lista di ruoli
        ArrayList<String> ruoli = new ArrayList<>();
        ruoli.add("Fuorilegge");
        ruoli.add("Fuorilegge");
        ruoli.add("Rinnegato");

        if (numGiocatori > 4) {
            ruoli.add("Vice");
        }
        if (numGiocatori > 5) {
            ruoli.add("Fuorilegge");
        }
        if (numGiocatori > 6) {
            ruoli.add("Vice");
        }
        Collections.shuffle(ruoli);

        // Aggiungi i giocatori con ruoli e distanze predefinite
        switch (numGiocatori) {
            case 4:
                giocatori.add(new Giocatore(ruoli.get(0), 4, 1));
                giocatori.add(new Giocatore(ruoli.get(1), 4, 2));
                giocatori.add(new Giocatore(ruoli.get(2), 4, -1));
                break;
            case 5:
                giocatori.add(new Giocatore(ruoli.get(0), 4, 1));
                giocatori.add(new Giocatore(ruoli.get(1), 4, 2));
                giocatori.add(new Giocatore(ruoli.get(2), 4, -2));
                giocatori.add(new Giocatore(ruoli.get(3), 4, -1));
                break;
            case 6:
                giocatori.add(new Giocatore(ruoli.get(0), 4, 1));
                giocatori.add(new Giocatore(ruoli.get(1), 4, 2));
                giocatori.add(new Giocatore(ruoli.get(2), 4, 3));
                giocatori.add(new Giocatore(ruoli.get(3), 4, -2));
                giocatori.add(new Giocatore(ruoli.get(4), 4, -1));
                break;
            case 7:
                giocatori.add(new Giocatore(ruoli.get(0), 4, 1));
                giocatori.add(new Giocatore(ruoli.get(1), 4, 2));
                giocatori.add(new Giocatore(ruoli.get(2), 4, 3));
                giocatori.add(new Giocatore(ruoli.get(3), 4, -3));
                giocatori.add(new Giocatore(ruoli.get(4), 4, -2));
                giocatori.add(new Giocatore(ruoli.get(5), 4, -1));
                break;
        }

        // Avvio della partita
        Partita.iniziaScontro(giocatori);

        // Dichiarazione del risultato 
        if(Partita.isSceriffoPresente(giocatori) == true) {
        	System.out.println("Vincono la partita lo Sceriffo e i suoi Vice!!");
        } else if(Partita.areFuorileggiPresenti(giocatori) == true)
        	System.out.println("Vincono la partita i Fuorilegge, che restano ancora in circolazione!!");
        else
        	System.out.println("Vince la partita il Rinnegato, che diventa il nuovo Sceriffo in città!!");
    }
}
