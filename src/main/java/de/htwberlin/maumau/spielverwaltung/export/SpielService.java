/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.spielverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.spielverwaltung.entity.Spiel;

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

}
