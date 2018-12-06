/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.spieler.export;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface SpielerService {

    /**
     * Diese Methode fuegt einem Spieler eine Karte zu seinen Handkarten hinzu
     *
     * @param karte - Die Karte die Hinzugefuegt werden soll
     * @param spieler - Der Spieler, dem die Karte hinzugefuegt werden soll
     */
    void karteZuHandblatthinzufuegen(Karte karte, Spieler spieler);

    /**
     * Diese Methode entfernt eine Karte aus den Handkarten eines Spielers
     *
     * @param karte - zu entfernende Karte
     * @param spieler - Der Spieler, aus dessen Handkarten die Karte entfernt werden soll
     */
    Spieler karteausHandblattentfernden(Karte karte, Spieler spieler);

    /**
     * Diese Methode fuegt einen gewuenschten spieler hinzu und speichert diesen im Spiel.
     *
     * @param spiel - Das zuvor angelegte Spiel wird uebergeben.
     */
    void auswaehlenSpielerFuerSpiel(Spiel spiel);//registerieren von spielern, spielerparameter

    /**
     * Legt einen neuen spieler an
     *
     * @param name  - Name des neuen spieler
     * @param email - eMail des Spielers
     * @return der neue spieler
     */
    Spieler neuerSpielerAnlegen(String name, String email);




}
