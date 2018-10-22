package de.htwberlin.MauMau.model;

import java.util.List;

/**
 * Created by Dustin on 12.10.2018
 */
public class Spieler extends BaseEntity {

    private String name;
    private String email;
    private List<Karte> handkarten;
    private int spielerID;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Karte> getHandkarten() {
        return handkarten;
    }

    public void setHandkarten(List<Karte> handkarten) {
        this.handkarten = handkarten;
    }

    public int getSpielerID() {
        return spielerID;
    }

    public void setSpielerID(int spielerID) {
        this.spielerID = spielerID;
    }
}