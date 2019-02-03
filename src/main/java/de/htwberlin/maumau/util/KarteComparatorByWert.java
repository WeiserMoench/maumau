package de.htwberlin.maumau.util;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import java.util.Comparator;

public class KarteComparatorByWert implements Comparator<Karte> {

    @Override
    /**
     * Methode sortiert die Karten nach Wert, wobei der Wert 10 als erstes kommt
     */
    public int compare(Karte o1, Karte o2) {
        return o1.getWert().compareTo(o2.getWert());
    }
}