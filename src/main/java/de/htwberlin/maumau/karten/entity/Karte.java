package de.htwberlin.maumau.karten.entity;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 */
public class Karte {


    private String farbe;
    private String wert;
    private int karteID;

    public void neueKarte(String farbe, String wert, int kartenID) {

        this.farbe = farbe;
        this.wert = wert;
        this.karteID = kartenID;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public int getKarteID() {
        return karteID;
    }

    public void setKarteID(int karteID) {
        this.karteID = karteID;
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }


}