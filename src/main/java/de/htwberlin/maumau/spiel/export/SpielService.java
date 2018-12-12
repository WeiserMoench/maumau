/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.spiel.export;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

public interface SpielService {

    /** Diese Methode legt ein Spiel an
     *
     * @param spielerliste Die Liste aller Spielern
     * @param erweiterteRegeln Welche Regeln fuer das Spiel verwendet wird
     * @return Gibt das neu angelegte Spiel zurück
     */
    Spiel anlegenSpiel(List<String> spielerliste, boolean erweiterteRegeln);


    /**
     * Diese Methode entnimmt eine Karte aus dem Ziehstapel
     *
     * @param spiel - Das Spiel
     * @return - Das veraenderte Spiel
     */
    Spiel ziehenKarteVomZiehstapel(Spiel spiel);

    /** Prueft ob die Karte gelegt werden darf und wenn ja, fuegt diese hinzu
     *
     * @param zulegendeKarte Die Karte, die gelegt werden will
     * @param spieler Der Spieler der die Karte legen will
     * @param spiel Das spielende Spiel
     * @return Das Spiel
     */
    Spiel legeKarte(Karte zulegendeKarte, Spieler spieler, Spiel spiel);

    /** Setzt die Farbe im Spiel um
     *
     * @param spiel Das Spiel
     * @param farbe Die Farbe, die gesetzt wurde
     * @return Das Spiel
     */
    Spiel farbeGewaehlt(Spiel spiel, Farbe farbe);


//    /**
//     * Diese Methode entfernt eine Karte aus einem Stapel
//     *
//     * @param karteStapel - Der Stapel, von dem die Karte entfernt werden soll
//     * @param karte - Die zu entfernende Karte
//     * @return Liste der restlichen Karten
//     */
//    List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte);

//    /**
//     * Diese Methode wird beim Start eines Spieles ausgefuehrt und verteilt die Handkarten
//     *
//     * @param kartenDeck - Liste an Karten, die den Kartenstapel darstellen, von dem verteilt werden soll
//     * @param spielerListe - Liste mit Spielern aus dem Spiel, die die Karten erhalten sollen
//     * @param durchgaenge - Anzahl der Durchgaenger des Verteilens (Startanzahl Handkarten)
//     * @return Liste der restlichen Karten, stellt Ziehstapel des Spieles dar
//     */
//    List<Karte> austeilenVonKarten(List<Karte> kartenDeck, List<Spieler> spielerListe, int durchgaenge);

    /** Setzt den Nächsten Spieler als Aktiv
     *
     * @param spiel Das Spiel
     * @return Das Spiel
     */
    Spiel naechsterSpieler(Spiel spiel);

    /**
     * Diese Methode zieht Karten vom Ablagestapel und sorgt dafuer, dass der Spieler sie als Handkarten bekommt
     *
     * @param anzahl - Anzahl der Karten, die gezogen werden sollen
     * @param karteStapel - Liste an Karten, von denen gezogen werden soll (Ziehstapel)
     * @param spieler - Spieler, der die Karten ziehen soll
     * @return Liste der restlichen Karten, stellt Ziehstapel des Spieles dar
     */
    List<Karte> karteZiehen(int anzahl, List<Karte> karteStapel, Spieler spieler);

//    /**
//     * Diese Methode entfernt eine Handkarte aus den Handkarten des Spielers und legt diese auf den Ablagestapel.
//     *
//     * @param spieler - Der Spieler, bei dem die Karte entfernt werden soll
//     * @param kartenAblagestapel - der Ablagestapel, auf den die Karte gelegt werden soll
//     * @param karte - Die Karte, die abgelegt werden soll
//     * @return - Liste der abgelegten Karten (Ablagestapel)
//     */
//    List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte);
//
//    /**
//     * Methode bietet die Moeglichkeit, die Spielrichtung zu aendern.
//     */
//    void aendernSpielrichtung(Spiel spiel);
//
//
//    /**
//     * Farbe des Spiels wird gewechselt.
//     *
//     * @param spiel     - Laufendes Spiel.
//     * @param neueFarbe - Die neue Farbe (Enum Farbe beachten)
//     * @return Das Spiel mit der geaenderten Einstellung fuer Farbe
//     */
//    Spiel aendernFarbe(Spiel spiel, Farbe neueFarbe);

    /**
     * Prueft am Ende eines Zuges, ob das Spiel zu Ende ist
     *
     * @param spieler - aktueller Spieler
     * @return boolean, der angibt, ob das Spiel zu Ende ist
     */
    boolean ermittleSpielende(Spieler spieler);

    /**
     * Prueft ob der Spieler Mau geklickt hat
     * und falls nicht, bekommt der Spieler auch die Strafkarten auf die Hand
     *
     * @param spiel - Das Spiel
     * @return Das Spiel, nachdem ggfs. Strafkarten dem Spieler zugefuehrt wurden
     */
    Spiel pruefeAufMau(Spiel spiel);

    /**
     * Setzt Mau bei Spieler
     *
     * @param spieler - bei dem Mau gesetzt werden muss
     * @param neuerZustand - Der Zustand den der Mauzustand danach haben soll,
     */
    void setzeMau(Spieler spieler, boolean neuerZustand);

    /** Prüft, ob Karten gemischt werden muessen, und tut es ggf.
     *
     * @param spiel Das Spiel
     * @return Das Spiel
     */
    Spiel mussGemischtWerden(Spiel spiel);
  /*TODO
        Sollten alle Karten auf den Handkarten der Spieler sein, kommt ers zu einer Exception und dem Abbruch des Spieles,
        da die Wahrscheinlichkeit sehr gering ist, das so gespielt wird, wird dieses Mal die Exception noch nicht gefangen und bearbeitet,
        dies erfolgt bei der nächsten Abgabe
        */
//    /**
//     * Methode bestimmt, wie viele Handkarten jeder Spieler bei Beginn bekommen muss
//     *
//     * @param spielerListe - Liste der Spieler
//     * @param ziehstapel - Der Ziehstapel der verteilt verden soll
//     * @return Anzahl der Handkarten zu Spielbeginn
//     */
//    int anzahlStartkartenbestimmen(List<Spieler> spielerListe, List<Karte> ziehstapel);
//
//    /**
//     * Methode prueft, ob Karten waehrend des Spiels gemischt werden
//     *
//     * @param ziehstapel - Der Ziehstapel
//     * @return boolean, der angibt ob gemischt werden muss
//     */
//    boolean mussGemischtWerden(List<Karte> ziehstapel);
}
