package de.htwberlin.maumau.spieler.export;

import de.htwberlin.maumau.spiel.entity.Spiel;

public interface KiService {

    Spiel kiSpielt(Spiel spiel);

    String kiAnlegen();

}
