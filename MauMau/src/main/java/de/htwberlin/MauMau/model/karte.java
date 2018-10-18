package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class karte extends baseEntity {


    private String farbe;
    private String wert;
    private String punkte;


    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public String getWert() {
        return wert;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public String getPunkte() {
        return punkte;
    }

    public void setPunkte(String punkte) {
        this.punkte = punkte;
    }
}
