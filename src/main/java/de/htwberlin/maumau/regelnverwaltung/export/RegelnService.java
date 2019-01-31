/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.regelnverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

public interface RegelnService {


    /**
     * Diese Methode prueft ob die zulegende Karte gelegt werden darf
     * Sollte die letzte Karte ein Bube sein, muss die Wunschfarbe beachtet werden
     *
     * @param letzteKarteKartenstapel - die letzte Karte, die auf dem Ablagestapel liegt
     * @param legendeKarte - die Karte, die gelegt werden soll
     * @param farbe - Farbe aus dem Spiel, die durch die Fahrbwahl eines Buben ausgeloest wurde
     * @return gibt einen boolean zurueck, der angibt, ob die Karte gelegt werden darf
     */
    boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, Farbe farbe);

    /**
     * Diese Methode prueft ob die letzte Karte ein Bube ist und somit eine Farbwahl noetig ist
     *
     * @param gelegteKarte - gerade gelegte Karte
     * @return boolean, der angibt ob der Spieler sich eine Farbe wuenschen muss
     */
    boolean mussSichFarbeWuenschen(Karte gelegteKarte);

    /**
     * Methode, die prueft ob der naechste Spieler zwei Karten ziehen muss
     *
     * @param gelegteKarte - gerade gelegte Karte
     * @param zuziehendeKarte - Anzahl zu ziehende Karten, damit mehrere nacheinander Spieler den naechsten zwei Karten ziehen lassen
     *                        ziehen lassen koennen und eine Addition moeglich ist
     * @return neue Anzahl der zuziehenden Karten durch den naechsten Spieler
     */
    int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    /**
     * Diese Methode prueft, wenn der naechste Spieler dran ist, ob er ueberhaupt ablegen darf
     *
     * @param gelegteKarte - die letzte Karte die gelegt wurde (also die oberste auf dem Ablagestapel)
     * @return boolean, der angibt ob der aktuelle Spieler spielen darf
     */
    boolean mussRundeAussetzen(Karte gelegteKarte);

    /**
     * Diese Methode prueft, ob ein Richtungswechsel ausgeloest werden muss
     *
     * @param gelegteKarte - gerade abgelegte Karte
     * @return boolean, der angibt ob ein Richtungswechsel ausgeloest werden muss
     */
    boolean richtungWechsel(Karte gelegteKarte);


}
