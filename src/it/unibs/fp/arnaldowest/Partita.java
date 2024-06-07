package it.unibs.fp.arnaldowest;

import java.util.ArrayList;
import java.util.List;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Partita {

    public static void rivelaRuoli(ArrayList<Giocatore> giocatori) {
    	System.out.println();
		System.out.println("Rivelazione momentanea dei ruoli: non fare vedere a chi ti sta in parte il tuo ruolo!! ;)");
        for (int i = 0; i < giocatori.size(); i++) {
            Giocatore giocatore = giocatori.get(i);
            System.out.println("Giocatore numero " + (i + 1) + " ha ruolo: " + giocatore.getRuolo());
            if(giocatore.getRuolo() == "Sceriffo")
            	System.out.println("Il tuo compito è di eliminare tutti i Fuorilegge ed il Rinnegato, "
            			+ "riportando così l’ordine in città. ");
            else if(giocatore.getRuolo() == "Rinnegato")
            	System.out.println("Vuoi diventare il nuovo Sceriffo; il tuo compito è di rimanere "
            			+ "l’ultimo personaggio in gioco. ");
            else if(giocatore.getRuolo() == "Fuorilegge")
            	System.out.println("Vuoi eliminare lo Sceriffo, ma non hai scrupoli ad "
            			+ "eliminare un altro fuorilegge per incassare le taglie sulla sua testa! ");
            else 
            	System.out.println("Aiuti e Proteggi lo Sceriffo, e Persegui i suoi stessi obiettivi, "
            			+ "anche a costo della tua vita! ");
            System.out.println();
        }
    }
    
    public static void rivelaSceriffo(ArrayList<Giocatore> giocatori) {
    	for (int i = 0; i < giocatori.size(); i++) {
            Giocatore giocatore = giocatori.get(i);
            if(giocatore.getRuolo() == "Sceriffo")
            	System.out.println("Il giocatore numero " + (i + 1) + "è lo Sceriffo! ");
        }
    }
    

    private static boolean isSceriffoPresente(ArrayList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getRuolo().equals("Sceriffo")) {
                return true;
            }
        }
        return false;
    }

    private static boolean areFuorileggiPresenti(ArrayList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            String ruolo = giocatore.getRuolo();
            if (ruolo.equals("Fuorilegge") || ruolo.equals("Rinnegato")) {
                return true;
            }
        }
        return false;
    }
    
    public static int findDistanza(Giocatore giocatore, int g) {
    	
    	return d;
    }

    
    public static void iniziaScontro(ArrayList<Giocatore> giocatori) {
        rivelaRuoli(giocatori);
        Mazzo mazzo = new Mazzo();
        mazzo.mescola();

        // Pesca carte per ogni giocatore in base ai suoi PF
        for (Giocatore giocatore : giocatori) {
            List<Carta> carteGiocatore = mazzo.pescaCarteCasuali(giocatore.getPF());
            giocatore.setCarte(carteGiocatore);
        }

        boolean sceriffoPresente = true;
        boolean fuorileggiPresenti = true;

        do {
        	for (Giocatore giocatore : giocatori) {
                turno(giocatore, giocatori); // turno per ciascun giocatore
                //giocatore.setCarte(Mazzo.pescaCarteCasuali(2).add);
            }
            sceriffoPresente = isSceriffoPresente(giocatori);
            fuorileggiPresenti = areFuorileggiPresenti(giocatori);
        } while (sceriffoPresente && fuorileggiPresenti);
    }
    
    public static void turno(Giocatore giocatore, ArrayList<Giocatore> giocatori) {
    	int scelta = 0;
        final String[] SCELTE = {
                "Scopri chi è lo Sceriffo",
                "Che carte possiedo?(in mano/equipaggiate)",
                "Trova distanza da un altro giocatore"
        };

        MyMenu menu = new MyMenu("Seleziona la difficolta della partita", SCELTE);
        do {
        	System.out.println("Iniziamo una nuova partita di morra cinese! ");
        	System.out.println("Il computer sarà onesto durante lo svolgimento dello scontro.");
        	
			scelta = menu.scegli();
            switch (scelta) {
                case 1:
                	rivelaSceriffo(giocatori);
                    break;
                case 2:
                	System.out.println("In questo momento possiedi: ");
                	System.out.println("[Carte = " + giocatore.getCarte() + ", Carte Equipaggiate = " + giocatore.getCarteEquipaggiate() + "]");
                	break;
                case 3:
                	int num = InputDati.leggiIntero("Di quale giocatore vuoi conoscere la distanza? ", 1, giocatori.size());
                	int g = 0;
                	for (int i = 0; i < giocatori.size(); i++) {
                        if(i == num)
                        	g = i;
                    }
                	d = findDistanza(giocatore, g);
                	System.out.println("La distanza che ti separa da questi è " + d;
                    break;
                default:
                    return;
            }
        }while(scelta != 0);
    }
}


