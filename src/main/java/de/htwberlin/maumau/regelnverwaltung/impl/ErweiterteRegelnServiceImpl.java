/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.regelnverwaltung.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.regelnverwaltung.export.RegelnService;
import org.apache.log4j.Logger;

public class ErweiterteRegelnServiceImpl implements RegelnService {

    private static Logger log = Logger.getRootLogger();
    private String kartenwert;

    @Override
    public boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, Farbe farbe) {
        String kartenwertLetzteKarte = letzteKarteKartenstapel.getWert();
        Farbe kartenFarbeLetzteKarte = letzteKarteKartenstapel.getFarbe();
        String kartenwertlegendeKarte = legendeKarte.getWert();
        Farbe kartenFarbelegendeKarte = legendeKarte.getFarbe();

        if (kartenwertLetzteKarte == "Bube") {
            if (farbe == null) {
                return kartenFarbeLetzteKarte == kartenFarbelegendeKarte;
            } else {
                return farbe == kartenFarbelegendeKarte;
            }
        } else {
            if (kartenwertLetzteKarte == kartenwertlegendeKarte) {
                return true;
            } else
                return kartenFarbeLetzteKarte == kartenFarbelegendeKarte;
        }
    }

    @Override
    public boolean mussSichFarbeWuenschen(Karte gelegteKarte) {
        kartenwert = gelegteKarte.getWert();
        return kartenwert == "Bube";
    }

    @Override
    /**
     * Bei Kartenwert 7 muss der naechste zwei zusaetzliche Karten ziehen
     */
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {
        kartenwert = gelegteKarte.getWert();
        if (kartenwert == "7") {
            int anzahlZuZiehendeKarten = zuziehendeKarte + 2;
            return anzahlZuZiehendeKarten;
        }
        return zuziehendeKarte;
    }

    @Override
    /**
     * Bei Kartenwert 8 muss der naechste Spieler Aussetzen
     */
    public boolean mussRundeAussetzen(Karte gelegteKarte) {
        kartenwert = gelegteKarte.getWert();
        return kartenwert == "8";
    }

    @Override
    /**
     * Bei Kartenwert 9 wird die Richtung gewechselt
     */
    public boolean richtungWechsel(Karte gelegteKarte) {
        log.debug("richtungswechsel");
        kartenwert = gelegteKarte.getWert();
        return kartenwert == "9";
    }


}
