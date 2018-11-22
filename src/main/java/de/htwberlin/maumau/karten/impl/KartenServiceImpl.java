/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.karten.impl;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.export.KartenService;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

// Dustin bitte machen

public class KartenServiceImpl implements KartenService {
    @Override
    public Spiel anlegenKartenstapel(Spiel spiel) {
        return null;
    }

    @Override
    public List<Karte> mischenKartenstapel(List<Karte> karten, boolean obersteKarteBleibt) {
        return null;
    }

    @Override
    public List austeilenvonKarten(List<Karte> kartenDeck, Spieler spieler) {
        return null;
    }

    @Override
    public Karte erstellenNeuerKarte(Farbe farbe, String wert) {
        return null;
    }
}
