package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class Karte extends BaseEntity {


    private String farbe;
    private String wert;



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


}