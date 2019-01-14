package de.htwberlin.maumau.spieler.impl;

import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.export.KiService;


public class KiServiceImpl implements KiService {


    private int kiZaehler=1;

    @Override
    public Spiel kiSpielt(Spiel spiel) {

        return spiel;
    }

    @Override
    public String kiAnlegen() {
        String spielername = "Computer"+kiZaehler;
        return spielername;
    }
}
