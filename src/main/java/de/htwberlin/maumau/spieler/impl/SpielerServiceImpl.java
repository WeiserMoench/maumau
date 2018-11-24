/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.spieler.impl;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;

import java.util.ArrayList;
import java.util.List;

public class SpielerServiceImpl implements SpielerService {

//    Jörg bitte machen
    private List<Karte> handkarten;

    @Override
    // ob implementierung reicht, habe ich nicht geprueft Christian
    public void karteZuHandblatthinzufuegen(Karte karte, Spieler spieler) {
        handkarten = new ArrayList<>();
        handkarten = spieler.getHandkarten();
        handkarten.add(karte);
        spieler.setHandkarten(handkarten);
    }

    @Override
    public void karteausHandblattentfernden(Karte karte, Spieler spieler) {

    }

    @Override
    public void auswaehlenSpielerFuerSpiel(Spiel spiel) {

    }

    @Override
    public Spieler neuerSpielerAnlegen(String name, String email) {
        return null;
    }
}
