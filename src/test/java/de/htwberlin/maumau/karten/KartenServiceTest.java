package de.htwberlin.maumau.karten;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.entity.Kartendeck;
import de.htwberlin.maumau.spieler.entity.Spieler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class KartenServiceTest {

    private KartenService kartenService = new KartenServiceImpl();

    private Karte karte1 = new Karte("pik", "10", 1);
    private Karte karte2 = new Karte("pik", "9", 2);
    private Karte karte3 = new Karte("herz", "9", 3);
    private List<Karte> karten = new ArrayList<>();
    private Spieler spieler1 = new Spieler();


    private Kartendeck deck1 = new Kartendeck();

    @Before
    public void setUp() {
        karten.add(karte1);
        karten.add(karte2);
        karten.add(karte3);
        deck1.setKarten(karten);
    }


    @Test
    public void testMischenKartenstapel() {
        List<Karte> testMisch = new ArrayList<>();
        testMisch.add(karte2);
        testMisch.add(karte1);
        testMisch.add(karte3);
        List<Karte> gemischtKarten = kartenService.mischenKartenstapel(deck1.getKarten(), true);
        assertEquals(testMisch, gemischtKarten);
    }

    @Test
    public void testAusteilenvonKarten() {
        kartenService.austeilenvonKarten(deck1.getKarten(), spieler1);
        assertFalse(spieler1.getHandkarten().isEmpty());
    }

    @Test
    public void testErstellenNeuerKarte() {
        Karte neueKarte = kartenService.erstellenNeuerKarte("pik", "10");
        neueKarte.setKarteID(1);
        assertEquals(karte1, neueKarte);


    }
}
