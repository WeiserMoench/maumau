/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.karten.export;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;//raus

import java.util.List;

public interface KartenService {


    /**
     * Diese Methode baut den Kartenstapel aus neuen Karten des ausgewaehlten Kartendecks auf.
     *
     * @param spiel - Das zu spielende Spiel.
     * @return Das Spiel mit der integrierten Liste an Karten
     */
    List<Karte> anlegenKartenstapel();

    /**
     * Diese Methode mischt den Ziehstapel einmalig durch.
     *
     * @param obersteKarteBleibt - boolean, der angibt, ob die oberste Karte des Ablagestapels bleibt.
     * @return Gibt eine Liste an Karten zurueck, diese Liste ist dann durchgemischt
     */
    List<Karte> mischenKartenstapel(List<Karte> karten, boolean obersteKarteBleibt);

    /**
     * Diese Methode entscheidet welche Karte, ruft den Spieler auf und übergibt diese ihm
     *
     * @param kartenDeck
     * @param spieler
     * @return
     */
    List austeilenvonKarten(List<Karte> kartenDeck, Spieler spieler);//in spielerService

    /**
     * Diese Methode legt neue Karten an
     *
     * @param farbe - Die Farbe der neuen Karte (Enum Farbe beachten)
     * @param wert - Den Wert der neuen Karte
     * @return Die neue Karte
     */
    Karte erstellenNeuerKarte(Farbe farbe, String wert);
}
