package de.htwberlin.maumau.model;

import java.util.List;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 */
public class Spieler {
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