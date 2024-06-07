package it.unibs.fp.arnaldowest;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Classifica {
    public static void salvaInXml(String filePath, List<Giocatore> giocatori, int countP) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Elemento radice
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Classifica");
            doc.appendChild(rootElement);

            for (Giocatore giocatore : giocatori) {
                Element giocatoreElement = doc.createElement("Giocatore");

                Element nome = doc.createElement("Nome");
                nome.appendChild(doc.createTextNode(giocatore.getNome()));
                giocatoreElement.appendChild(nome);

                Element totSbleuri = doc.createElement("TotSbleuri");
                totSbleuri.appendChild(doc.createTextNode(String.valueOf(giocatore.getSbleuri())));
                giocatoreElement.appendChild(totSbleuri);

                Element partiteGiocate = doc.createElement("PartiteGiocate");
                partiteGiocate.appendChild(doc.createTextNode(String.valueOf(countP)));
                giocatoreElement.appendChild(partiteGiocate);

                Element punteggio = doc.createElement("Punteggio");
                punteggio.appendChild(doc.createTextNode(String.valueOf(giocatore.getPunteggio())));
                giocatoreElement.appendChild(punteggio);

                rootElement.appendChild(giocatoreElement);
            }

            // Scrittura del contenuto in un file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("File XML salvato!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
