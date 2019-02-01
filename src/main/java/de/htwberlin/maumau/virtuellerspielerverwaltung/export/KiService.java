package de.htwberlin.maumau.virtuellerspielerverwaltung.export;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;

public interface KiService {

    boolean mauSetzen(Spieler spieler);

    String kiAnlegen(int kiZaehler);

    Farbe kiMussFarbeWuenschen();
}
