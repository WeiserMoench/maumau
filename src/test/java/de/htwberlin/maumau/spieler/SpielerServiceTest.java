/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.spieler;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpielerServiceTest {

    List<Karte> handkarten = new ArrayList<>();
    private SpielerService spielerService = new SpielerServiceImpl();
    private Karte karte1 = new Karte(Farbe.PIK, "10");
    private Karte karte2 = new Karte(Farbe.PIK, "9");
    private Karte karte3 = new Karte(Farbe.HERZ, "9");
    private Spieler spieler = new Spieler();
    private Spiel spiel = new Spiel();


    @Before

    public void setUp() {

        handkarten.add(karte1);
        handkarten.add(karte2);
        spieler.setHandkarten(handkarten);
    }

    @Test
    public void testKarteZuHandblatthinzufuegen() {
        spielerService.karteZuHandblatthinzufuegen(karte3, spieler);
        handkarten.add(karte3);
        assertEquals(handkarten, spieler.getHandkarten());
    }

    @Test
    public void testKarteausHandblattentfernden() {
        spielerService.karteausHandblattentfernden(karte2, spieler);
        handkarten.remove(karte2);
        assertEquals(handkarten, spieler.getHandkarten());
    }

//    @Test
//    public void testAuswaehlenSpielerFuerSpiel() {
//        spielerService.auswaehlenSpielerFuerSpiel(spiel);
//        assertTrue(!spiel.getSpielerDesSpieles().isEmpty());
//    }

//    @Test
//    public void testNeuerSpielerAnlegen() {
//        Spieler spielerHans = spielerService.neuerSpielerAnlegen("Hans", "abs@abs.de");
//        spieler.setName("Hans");
//        spieler.setEmail("abs@abs.de");
//        assertEquals(spieler, spielerHans);
//    }
}