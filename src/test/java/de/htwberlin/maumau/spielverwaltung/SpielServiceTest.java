/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.spielverwaltung;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.kartenverwaltung.export.KartenService;
import de.htwberlin.maumau.regelnverwaltung.export.RegelnService;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.spielerverwaltung.export.SpielerService;
import de.htwberlin.maumau.spielverwaltung.entity.Spiel;
import de.htwberlin.maumau.spielverwaltung.impl.SpielServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpielServiceTest {

    @InjectMocks
    private SpielServiceImpl spielService;
    @Mock
    private SpielerService spielerService;
    @Mock
    private RegelnService regelnService;
    @Mock
    private KartenService kartenService;


    private Karte herz7;
    private Karte pik8;
    private Karte pik9;
    private Karte pikkoenig;
    private List<Karte> ziehstapel;
    private Spieler paul;
    private List<Spieler> spielerliste;
    private Spieler sven;
    private Spiel spiel = new Spiel();


    @Before
    public void initialize() {
        herz7 = new Karte((Farbe.HERZ), "7");
        pik8 = new Karte(Farbe.PIK, "8");
        pik9 = new Karte(Farbe.PIK, "9");
        pikkoenig = new Karte(Farbe.PIK, "König");
        ziehstapel = new ArrayList<>();
        paul = new Spieler();
        sven = new Spieler();
        spiel.setFarbe(Farbe.PIK);
        spielerliste = new ArrayList<>();
        spielerliste.add(paul);
        spielerliste.add(sven);
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        ziehstapel.add(herz7);

        //Spiel vorbereiten
        spiel.setAblagestapelkarten(ziehstapel);
        spiel.setZiehstapelkarten(ziehstapel);
        spiel.setAktiverSpieler(paul);
        spiel.setIstSpielrichtungRechts(true);
        spiel.setFarbe(Farbe.HERZ);
        spiel.setSpielerDesSpieles(spielerliste);

    }


//    @Test
//    public void testZieheKarteVomZiehStapel() {
//        ziehstapel.add(pik8);
//        assertEquals(pik8, spielService.ziehenKarteVomZiehstapel(ziehstapel));
//    }


//    @Test
//    public void testMussNichtGemischtWerden() {
//        ziehstapel.add(pik8);
//        assertFalse("Es muss nicht gemischt werden", spielService.mussGemischtWerden(spiel));
//    }

    @Test
    public void test1ZuZiehendeKarte() {
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        spielerliste.add(paul);
        assertEquals(1, spielService.karteZiehen(1, ziehstapel, paul).size());
    }

    @Test
    public void test2ZuZiehendeKarte() {
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        spielerliste.add(paul);
        assertEquals(0, spielService.karteZiehen(2, ziehstapel, paul).size());
    }


    @Test
    public void testErmittleSpielendeNegativ() {
        spielerService.karteZuHandblatthinzufuegen(pik8, paul);
        assertFalse("das spiel hätte nicht zu Ende sein müssen", spielService.ermittleSpielende(paul));
    }

    @Test
    public void testeSetzeMau() {
        spielService.setzeMau(paul, true);

        assertTrue("Mau hätte gesetzt sein müssen", paul.isMauistgesetzt());
        spielService.setzeMau(paul, false);
        assertFalse(paul.isMauistgesetzt());
    }

//    @Test
//    public void testAnlegenSpiel() {
//        Spiel testSpiel = spielService.anlegenSpiel();
//        assertEquals(spiel, testSpiel);
//
//    }


    @Test
    public void testPruefeAufMauTrue() {
        paul.getHandkarten().add(herz7);
        spiel.getAktiverSpieler().setMauistgesetzt(true);


         spiel = spielService.pruefeAufMau(spiel);
        assertEquals(1, spiel.getAktiverSpieler().getHandkarten().size());
    }


    @Test
    public void testMussGemischtWerden() {
        Spiel inSpiel = new Spiel();
        List<Karte> stapel = new ArrayList<>();
        stapel.addAll(ziehstapel);
        List<Karte> ziehKarten = new ArrayList<>();
        inSpiel.setZiehstapelkarten(ziehKarten);
        inSpiel.setAblagestapelkarten(stapel);
        ziehKarten.add(pikkoenig);
        when(kartenService.mischenKartenstapel(stapel, false)).thenReturn(ziehKarten);


        assertTrue(!spielService.mussGemischtWerden(inSpiel).getZiehstapelkarten().isEmpty());


    }


    @Test
    public void testFarbegewaehlt() {
        spiel.setFarbe(Farbe.HERZ);
        assertEquals(Farbe.PIK, spielService.farbeGewaehlt(spiel, Farbe.PIK).getFarbe());
        spiel.setMussFarbeWuenschen(true);
        assertFalse(spielService.farbeGewaehlt(spiel, Farbe.PIK).isMussFarbeWuenschen());

    }

    @Test
    public void testNaechsterSpielerRechts() {
        Spieler hans = new Spieler();
        spielerliste.add(hans);
        spiel = spielService.naechsterSpieler(spiel);

        assertEquals(sven, spiel.getAktiverSpieler());

    }
    @Test
    public void testNaechsterSpielerLinks() {
        Spieler hans = new Spieler();
        spielerliste.add(hans);
        spiel.setIstSpielrichtungRechts(false);
        spiel = spielService.naechsterSpieler(spiel);

        assertEquals(hans, spiel.getAktiverSpieler());
    }
    @Test
    public void testNaechsterSpielerRechtsAussetzen() {
        Spieler hans = new Spieler();
        spielerliste.add(hans);
        spiel.setAussetzen(true);
        spiel = spielService.naechsterSpieler(spiel);

        assertEquals(hans, spiel.getAktiverSpieler());
    }
}
