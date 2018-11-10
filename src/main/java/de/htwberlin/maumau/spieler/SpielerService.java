package de.htwberlin.maumau.spieler;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface SpielerService {


    Spieler karteZuHandblatthinzufuegen(Karte karte, Spieler spieler);

    Spieler karteausHandblattentfernden(Karte karte, Spieler spieler);

    /**
     * Diese Methode fuegt einen gewuenschten spieler hinzu und speichert diesen im Spiel.
     *
     * @param spiel - Das zuvor angelegte Spiel wird uebergeben.
     */
    Spiel auswaehlenSpielerFuerSpiel(Spieler spieler);//registerieren von spielern, spielerparameter

    /**
     * Legt einen neuen spieler an
     *
     * @param name  - Name des neuen spieler
     * @param email - eMail des Spielers
     * @return der neue spieler
     */
    Spieler neuerSpielerAnlegen(String name, String email);


}
