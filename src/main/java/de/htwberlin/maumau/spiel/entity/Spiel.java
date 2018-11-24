/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.spiel.entity;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;


public class Spiel {

    private Spieler aktiverSpieler;
    private boolean istSpielrichtungRechts = true;
    private int summeZuziehendeKarten;
    private List<Karte> ablagestapelkarten;
    private List<Karte> ziehstapelkarten;
    private List<Spieler> spielerDesSpieles;
    private int kartendeck;
    private Farbe farbe;

    public Spieler getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(Spieler aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    public boolean isIstSpielrichtungRechts() {
        return istSpielrichtungRechts;
    }

    public void setIstSpielrichtungRechts(boolean istSpielrichtungRechts) {
        this.istSpielrichtungRechts = istSpielrichtungRechts;
    }

    public int getSummeZuziehendeKarten() {
        return summeZuziehendeKarten;
    }

    public void setSummeZuziehendeKarten(int summeZuziehendeKarten) {
        this.summeZuziehendeKarten = summeZuziehendeKarten;
    }

    public List<Karte> getAblagestapelkarten() {
        return ablagestapelkarten;
    }

    public void setAblagestapelkarten(List<Karte> ablagestapelkarten) {
        this.ablagestapelkarten = ablagestapelkarten;
    }

    public List<Karte> getZiehstapelkarten() {
        return ziehstapelkarten;
    }

    public void setZiehstapelkarten(List<Karte> ziehstapelkarten) {
        this.ziehstapelkarten = ziehstapelkarten;
    }

    public List<Spieler> getSpielerDesSpieles() {
        return spielerDesSpieles;
    }

    public void setSpielerDesSpieles(List<Spieler> spielerDesSpieles) {
        this.spielerDesSpieles = spielerDesSpieles;
    }

    public int getKartendeck() {
        return kartendeck;
    }

    public void setKartendeck(int kartendeck) {
        this.kartendeck = kartendeck;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }
}
