/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 *
 */

package de.htwberlin.maumau.spieler.entity;

import de.htwberlin.maumau.karten.entity.Karte;

import java.util.ArrayList;
import java.util.List;


public class Spieler {
    private String name;
    private List<Karte> handkarten = new ArrayList<>();
    private boolean mauistgesetzt = false;

    public boolean isMauistgesetzt() {
        return mauistgesetzt;
    }

    public void setMauistgesetzt(boolean mauistgesetzt) {
        this.mauistgesetzt = mauistgesetzt;
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

}