package it.unibs.fp.arnaldowest;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.Collections;

public class ArnaldoWestMain {

    public static void main(String[] args) {
    	
        System.out.println("Benvenuti nell'ArnaldoWest!");
        System.out.println();
        String partita = InputDati.leggiStringaNonVuota("Vuoi giocare in modaità torneo?(si/no)").toLowerCase();
        boolean torneo;
        if(partita.equals("si")) {
        	System.out.println("Modalità torneo attivata!");
        	torneo = true;
        } else
        	torneo = false;
        
        boolean stop;
        if(torneo == true)
        	stop = false; // per fermare il torneo
        else
        	stop = true; // in caso non si faccia torneo si finisce dopo una partita
        
        System.out.println("Iniziamo una nuova partita!");

        int countP = 0; // conteggio partita(utile nel torneo)
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
        
        ArrayList<Giocatore> torneoArray = giocatori;

        if(torneo == true) {
        	for(Giocatore g: giocatori) {
        		g.setSbleuri(500);
        	}
        }
        do {	
        	if(torneo == true) {
        		Collections.shuffle(ruoli); // riassegnamo ruoli diversi a ogni giocatore
        		int i = 0;
        		for(Giocatore g: giocatori) {
    				g.setRuolo(ruoli.get(i));
    				i++;
    			}
        	}
	        // Avvio della partita
	        boolean rinneUltimo = Partita.iniziaScontro(giocatori, torneo, countP);
	
	        // Dichiarazione del risultato 
	        if(Partita.isSceriffoPresente(giocatori) == true) {
	        	System.out.println("Vincono la partita lo Sceriffo e i suoi Vice!!");
	        	if(torneo == true) {
	        		System.out.println("Vengono assegnati i punteggi!");
	        		System.out.println();
	        		if(torneoArray.size() == 4) {
	        			System.out.println("Lo sceriffo riceve 1400§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Sceriffo"))
	        					g.setSbleuri(g.getSbleuri() + 1400);
	        			}
	        			if(rinneUltimo == true) {
	        				System.out.println("Il rinnegato, eliminato per ultimo, riceve 250§!");
	        				for(Giocatore g: giocatori) {
	            				if(g.getRuolo().equals("Rinnegato"))
	            					g.setSbleuri(g.getSbleuri() + 250);
	            			}
	        			}
	        				
	        		} else if(torneoArray.size() == 5) {
	        			System.out.println("Lo sceriffo riceve 1200§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Sceriffo"))
	        					g.setSbleuri(g.getSbleuri() + 1200);
	        			}
	        			System.out.println("Il Vice riceve 1200§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Vice"))
	        					g.setSbleuri(g.getSbleuri() + 1200);
	        			}
	        			if(rinneUltimo == true) {
	        				System.out.println("Il rinnegato, eliminato per ultimo, riceve 300§!");
	        				for(Giocatore g: giocatori) {
	            				if(g.getRuolo().equals("Rinnegato"))
	            					g.setSbleuri(g.getSbleuri() + 300);
	            			}
	        			}
	        		} else if(torneoArray.size() == 6) {
	        			System.out.println("Lo sceriffo riceve 1600§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Sceriffo"))
	        					g.setSbleuri(g.getSbleuri() + 1600);
	        			}
	        			System.out.println("Il Vice riceve 1600§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Vice"))
	        					g.setSbleuri(g.getSbleuri() + 1600);
	        			}
	        			if(rinneUltimo == true) {
	        				System.out.println("Il rinnegato, eliminato per ultimo, riceve 350§!");
	        				for(Giocatore g: giocatori) {
	            				if(g.getRuolo().equals("Rinnegato"))
	            					g.setSbleuri(g.getSbleuri() + 350);
	            			}
	        			}
	        		}else {
	        			System.out.println("Lo sceriffo riceve 1200§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Sceriffo"))
	        					g.setSbleuri(g.getSbleuri() + 1200);
	        			}
	        			System.out.println("Il Vice riceve 1200§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Vice"))
	        					g.setSbleuri(g.getSbleuri() + 1200);
	        			}
	        			if(rinneUltimo == true) {
	        				System.out.println("Il rinnegato, eliminato per ultimo, riceve 400§!");
	        				for(Giocatore g: giocatori) {
	            				if(g.getRuolo().equals("Rinnegato"))
	            					g.setSbleuri(g.getSbleuri() + 400);
	            			}
	        			}
	        		}
	        			
	        	}
	        } else if(Partita.areFuorileggiPresenti(giocatori) == true) {
	        	System.out.println("Vincono la partita i Fuorilegge, che restano ancora in circolazione!!");
	        	if(torneo == true) {
	        		System.out.println("Vengono assegnati i punteggi!");
	        		System.out.println();
	        		if(torneoArray.size() == 4) {
	        			System.out.println("I Fuorilegge ricevono 1600§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Fuorilegge"))
	        					g.setSbleuri(g.getSbleuri() + 1600);
	        			}
	        		}else if(torneoArray.size() == 5) {
	        			System.out.println("I Fuorilegge ricevono 1800§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Fuorilegge"))
	        					g.setSbleuri(g.getSbleuri() + 1800);
	        			}
	    			} else if(torneoArray.size() == 6) {
	        			System.out.println("I Fuorilegge ricevono 1500§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Fuorilegge"))
	        					g.setSbleuri(g.getSbleuri() + 1500);
	        			}
	    			}else {
	        			System.out.println("I Fuorilegge ricevono 1700§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Fuorilegge"))
	        					g.setSbleuri(g.getSbleuri() + 1700);
	        			}
	    			}
	        	}
	       } else {
	        	System.out.println("Vince la partita il Rinnegato, che diventa il nuovo Sceriffo in città!!");
	        	if(torneo == true) {
	        		System.out.println("Vengono assegnati i punteggi!");
	        		System.out.println();
	        		if(torneoArray.size() == 4) {
	        			System.out.println("Il Rinnegato riceve 2200§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Rinnegato"))
	        					g.setSbleuri(g.getSbleuri() + 2200);
	        			}
	        		}else if(torneoArray.size() == 5) {
	        			System.out.println("Il Rinnegato riceve 2400§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Rinnegato"))
	        					g.setSbleuri(g.getSbleuri() + 2400);
	        			}
	    			} else if(torneoArray.size() == 6) {
	        			System.out.println("Il Rinnegato riceve 2600§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Rinnegato"))
	        					g.setSbleuri(g.getSbleuri() + 2600);
	        			}
	    			}else {
	        			System.out.println("Il Rinnegato riceve 2800§!");
	        			for(Giocatore g: giocatori) {
	        				if(g.getRuolo().equals("Rinnegato"))
	        					g.setSbleuri(g.getSbleuri() + 2800);
	        			}
	    			}
	        	}
	       }
	        
	        System.out.println();
	        if(torneo == true) {
	        	System.out.println("Punteggi attuali: ");
	            for(Giocatore g: giocatori) {
	    			System.out.println(g);
	    		}
	            System.out.println();
	            
	         // utente vuole o non vuole fare un altra partita la partita
	            String risposta = null;
	            do {
	                risposta = InputDati.leggiStringaNonVuota("Vuoi iniziare una'altra partita? (si/no) ").toLowerCase();
	                if (risposta.equals("no"))
	                    stop = true;
	                else if (!risposta.equals("si") && !risposta.equals("no"))
	                    System.out.println("Non è stata inserita una risposta valida, riprovare. ");
	            } while (!risposta.equals("si") && !risposta.equals("no"));
	
	        }
	        countP++;
       } while(stop == false);
        
        // calcolo punteggio
        for(Giocatore g: giocatori) {
			g.setPunteggio(g.getSbleuri()/countP);
		}
        
       if(torneo == true)
    	   Classifica.salvaInXml("classifica.xml", giocatori, countP);
    }
}