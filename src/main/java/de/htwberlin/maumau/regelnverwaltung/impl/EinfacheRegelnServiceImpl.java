/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20190203
 */

package de.htwberlin.maumau.regelnverwaltung.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.regelnverwaltung.export.RegelnService;
import org.apache.log4j.Logger;

public class EinfacheRegelnServiceImpl implements RegelnService {
    private static Logger log = Logger.getRootLogger();

    @Override
    public boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, Farbe farbe) {
        String kartenwertLetzteKarte = letzteKarteKartenstapel.getWert();
        Farbe kartenFarbeLetzteKarte = letzteKarteKartenstapel.getFarbe();
        String kartenwertlegendeKarte = legendeKarte.getWert();
        Farbe kartenFarbelegendeKarte = legendeKarte.getFarbe();

        if (kartenwertLetzteKarte.equals(kartenwertlegendeKarte)) {
            return true;
        } else
            return kartenFarbeLetzteKarte.equals(kartenFarbelegendeKarte);
    }

    @Override
    public boolean mussSichFarbeWuenschen(Karte gelegteKarte) {
        return false;
    }

    @Override
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {
        return 0;
    }

    @Override
    public boolean mussRundeAussetzen(Karte gelegteKarte) {
        return false;
    }

    @Override
    public boolean richtungWechsel(Karte gelegteKarte) {
        return false;
    }

}
