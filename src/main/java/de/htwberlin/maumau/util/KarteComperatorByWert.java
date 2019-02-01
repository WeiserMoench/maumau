package de.htwberlin.maumau.util;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import java.util.Comparator;

public class KarteComperatorByWert implements Comparator<Karte> {

    @Override
    public int compare(Karte o1, Karte o2) {
        return o1.getWert().compareTo(o2.getWert());
    }
}