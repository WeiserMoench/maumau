/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 */

package de.htwberlin.maumau.kartenverwaltung.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.kartenverwaltung.export.KartenService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KartenServiceImpl implements KartenService {
    private String[] kartenwerte = {"Ass", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "Koenig"};

    @Override
    public List<Karte> anlegenKartenstapel() {
        List<Karte> kartenStapel = new ArrayList();
        for (Farbe farben : Farbe.values()) {
            for (String werte : kartenwerte) {
                kartenStapel.add(erstellenNeuerKarte(farben, werte));
            }
        }
        return kartenStapel;
    }

    @Override
    public List<Karte> mischenKartenstapel(List<Karte> karten, boolean obersteKarteBleibt) {
        if (obersteKarteBleibt) {
            Karte oberstekarte = karten.get(karten.size() - 1);
            System.out.println(oberstekarte.getWert() + oberstekarte.getFarbe());
            karten.remove(karten.size() - 1);
            Collections.shuffle(karten);
            karten.add(oberstekarte);
            System.out.println(karten.get(karten.size() - 1).getWert() + oberstekarte.getFarbe());
        } else {
            Collections.shuffle(karten);
        }
        return karten;
    }

    @Override
    public Karte erstellenNeuerKarte(Farbe farbe, String wert) {
        Karte karte = new Karte(farbe, wert);
        return karte;
    }

}
