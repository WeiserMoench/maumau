package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.karten.entity.Karte;

public interface RegelnService {



    boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, int farbe);

    int mussSichFarbeWuenschen(Karte bubenKarte);

    int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    boolean mussRundeAussetzen(Karte gelegteKarte);

    boolean richtungWechsel(Karte gelegteKarte);


}
