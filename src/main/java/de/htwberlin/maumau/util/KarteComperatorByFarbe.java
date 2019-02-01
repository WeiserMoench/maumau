package de.htwberlin.maumau.util;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import java.util.Comparator;

public class KarteComperatorByFarbe implements Comparator<Karte> {

    @Override
    public int compare(Karte o1, Karte o2) {
        return o1.getFarbe().compareTo(o2.getFarbe());
    }
}