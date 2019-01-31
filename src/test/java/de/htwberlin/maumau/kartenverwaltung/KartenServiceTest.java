/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.kartenverwaltung;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.kartenverwaltung.export.KartenService;
import de.htwberlin.maumau.kartenverwaltung.impl.KartenServiceImpl;
import de.htwberlin.maumau.spielverwaltung.entity.Spiel;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KartenServiceTest {

    private KartenService kartenService = new KartenServiceImpl();

    private Karte karte1 = new Karte(Farbe.PIK, "10");
    private Karte karte2 = new Karte(Farbe.PIK, "9");
    private Karte karte3 = new Karte(Farbe.HERZ, "9");
    private List<Karte> karten = new ArrayList<>();
    private Spieler spieler1 = new Spieler();
    private Spiel spiel = new Spiel();


    @Before
    public void setUp() {
        karten.add(karte1);
        karten.add(karte2);
        karten.add(karte3);
    }


    @Test
    public void testMischenKartenstapel() {
        List<Karte> testMisch = new ArrayList<>();
        testMisch.add(karte2);
        testMisch.add(karte1);
        testMisch.add(karte3);
        List<Karte> gemischtKarten = kartenService.mischenKartenstapel(karten, true);
        assertEquals(testMisch.get(karten.size() -1), gemischtKarten.get(karten.size() -1));
    }


    @Test
    public void testErstellenNeueKarte(){
        Karte karteTest = kartenService.erstellenNeuerKarte(Farbe.PIK, "10");
        assertEquals(karte1.getFarbe(), karteTest.getFarbe());
        assertEquals(karte1.getWert(), karteTest.getWert());
    }

    @Test
    public void testAnlegenKartenstapel(){
        assertEquals(52, kartenService.anlegenKartenstapel().size());
    }
}