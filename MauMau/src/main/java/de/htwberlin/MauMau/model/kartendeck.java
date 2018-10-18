package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class kartendeck extends BaseEntity {


    private String beschreibung;

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
