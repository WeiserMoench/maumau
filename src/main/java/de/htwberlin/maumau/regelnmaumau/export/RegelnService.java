package de.htwberlin.maumau.regelnmaumau.export;

import de.htwberlin.maumau.karten.entity.Karte;

public interface RegelnService {



    boolean darfKartegelegtwerden(Karte letzteKarteKartenstapel, Karte legendeKarte, int farbe);

    boolean mussSichFarbeWuenschen(Karte bubenKarte);

    int mussZweiKartenZiehen(Karte gelegteKarte, int zuziehendeKarte);

    boolean mussRundeAussetzen(Karte gelegteKarte); //ist es nicht eher die letzte Karte auf dem Ablagestapel und muss das nicht zu Beginn einer Runde geprueft werden, oder sorgt diese Methode dafür, das der naechste Spieler aussetzen muss

    boolean richtungWechsel(Karte gelegteKarte);


}
