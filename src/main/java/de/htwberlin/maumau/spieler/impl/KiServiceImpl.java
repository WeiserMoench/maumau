package de.htwberlin.maumau.spieler.impl;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.KiService;


public class KiServiceImpl implements KiService {


    private int kiZaehler=1;

    @Override
    public Karte kiSpielt(Spieler spieler, Karte letzteKarte) {
        Karte zulegendeKarte = null;

        return zulegendeKarte;
    }

    @Override
    public String kiAnlegen() {
        String spielername = "Computer"+kiZaehler;
        return spielername;
    }
}
