/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20190202
 */

package de.htwberlin.maumau.kartenverwaltung.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Karte {

    private Farbe farbe;
    private String wert;

    public Karte(Farbe farbe, String wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public Karte() {
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

}

