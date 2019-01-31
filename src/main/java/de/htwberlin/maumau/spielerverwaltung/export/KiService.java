package de.htwberlin.maumau.spielerverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;

public interface KiService {

    Karte kiWaehltKarte(Spieler spieler, int durchgangszaehler);

    boolean mauSetzen(Spieler spieler);

    String kiAnlegen();

}
