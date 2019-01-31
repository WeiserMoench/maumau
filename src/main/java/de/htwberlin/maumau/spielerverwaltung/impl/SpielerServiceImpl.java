/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 *
 */

package de.htwberlin.maumau.spielerverwaltung.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.spielerverwaltung.export.SpielerService;

import java.util.ArrayList;
import java.util.List;

public class SpielerServiceImpl implements SpielerService {

    private List<Karte> handkarten;

    @Override
    public Spieler karteZuHandblatthinzufuegen(Karte karte, Spieler spieler) {
        handkarten = new ArrayList<>();
        handkarten = spieler.getHandkarten();
        handkarten.add(karte);
        spieler.setHandkarten(handkarten);
        return spieler;
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
    public Spieler neuerSpielerAnlegen(String name) {
        Spieler spieler = new Spieler();
        spieler.setName(name);
        return spieler;
    }
}
