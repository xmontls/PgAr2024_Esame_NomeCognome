package it.unibs.fp.arnaldowest;

public class LeggiBrixiano {

    public static String encrypted(String p) {
    	String[] chiave = {"bugged", "resolution", "into", "xplosive", "impossible", "algorithm"};
        String[] parole = p.split(" ");

        StringBuilder risultato = new StringBuilder();

        for (int i = 0; i < parole.length; i++) {
            String parolaCifrata = cifraParola(parole[i], chiave[i]);
            risultato.append(parolaCifrata).append(" ");
        }

        return risultato.toString().trim();
    }

    private static String cifraParola(String parola, String chiave) {
        // Il brixiano è difficile ;-;

    	StringBuilder risultato = new StringBuilder();

        for (int i = 0; i < parola.length(); i++) {
            char carattere = parola.charAt(i);
            if (Character.isLetter(carattere)) {
                int indice = chiave.indexOf(carattere);
                char carattereDecifrato = (indice != -1) ? (char) ('a' + indice) : carattere;
                risultato.append(carattereDecifrato);
            } else {
                risultato.append(carattere);
            }
        }

        return risultato.toString();
    }
}
