package de.htwberlin.maumau.spiel;

import de.htwberlin.maumau.karten.entity.Kartendeck;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

public interface SpielService {

    void anlegenSpiel();

    /**
     * Diese Methode sorgt dafuer, dass ein gewuenschtes Kartenblatt dem Spiel hinzugefuegt wird.
     *
     * @param spiel       - Es wird das zuvor erzeugte Spiel uebergeben
     * @param kartenblatt - int Wert aus Listener
     */
    void auswaehlenKartendeck(Spiel spiel, int kartenblatt);

    /**
     * Diese Methode soll die Anzahl der benoetigten Karten aus dem
     * Ziehstapel entnehmen und den Handkarten des Spielers hinzufuegen.
     * Sie wird auch benutzt, um das Kartenausteilen am Spielbeginn zu initiieren.
     *
     * @param spiel        - Das Spiel wird uebergeben, daraus wird der Ziehkartenstapel und
     *                     die Anzahl der zu ziehenden Karten extrahiert.
     * @param spieler      - Der spieler der nun an der Reihe ist und die neuen Karten bekommen soll.
     * @param anzahlKarten - Es ist 0 anzugeben, wenn der spieler aus Voraktionen, bspw. Strafkarten, ziehen muss.
     *                     Wenn der spieler nicht legen kann und daher ziehen muss, ist die 1 zu uebergeben.
     */
    void ziehenKarteVomZiehstapel(Spiel spiel, Spieler spieler, int anzahlKarten);

    void austeilenStart(Kartendeck kartenDeck, List<Spieler> spielerListe);


    /**
     * Diese Methode prueft, ob der am Zug seiende spieler, vor dem Auslegen eigener Karten, Karten ziehen muss.
     *
     * @param spiel - Das laufende Spiel wird übergeben.
     * @return - true, wenn gezogen werden muss, sonst false.
     */
    boolean ziehenVonKarteNotwendig(Spiel spiel);


    /**
     * Diese Methode entfernt eine Handkarte aus den Handkarten des Spielers und legt diese auf den Ablagestapel.
     *
     * @param spiel            - Das laufende Spiel wird uebergeben, damit die Karte auf den Ablagestapel kommen kann.
     * @param spieler          - Der spieler, der an der Reihe ist.
     * @param sichtbarLegen    - boolean, der angibt, ob die Karte sichtbar gelegt werden soll.
     * @param abzulegendeKarte - Die Karte, die aus dem Handkartenstapel herausgenommen und auf den Ablagestapel gelegt werden soll
     */
    void legenKarteAufAblageStapel(Spiel spiel, Spieler spieler, boolean sichtbarLegen, int abzulegendeKarte);


    /**
     * Diese Methode aendert die Anzahl der Karten, die der naechste spieler ziehen muss.
     *
     * @param spiel        - Das Spiel, indem die Anzahl der zu ziehenden Karten geaendert werden soll.
     * @param anzahlKarten - Die Anzahl zusaetzlich zu ziehender Karten.
     */
    void aendernAnzahlZiehkarten(Spiel spiel, int anzahlKarten);


    /**
     * Methode bietet die Moeglichkeit, die Spielrichtung zu aendern.
     *
     * @param spiel - Das Spiel, indem die Spielrichtung geaendert werden soll.
     */
    void aendernSpielrichtung(Spiel spiel);//da methode nur aufgerufen wird, wenn benoetigt, tauscht er einfach true gegen fals, somit ist kein Parameter erforderlich


    /**
     * Farbe des Spiels wird gewechselt.
     *
     * @param spiel     - Laufendes Spiel.
     * @param neueFarbe - 0 Herz, 1 Karo, 2 Pik, 3 Kreuz.
     */
    void aendernFarbe(Spiel spiel, int neueFarbe);

    void ermittleSpielende(List<Spieler> spielerList);
}
