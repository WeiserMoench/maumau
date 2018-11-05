package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface RegelnMauMauService {

    boolean pruefen

    void legeSpielendeFest(Spiel spiel, boolean ersterSpielerbeendetSpiel);

    /**
     * Prüfe handkarte gleich 1
     * auf mau gesetzt
     * true weiter
     * false ziehe karte
     *
     * @param spieler
     */
    void pruefeAufMau(Spieler spieler);

    void istMauNoetig(boolean noetig);

    int anzahlStartKarten(int anzahl);
}
