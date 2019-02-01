/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.kartenverwaltung.entity;


public class Karte implements Comparable<Karte>{


    private Farbe farbe;
    private String wert;

    public Karte(Farbe farbe, String wert) {
        this.farbe = farbe;
        this.wert = wert;
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

    @Override
    public int compareTo(Karte o) {
        if(o.farbe.ordinal() < this.farbe.ordinal())
            return 1;
        else if(o.farbe.ordinal() > this.farbe.ordinal())
            return -1;
        else
            return 1;
    }
}