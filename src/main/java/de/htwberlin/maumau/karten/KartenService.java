package de.htwberlin.maumau.karten;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.entity.Kartendeck;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

public interface KartenService {


    /**
     * Diese Methode baut den Ablagestapel aus neuen Karten des ausgewaehlten Kartendecks auf.
     * Im Anschluss wird der Stapel gemischt und dann erst ausgeteilt.
     *
     * @param spiel - Das zu spielende Spiel.
     */
    List<Karte> anlegenAblagestapel(Spiel spiel);

    /**
     * Diese Methode mischt den Ziehstapel einmalig durch.
     *
     * @param spiel              - Das Spiel, dessen Kartenstapel gemischt werden soll.
     * @param obersteKarteBleibt - boolean, der angibt, dass die oberste Karte des Ablagestapels bleibt.
     */
    List<Karte> mischenKartenstapel(Spiel spiel, boolean obersteKarteBleibt);

    /**
     * Diese Methode entscheidet welche Karte, ruft den Spieler auf und übergibt diese ihm
     *
     * @param kartenDeck
     * @param spieler
     * @return
     */
    Kartendeck austeilenvonKarten(List<Karte> kartenDeck, Spieler spieler);

    void anlegenNeueKarte(String farbe, String wert);
}
