package de.htwberlin.maumau.regeln;

import de.htwberlin.maumau.karten.entity.Karte;

public interface RegelnService {


    boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, String farbe);

    boolean mussSichFarbeWuenschen(Karte bubenKarte);

    int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    boolean mussRundeAussetzen(Karte gelegteKarte);

    boolean richtungWechsel(Karte gelegteKarte);


}
