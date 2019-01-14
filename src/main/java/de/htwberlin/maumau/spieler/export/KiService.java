package de.htwberlin.maumau.spieler.export;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface KiService {

    Karte kiSpielt(Spieler spieler, Karte letzteKarte);

    String kiAnlegen();

}
