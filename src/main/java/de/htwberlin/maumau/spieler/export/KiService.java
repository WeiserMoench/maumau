package de.htwberlin.maumau.spieler.export;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;

public interface KiService {

    Karte kiWaehltKarte(Spieler spieler, int durchgangszaehler);

    boolean mauSetzen(Spieler spieler);

    String kiAnlegen();

}
