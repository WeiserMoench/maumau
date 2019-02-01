/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20190201
 *
 */
package de.htwberlin.maumau.virtuellerspielerverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;

public interface KiService {

    /**
     * Diese Methode prueft ob die KI mau sagen muss
     * und entscheidet ob die KI Mau sagt
     *
     * @param spieler - Die Ki die geprueft werden soll
     * @return - boolean, der angibt ob die Ki Mau gesagt hat
     */
    boolean mauSetzen(Spieler spieler);

    /**
     * Diese Methode generiert den Namen fuer einen KI Spieler
     *
     * @param kiZaehler - Nummer des wie vielten gewuenschten KI Spielers
     * @return - String der den Namen der Ki darstellt
     */
    String kiAnlegen(int kiZaehler);

    /**
     * Methode entscheidet, welche Farbe sich die KI wuenscht
     *
     * @return - gewuenschte Farbe
     */
    Farbe kiMussFarbeWuenschen();
}
