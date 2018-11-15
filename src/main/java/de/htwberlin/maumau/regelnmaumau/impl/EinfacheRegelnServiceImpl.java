/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181115
 *
 */

package de.htwberlin.maumau.regelnmaumau.impl;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;

public class EinfacheRegelnServiceImpl implements RegelnService {

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

        if(kartenwertLetzteKarte == kartenwertlegendeKarte){
            return true;
        }else return kartenFarbeLetzteKarte == kartenFarbelegendeKarte;
    }

    @Override
    public boolean mussSichFarbeWuenschen(Karte bubenKarte) {//das macht keinen Sinn, da der Name darauf hindeutet, dass geprueft wird ob es noetig ist, dann muss aber ein True oder false erscheinen
        return false;
    }

    @Override
    /**
     * Bei Kartenwert 7 muss der naechste zwei zusaetzliche Karten ziehen
     */
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {//evtl Int umbenennen in bisherZuZiehendeKarten
        return 0;
    }

    @Override
    /**
     * Bei Kartenwert 8 muss der naechste Spieler Aussetzen
     */
    public boolean mussRundeAussetzen(Karte gelegteKarte) {
        return false;
    }

    @Override
    /**
     * Bei Kartenwert 9 muss der naechste Spieler Aussetzen
     */
    public boolean richtungWechsel(Karte gelegteKarte) {
        return false;
    }

}
