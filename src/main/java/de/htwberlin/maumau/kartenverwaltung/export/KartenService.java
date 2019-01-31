/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.kartenverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import java.util.List;

public interface KartenService {


    /**Diese Methode legt einen Kartenstapel von 52 Karten an
     *
     * @return Liste der Karten von 52
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
     * Diese Methode legt neue Karten an
     *
     * @param farbe - Die Farbe der neuen Karte (Enum Farbe beachten)
     * @param wert - Den Wert der neuen Karte
     * @return Die neue Karte
     */
    Karte erstellenNeuerKarte(Farbe farbe, String wert);
}
