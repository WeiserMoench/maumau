/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.spielerverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;

public interface SpielerService {

    /**
     * Diese Methode fuegt einem Spieler eine Karte zu seinen Handkarten hinzu
     *  @param karte - Die Karte die Hinzugefuegt werden soll
     * @param spieler - Der Spieler, dem die Karte hinzugefuegt werden soll
     */
    Spieler karteZuHandblatthinzufuegen(Karte karte, Spieler spieler);

    /**
     * Diese Methode entfernt eine Karte aus den Handkarten eines Spielers
     *
     * @param karte - zu entfernende Karte
     * @param spieler - Der Spieler, aus dessen Handkarten die Karte entfernt werden soll
     */
    Spieler karteausHandblattentfernden(Karte karte, Spieler spieler);

    /**
     * Legt einen neuen spielerverwaltung an
     *
     * @param name  - Name des neuen spielerverwaltung
     * @return der neue spielerverwaltung
     */
    Spieler neuerSpielerAnlegen(String name);
}
