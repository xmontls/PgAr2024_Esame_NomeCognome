package it.unibs.fp.arnaldowest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Partita {

    public static void rivelaRuoli(ArrayList<Giocatore> giocatori) {
    	System.out.println();
		System.out.println("Rivelazione momentanea dei ruoli: non fare vedere a chi ti sta in parte il tuo ruolo!! ;)");
        for (Giocatore giocatore : giocatori) {
            String nome = InputDati.leggiStringaNonVuota("Qual è il tuo nome? ").toLowerCase();
            giocatore.setNome(nome);
            System.out.println("Giocatore " + nome + " ha ruolo: " + giocatore.getRuolo());
            if(giocatore.getRuolo().equals("Sceriffo"))
            	System.out.println("Il tuo compito è di eliminare tutti i Fuorilegge ed il Rinnegato, "
            			+ "riportando così l’ordine in città. ");
            else if(giocatore.getRuolo().equals("Rinnegato"))
            	System.out.println("Vuoi diventare il nuovo Sceriffo; il tuo compito è di rimanere "
            			+ "l’ultimo personaggio in gioco. ");
            else if(giocatore.getRuolo().equals("Fuorilegge"))
            	System.out.println("Vuoi eliminare lo Sceriffo, ma non hai scrupoli ad "
            			+ "eliminare un altro fuorilegge per incassare le taglie sulla sua testa! ");
            else 
            	System.out.println("Aiuti e Proteggi lo Sceriffo, e Persegui i suoi stessi obiettivi, "
            			+ "anche a costo della tua vita! ");
            System.out.println();
        }
    }
    
    public static void printCarte(Giocatore g) {
        List<Carta> carteInMano = g.getCarte();
        
        // Mostra le carte in mano al giocatore
        System.out.println("Carte disponibili:");
        for (int i = 0; i < carteInMano.size(); i++) {
            System.out.println((i + 1) + ". " + carteInMano.get(i));
        }
    }
    
    public static void rivelaSceriffo(ArrayList<Giocatore> giocatori) {
    	for (Giocatore giocatore : giocatori) {
            if(giocatore.getRuolo().equals("Sceriffo"))
            	System.out.println("Il giocatore " + giocatore.getNome() + " è lo Sceriffo! ");
        }
    }
    
    /**
     * Se lo Sceriffo elimina personalmente un Vice, lo Sceriffo deve scartare tutte
	 * le carte che ha in mano e quelle in gioco davanti a sé.
     */
    public static void eliminiViceESeiSceriffo(Giocatore giocatoreCheHaEliminato, Giocatore giocatoreEliminato, Mazzo mazzo) {
        	if(giocatoreEliminato.getRuolo().equals("Vice")) {
        		System.out.println("Il giocatore " + giocatoreEliminato.getNome() + " è stato eliminato! ERa un Vice!!!");
                System.out.println("Il giocatore che lo ha eliminato deve scartare tutte le carte che ha in mano.");
                
                // Scarta tutte le carte
                giocatoreCheHaEliminato.scartaTutteLeCarte();
        	}
    }
    
    /**
     * Chiunque elimini un Fuorilegge (anche un altro Fuorilegge!) ha diritto a pescare subito 
     * una ricompensa di 3 carte dal mazzo.
     */
    public static void eliminiFuorilegge(Giocatore giocatoreCheHaEliminato, Giocatore giocatoreEliminato, Mazzo mazzo) {
    	if(giocatoreEliminato.getRuolo().equals("Fuorilegge")) {
    		System.out.println("Il giocatore " + giocatoreEliminato.getNome() + " è stato eliminato! Era un FuoriLegge!!!");
            System.out.println("Il giocatore che lo ha eliminato riceve una ricompensa di 3 carte dal mazzo.");
            
            // Pesca tre carte dal mazzo come ricompensa
            List<Carta> ricompensa = mazzo.pescaCarteCasuali(3);
            giocatoreCheHaEliminato.aggiungiCarte(ricompensa);
    	}
    }

    
    public static boolean isSceriffoPresente(ArrayList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getRuolo().equals("Sceriffo")) {
                return true;
            }
        }
        return false;
    }

    public static boolean areFuorileggiPresentiWORinnegato(ArrayList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            String ruolo = giocatore.getRuolo();
            if (ruolo.equals("Fuorilegge")) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean areFuorileggiPresenti(ArrayList<Giocatore> giocatori) {
        for (Giocatore giocatore : giocatori) {
            String ruolo = giocatore.getRuolo();
            if (ruolo.equals("Fuorilegge") || ruolo.equals("Rinnegato")) {
                return true;
            }
        }
        return false;
    }
    
    public static int findDistanza(Giocatore giocatore1, Giocatore giocatore2) {
        int distanzaGiocatore1 = giocatore1.getDistanza();
        int distanzaGiocatore2 = giocatore2.getDistanza();
        int distanzaAssoluta = Math.abs(distanzaGiocatore1 - distanzaGiocatore2);
        
        // Se la somma delle distanze è maggiore di 3, calcola il percorso più breve
        if (distanzaGiocatore1 * distanzaGiocatore2 > 0 && distanzaAssoluta > 3) {
            int distanzaPercorsoBreve = Math.min(Math.abs(distanzaGiocatore1), Math.abs(distanzaGiocatore2));
            return distanzaPercorsoBreve;
        } else {
            return distanzaAssoluta;
        }
    }

    public static boolean iniziaScontro(ArrayList<Giocatore> giocatori, boolean torneo, int countP) {
    	if(torneo == false)
    		Collections.shuffle(giocatori);
    	else {
    		if(countP == 0) 
    			Collections.shuffle(giocatori); // se è la prima partita del torneo, è utile, altrimenti no, perche 
    											// i ruoli varierebbero e i punteggi sarbbero sbagliati
    	}
    	boolean rinneUltimo = false; // servirà per assicurarsi se il rinnegato è l ultimo a morire
    	ArrayList<Carta> carteScartate = new ArrayList<Carta>();
        rivelaRuoli(giocatori);
        Mazzo mazzo = new Mazzo();
        mazzo.creaMazzo();
        mazzo.mescola();

        // Pesca carte per ogni giocatore in base ai suoi PF
        for (Giocatore giocatore : giocatori) {
        	if(torneo == true)
        		giocatore.setSbleuri(giocatore.getSbleuri() - 50);
            List<Carta> carteGiocatore = mazzo.pescaCarteCasuali(giocatore.getPF());
            giocatore.setCarte(carteGiocatore);
        }

        boolean sceriffoPresente = true;
        boolean fuorileggiPresenti = true;

        do {
        	for (Giocatore giocatore : giocatori) {
        		System.out.println("Turno di " + giocatore.getNome());
                turno(giocatore, giocatori, mazzo, carteScartate, rinneUltimo); // turno per ciascun giocatore
            }
            sceriffoPresente = isSceriffoPresente(giocatori);
            fuorileggiPresenti = areFuorileggiPresenti(giocatori);
        } while (sceriffoPresente && fuorileggiPresenti);
        return rinneUltimo;
    }
    
    public static void turno(Giocatore giocatore, ArrayList<Giocatore> giocatori, Mazzo mazzo, ArrayList<Carta> carteScartate, boolean rinneUltimo) {
    	System.out.println();
    	System.out.println("Inizia col pescare 2 carte! ");
    	giocatore.aggiungiCarte(mazzo.pescaCarteCasuali(2));
    	if (mazzo.vuoto() == true ) {
    		mazzo.aggiungiCarte(carteScartate);
            mazzo.mescola();
            System.out.println("Il mazzo è stato rimescolato con le carte scartate!");
        }
    	
    	int scelta = 0;
        final String[] SCELTE = {
                "Scopri chi è lo Sceriffo",
                "Che carte possiedo?(in mano/equipaggiate)",
                "Trova distanza da un altro giocatore",
                "Trova i miei punti attuali",
                "Provoca avversario",
                "Gioca carte"
        };

        MyMenu menu = new MyMenu("Seleziona la difficolta della partita", SCELTE);
        int count = 0;
        do {
        	System.out.println();
			scelta = menu.scegli();
            switch (scelta) {
                case 1:
                	rivelaSceriffo(giocatori);
                    break;
                case 2:
                	System.out.println("In questo momento possiedi: ");
                	printCarte(giocatore);
                	break;
                case 3:
                	String nome = InputDati.leggiStringaNonVuota("Di quale giocatore vuoi conoscere la distanza?(inserire nome giocatore) ").toLowerCase();
                	Giocatore g = null;
                	for (Giocatore player : giocatori) {
                        if(player.getNome().equals(nome))
                        	g = player;
                    }
                	int d = findDistanza(giocatore, g);
                	System.out.println("La distanza che ti separa da questi è " + d);
                	if(d == 0)
                		System.out.println("Questo giocatore sei tu^^");
                    break;
                case 4:
                	System.out.println("I tuoi PF attuali sono: " + giocatore.getPF());
                	break;
                case 5:
                	giocaCarte(giocatore, count, giocatori, rinneUltimo);
                	break;
                case 6:
                	provocaAvversario(giocatore, giocatori);
                	break;
                default:
                    return;
            }
        }while(scelta != 0);
    }
    
    public static void provocaAvversario(Giocatore giocatore, ArrayList<Giocatore> giocatori) {
    	String provocazione = InputDati.leggiStringaNonVuota("Quale sarebbe la sua provocazione? Che verrà appuratamente legga in Brixiano? ");
    	String nome = InputDati.leggiStringaNonVuota("Hah! Chi vorresti provocare? ");
    	for(Giocatore g: giocatori) {
    		if(nome.equals(g.getNome()))
    			System.out.println("Giocatore " + g.getNome() + "!! " + giocatore.getNome() + "ti sta provocando!!");
    			System.out.println("Ecco il messaggio rimortato!! " + LeggiBrixiano.encrypted(provocazione));
    			System.out.println(g.getNome() + " non ci ha capito una mazza, ma ora è molto irritato e "
    					+ "ce l'avrà a morte con te!!");
    	}
    	
    }
    
    public static void giocaCarte(Giocatore g, int count, ArrayList<Giocatore> giocatori, boolean rinneUltimo) {
    	printCarte(g);
    	System.out.println("Puoi giocare solo 1 carta BANG! per turno e"
    		    + " puoi avere in gioco solo 1 arma.");
		System.out.println();
		int indiceCarta = InputDati.leggiIntero("Seleziona l'indice della carta che vuoi giocare: ", 1, g.getCarte().size()); 

		Carta cartaDaGiocare = g.getCarte().get(indiceCarta);

		if (cartaDaGiocare.getTipo().equals("Equipaggiabili")) {
		    if (g.getCarteEquipaggiate().isEmpty()) {
		        g.getCarteEquipaggiate().add(cartaDaGiocare);
		        System.out.println("La carta è stata equipaggiata con successo!");
		    } else { // nel caso c fosse un altra carta gia equipaggiata
		        Carta cartaPrecedente = g.getCarteEquipaggiate().get(0);
		        g.setCarteEquipaggiate(new ArrayList<>());
		        g.getCarteEquipaggiate().add(cartaDaGiocare);
		        System.out.println("La carta è stata equipaggiata con successo! E' stata inoltre rimossa la carta equipaggiata in precedenza.");
		        // scarta la carta sostituita
		        g.rimuoviCarta(cartaPrecedente);
		    }
		}

    	else {
    		if(cartaDaGiocare.getNome().equals("BANG!")) {
    			if(count == 0) {
    				String nomeBersaglio = InputDati.leggiStringaNonVuota("Contro quale giocatore vuoi sferrare il tuo attacco? ");
    				Giocatore bersaglio = null;
    				for(Giocatore player: giocatori) {
    					if(player.getNome().equals(nomeBersaglio))
    						bersaglio = player;
    				}
    				int d = findDistanza(bersaglio, g);
    				if(d == 1) {
    					System.out.println(g.getNome() + " ha sparato a " + bersaglio.getNome());
    					System.out.println("Chiamiamo urgentemente il giocatore " + bersaglio.getNome() + "!!");
    					controlloCartaMancato(bersaglio);
    					
    					rinneUltimo = controlloEliminazione(bersaglio, giocatori);
    				} else if(d == 0) {
    					System.out.println("Questo giocatore sei tu..");
    				} else {
    					if (!g.getCarteEquipaggiate().isEmpty()) {
    					    Carta cartaEquipaggiata = g.getCarteEquipaggiate().get(0); // Ottieni la carta equipaggiata
    					    int distanzaArma = cartaEquipaggiata.getDistanza(); // Ottieni la distanza dell'arma

    					    if (d == 1 || distanzaArma >= d) {
    					    	System.out.println(g.getNome() + " ha sparato a " + bersaglio.getNome());
    	    					System.out.println("Chiamiamo urgentemente il giocatore " + bersaglio.getNome() + "!!");
    	    					controlloCartaMancato(bersaglio);
    	    					rinneUltimo =  controlloEliminazione(bersaglio, giocatori);
    					    } else {
    					        System.out.println("Non hai equipaggiato un'arma adatta allo scontro..");
    					    }
    					} else {
    					    System.out.println("Non hai equipaggiato un'arma per sparare.");
    					}
    				}
    				
    			} else {
    				System.out.println("Hai già giocato il numero max di carte BANG! per questo turno!");
    			}
    			count++;
    		}
    	}
    	scartaCarte(g);
    }
    
    public static void controlloCartaMancato(Giocatore g) {
        List<Carta> carteInMano = g.getCarte();
        for (Carta carta : carteInMano) {
            if (carta.getNome().equals("Mancato")) {
                System.out.println("Il giocatore ha una carta Mancato e può usarla per evitare un attacco!");
                // Rimuovi la carta Mancato dalle carte del giocatore
                g.rimuoviCarta(carta);
            } else {
            	System.out.println("Il giocatore non ha la carta Mancato e subirà l'attacco.");
				g.setPF(g.getPF() - 1);
            }
        }
        System.out.println();
    }

    
    public static boolean controlloEliminazione(Giocatore g, ArrayList<Giocatore> giocatori) {
    	boolean rinneUltimo = false;
    	System.out.println();
    	if(g.getPF() == 0) {
    		System.out.println("Il giocatore " + g.getNome() + " è stato eliminato!!");
    		System.out.println(g.getNome() + " era un " + g.getRuolo());
    		giocatori.remove(g);
    		if(g.getRuolo().equals("Rinnegato") && (areFuorileggiPresentiWORinnegato(giocatori) == false))
    			rinneUltimo = true;
    	}
    	return rinneUltimo;
    }
    
    public static void scartaCarte(Giocatore g) {
        int numeroCarte = g.getCarte().size(); // Ottieni il numero di carte del giocatore
        if (g.getRuolo().equals("Sceriffo")) {
            if (numeroCarte > 5) {
                System.out.println("Devi scartare " + (numeroCarte - 5) + " carte.");
                printCarte(g); // Mostra le carte del giocatore
                System.out.println("Seleziona l'indice della carta da scartare: ");
                int indice = InputDati.leggiIntero("Indice: ", 0, numeroCarte - 1);
                Carta cartaDaRimuovere = g.getCarte().get(indice);
                g.rimuoviCarta(cartaDaRimuovere); // Rimuovi la carta selezionata
            }
        } else {
            if (numeroCarte > 4) {
                System.out.println("Devi scartare " + (numeroCarte - 4) + " carte.");
                printCarte(g); // Mostra le carte del giocatore
                System.out.println("Seleziona l'indice della carta da scartare: ");
                int indice = InputDati.leggiIntero("Indice: ", 0, numeroCarte - 1);
                Carta cartaDaRimuovere = g.getCarte().get(indice);
                g.rimuoviCarta(cartaDaRimuovere); // Rimuovi la carta selezionata
            }
        }
    }

    
    public static List<Carta> selezionaCarteDaScartare(Giocatore g) {
        List<Carta> carteInMano = g.getCarte();
        int limiteCarte = (g.getRuolo().equals("Sceriffo")) ? 5 : 4;
        return carteInMano.subList(limiteCarte, carteInMano.size());
    }
    
}


