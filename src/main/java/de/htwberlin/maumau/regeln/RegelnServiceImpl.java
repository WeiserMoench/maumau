package de.htwberlin.maumau.regeln;

import de.htwberlin.maumau.karten.entity.Karte;

public class RegelnServiceImpl implements RegelnService {

    @Override
    public boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, String farbe) {
        return false;
    }

    @Override
    public boolean mussSichFarbeWuenschen(Karte bubenKarte) {
        return false;
    }

    @Override
    public int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte) {
        return 0;
    }

    @Override
    public boolean mussRundeAussetzen(Karte gelegteKarte) {
        return false;
    }

    @Override
    public boolean richtungWechsel(Karte gelegteKarte) {
        return false;
    }
}
