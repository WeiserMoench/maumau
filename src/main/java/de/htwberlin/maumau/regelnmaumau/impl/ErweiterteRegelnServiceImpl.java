/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181115
 *
 */

package de.htwberlin.maumau.regelnmaumau.impl;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.export.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ErweiterteRegelnServiceImpl implements RegelnService {

    static Log log = LogFactory.getLog(ErweiterteRegelnServiceImpl.class);


    @Override
    public boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, Farbe farbe) {//wenn bube Farbe ändert???
        String kartenwertLetzteKarte;
        kartenwertLetzteKarte=letzteKarteKartenstapel.getWert();
        Farbe kartenFarbeLetzteKarte;
        kartenFarbeLetzteKarte=letzteKarteKartenstapel.getFarbe();
        String kartenwertlegendeKarte;
        kartenwertlegendeKarte=legendeKarte.getWert();
        Farbe kartenFarbelegendeKarte;
        kartenFarbelegendeKarte=legendeKarte.getFarbe();

        if(kartenwertLetzteKarte == "Bube"){
            if(farbe==null){
                return kartenFarbeLetzteKarte==kartenFarbelegendeKarte;
            }else{
                return farbe == kartenFarbelegendeKarte;
            }
        }else{
            if(kartenwertLetzteKarte == kartenwertlegendeKarte){
                return true;
            }else
                return kartenFarbeLetzteKarte == kartenFarbelegendeKarte;
        }
    }

    @Override
    public boolean mussSichFarbeWuenschen(Karte gelegteKarte) {//das macht keinen Sinn, da der Name darauf hindeutet, dass geprueft wird ob es noetig ist, dann muss aber ein True oder false erscheinen
        String kartenwert = gelegteKarte.getWert();
        return kartenwert == "Bube";
    }

    @Override
    /**
     * Bei Kartenwert 7 muss der naechste zwei zusaetzliche Karten ziehen
     */
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {//evtl Int umbenennen in bisherZuZiehendeKarten
        String kartenwert;
        kartenwert=gelegteKarte.getWert();
        if(kartenwert=="7"){
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
        String kartenwert;
        kartenwert=gelegteKarte.getWert();
        return kartenwert == "8";
    }

    @Override
    /**
     * Bei Kartenwert 9 wird die Richtung gewechselt
     */
    public boolean richtungWechsel(Karte gelegteKarte) {
        log.debug("richtungswechsel");
        String kartenwert;
        kartenwert=gelegteKarte.getWert();
        return kartenwert == "9";
    }


}
