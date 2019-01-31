package de.htwberlin.maumau.spieler.impl;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.KiService;

import java.util.List;
import java.util.Random;


public class KiServiceImpl implements KiService {


    private int kiZaehler=1;

    @Override
    public Karte kiWaehltKarte(Spieler spieler, int durchgangszaehler) {
        Karte moeglicheKarte = null;
        List<Karte> handkarten = spieler.getHandkarten();

        moeglicheKarte=handkarten.get(durchgangszaehler);
        return moeglicheKarte;
    }

    @Override
    public boolean mauSetzen(Spieler spieler) {

        if(spieler.getHandkarten().size()==1){
            Random rand = new Random();
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;

            return randomNum > 3;
        }
        return false;
    }

    @Override
    public String kiAnlegen() {
        String spielername = "Computer"+kiZaehler;
        return spielername;
    }
}
