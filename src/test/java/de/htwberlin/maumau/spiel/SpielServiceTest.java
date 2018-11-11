package de.htwberlin.maumau.spiel;


import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SpielServiceTest {

    private SpielService spielService = new SpielServiceImpl();

    private Spiel spiel = new Spiel();
    private Karte karte = new Karte("pik", "10", 1);
    private Karte karte2 = new Karte("herz", "7", 2);
    private List<Karte> stapel = new ArrayList<>();
    private  Spieler spieler = new Spieler();

    @Before
    public void setUp() {
        stapel.add(karte);
        spiel.setAblagestapelkarten(stapel);
    }

    @Test
    public void testAnlegenSpiel() {
        Spiel testSpiel = spielService.anlegenSpiel();
        assertEquals(spiel, testSpiel);

    }

    @Test
    public void testZiehenKarteVomZiehstapel() {
       Karte gezogendeKarte =  spielService.ziehenKarteVomZiehstapel(spiel.getZiehstapelkarten());
       assertEquals(karte, gezogendeKarte);

    }

    @Test
    public void testEntferneGezogendeKarteVomZiehstapel() {
        List<Karte> ablagestapel = spielService.entferneGezogendeKarteVomZiehstapel(stapel, karte);

        assertTrue(ablagestapel.isEmpty());
    }

    @Test
    public void testZuZiehendeKarte() {
        stapel.add(karte2);

        spielService.zuZiehendeKarte(2, stapel, spieler);
        assertTrue(stapel.isEmpty());
        assertTrue(!spieler.getHandkarten().isEmpty());
    }

    @Test
    public void testLegenKarteAufAblageStapel() {

       Spiel testSpiel =  spielService.legenKarteAufAblageStapel(spiel, spieler, stapel, karte2);
        stapel.add(karte2);

        assertEquals(stapel, testSpiel.getAblagestapelkarten());
    }

    @Test
    public void testAendernSpielrichtung() {

        Spiel testSpiel = spielService.aendernSpielrichtung(spiel);

        assertFalse(testSpiel.isIstSpielrichtungRechts());

    }

    @Test
    public void testAendernFarbe() {
        Spiel testSpiel = spielService.aendernFarbe(spiel, "herz");

        assertEquals("herz", testSpiel.getFarbe());
    }

    @Test
    public void testSetzeMau() {

        spielService.setzeMau(spieler);
        assertTrue(spieler.isMau());
    }

    @Test
    public void testAnzahlStartkartenbestimmen() {
        //4 Spieler = 7 Karten
        List<Spieler> spielerListe4 = Arrays.asList(spieler, new Spieler(), new Spieler(), new Spieler());

        assertEquals(7, spielService.anzahlStartkartenbestimmen(spielerListe4) );
        List<Spieler> spielerListe6 = Arrays.asList(spieler, new Spieler(), new Spieler(), new Spieler(), new Spieler(),
                new Spieler());
        assertEquals(5,  spielService.anzahlStartkartenbestimmen(spielerListe6));
    }

    @Test
    public void testIstMauNoetig() {
        spieler.getHandkarten().add(karte);
        assertTrue(spielService.istMauNoetig(spieler));

    }

}
