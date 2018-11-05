package de.htwberlin.maumau.karten.entity;

import java.util.List;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 */
public class Kartendeck {


    private String beschreibung;
    private String name;
    private List<Karte> karten;
    private int kartendeckID;

    public List<Karte> getKarten() {
        return karten;
    }

    public void setKarten(List<Karte> karten) {
        this.karten = karten;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKartendeckID() {
        return kartendeckID;
    }

    public void setKartendeckID(int kartendeckID) {
        this.kartendeckID = kartendeckID;
    }
}
