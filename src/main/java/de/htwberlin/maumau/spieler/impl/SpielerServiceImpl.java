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
    public Spieler karteausHandblattentfernden(Karte karte, Spieler spieler) {
        handkarten = new ArrayList<>();
        handkarten = spieler.getHandkarten();
        handkarten.remove(karte);
        spieler.setHandkarten(handkarten);
        return spieler;
    }

    @Override
    public void auswaehlenSpielerFuerSpiel(Spiel spiel) {//Macht das hier und so sinn? nicht eher in Spiel bringen und hier eher eine Liste aller in DB verhandenen Spieler abfragen und diese Richtung Spiel bringen?

    }

    @Override
    public Spieler neuerSpielerAnlegen(String name, String email) {
        Spieler spieler = new Spieler();
        spieler.setName(name);
        spieler.setEmail(email);
        //Spieler in der DB Speichern?
        return spieler;
    }
}
