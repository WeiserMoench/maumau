package de.htwberlin.maumau.regelnmaumau.impl;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.export.*;

public class RegelnServiceImpl implements RegelnService {


    @Override
    public boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, int farbe) {//Warum ein int???
        String kartenwertLetzteKarte;
        kartenwertLetzteKarte=letzteKarteKartenstapel.getWert();
        String kartenFarbeLetzteKarte;
        kartenFarbeLetzteKarte=letzteKarteKartenstapel.getFarbe();
        String kartenwertlegendeKarte;
        kartenwertlegendeKarte=legendeKarte.getWert();
        String kartenFarbelegendeKarte;
        kartenFarbelegendeKarte=legendeKarte.getFarbe();

        if(kartenwertLetzteKarte==kartenwertlegendeKarte){
            return true;
        }else return kartenFarbeLetzteKarte == kartenFarbelegendeKarte;

    }

    @Override
    public int mussSichFarbeWuenschen(Karte bubenKarte) {//das macht keinen Sinn, da der Name darauf hindeutet, dass geprueft wird ob es noetig ist, dann muss aber ein True oder false erscheinen
        return 0;
    }

    @Override
    /**
     * Bei Kartenwert 7 muss der naechste zwei zusaetzliche Karten ziehen
     */
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {//evtl Int umbenennen in bisherZuZiehende Karten
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
     * Bei Kartenwert 9 muss der naechste Spieler Aussetzen
     */
    public boolean richtungWechsel(Karte gelegteKarte) {
        String kartenwert;
        kartenwert=gelegteKarte.getWert();
        return kartenwert == "9";
    }
}
