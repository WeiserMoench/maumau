package de.htwberlin.maumau.spiel.entity;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.entity.Kartendeck;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 */

public class Spiel {

    private Spieler aktiverSpieler;
    private boolean istSpielrichtungRechts;
    private int summeZuziehendeKarten;
    private List<Karte> ablagestapelkarten;
    private List<Karte> ziehstapelkarten;
    private List<Spieler> spielerDesSpieles;
    private Kartendeck kartendeck;
    private int farbe; //0 Herz, 1 Karo, 2 Pik, 3 Kreuz


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

    public List<Spieler> getSpielerDesSpieles() {
        return spielerDesSpieles;
    }

    public void setSpielerDesSpieles(List<Spieler> spielerDesSpieles) {
        this.spielerDesSpieles = spielerDesSpieles;
    }

    public Kartendeck getKartendeck() {
        return kartendeck;
    }

    public void setKartendeck(Kartendeck kartendeck) {
        this.kartendeck = kartendeck;
    }

    public int getFarbe() {
        return farbe;
    }

    public void setFarbe(int farbe) {
        this.farbe = farbe;
    }
}
