package de.htwberlin.maumau.karten.entity;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 */
public class Karte {


    private Farbe farbe;
    private String wert;
    private int karteID;

    public void neueKarte(Farbe farbe, String wert, int kartenID) {

        this.farbe = farbe;
        this.wert = wert;
        this.karteID = kartenID;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public int getKarteID() {
        return karteID;
    }

    public void setKarteID(int karteID) {
        this.karteID = karteID;
    }
}