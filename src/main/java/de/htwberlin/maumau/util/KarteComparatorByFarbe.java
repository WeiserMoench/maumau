package de.htwberlin.maumau.util;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import java.util.Comparator;

public class KarteComparatorByFarbe implements Comparator<Karte> {

    @Override
    /**
     * Methode sortiert Karten nach Farbe
     */
    public int compare(Karte o1, Karte o2) {
        return o1.getFarbe().compareTo(o2.getFarbe());
    }
}