package de.htwberlin.maumau.spieler;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface SpielerService {

    /**
     * Diese Methode fuegt einen gewuenschten spieler hinzu und speichert diesen im Spiel.
     *
     * @param spiel - Das zuvor angelegte Spiel wird uebergeben.
     */
    void auswaehlenSpielerFuerSpiel(Spiel spiel);//registerieren von spielern, spielerparameter

    /**
     * Legt einen neuen spieler an
     *
     * @param name  - Name des neuen spieler
     * @param email - eMail des Spielers
     * @return der neue spieler
     */
    Spieler neuerSpielerAnlegen(String name, String email);


    void karteZumHandblatthinzufügen(Karte karte, Spieler spieler);

    void kartevomHandblattentfernen(Karte karte, Spieler spieler);

    void setzeMau(Spieler spieler);
}
