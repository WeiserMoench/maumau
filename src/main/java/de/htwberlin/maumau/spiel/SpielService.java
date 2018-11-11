package de.htwberlin.maumau.spiel;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

public interface SpielService {

    Spiel anlegenSpiel();

    /**
     * Diese Methode sorgt dafuer, dass ein gewuenschtes Kartenblatt dem Spiel hinzugefuegt wird.
     *
     * @param spiel       - Es wird das zuvor erzeugte Spiel uebergeben
     * @param kartenblatt - int Wert aus Listener
     */
    List<Karte> auswaehlenKartendeck( int kartenblatt);//Klärung ob Spiel drinne ist oder nicht

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
    Karte ziehenKarteVomZiehstapel(List<Karte> ziehStapel);

    List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte);


    void austeilenStart(List<Karte> kartenDeck, List<Spieler> spielerListe, int durchgaenge);

    void zuZiehendeKarte(int anzahl, List<Karte> karteStapel, Spieler spieler);


    /**
     * Diese Methode entfernt eine Handkarte aus den Handkarten des Spielers und legt diese auf den Ablagestapel.
     *
     * @param spiel            - Das laufende Spiel wird uebergeben, damit die Karte auf den Ablagestapel kommen kann.
     * @param spieler          - Der spieler, der an der Reihe ist.
     * @param sichtbarLegen    - boolean, der angibt, ob die Karte sichtbar gelegt werden soll.
     * @param abzulegendeKarte - Die Karte, die aus dem Handkartenstapel herausgenommen und auf den Ablagestapel gelegt werden soll
     */
    Spiel legenKarteAufAblageStapel(Spiel spiel, Spieler spieler, List<Karte> kartenAblagestapel, Karte karte);

    boolean spielerLegtKarteAb(Spieler spieler, Karte karte);


    /**
     * Methode bietet die Moeglichkeit, die Spielrichtung zu aendern.
     *
     * @param spiel - Das Spiel, indem die Spielrichtung geaendert werden soll.
     */
    Spiel aendernSpielrichtung(Spiel spiel);//da methode nur aufgerufen wird, wenn benoetigt, tauscht er einfach true gegen fals, somit ist kein Parameter erforderlich


    /**
     * Farbe des Spiels wird gewechselt.
     *
     * @param spiel     - Laufendes Spiel.
     * @param neueFarbe - 0 Herz, 1 Karo, 2 Pik, 3 Kreuz.
     */
    Spiel aendernFarbe(Spiel spiel, String neueFarbe);

    void ermittleSpielende(List<Spieler> spielerList);

    /**
     * Prüfe handkarte gleich 1
     * auf mau gesetzt
     * true weiter
     * false ziehe karte
     *
     * @param spieler
     */
    void pruefeAufMau(Spieler spieler);


    boolean istMauNoetig(Spieler spieler);

    /**
     * Wenn der Spieler nur eine Karte auf der Hand hat, wird is Mau gesetzt
     * @param spieler
     */
    void setzeMau(Spieler spieler);

    int anzahlStartkartenbestimmen(List<Spieler> spielerListe);
}
