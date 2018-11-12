package de.htwberlin.maumau.spiel;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.spieler.entity.Spieler;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpielServiceTest {

    private static SpielService service;
    Karte pik8 = new Karte();
    Karte pik9 = new Karte();
    List<Karte> ziehstapel;
    Spieler paul;


    @Before
    public void initialize() {
        service = new SpielServiceImpl();
        pik8.neueKarte(Farbe.PIK, "8", 8);
        pik9.neueKarte(Farbe.PIK, "9", 9);
        ziehstapel = new ArrayList<Karte>();
        paul = new Spieler();
    }

    @Test
    public void testMauistNoetig(){
        Spieler paul = new Spieler();
        List<Karte> handkarten = new ArrayList<>();
        handkarten.add(pik8);
        paul.setHandkarten(handkarten);
        assertTrue("Paul muss Mau sagen", service.istMauNoetig(paul));
    }

    @Test
    public void testMauistNichtNoetig(){
        List<Karte> handkarten = new ArrayList<>();
        handkarten.add(pik8);
        handkarten.add(pik9);
        paul.setHandkarten(handkarten);
        assertFalse("Paul muss Mau sagen", service.istMauNoetig(paul));
    }

    @Test
    public void testAuswaehlenKartenDeck(){
        List<Karte> karten = new ArrayList<>();
        assertEquals(karten, service.auswaehlenKartendeck(1));

    }

    @Test
    public void testZieheKarteVomZiehStapel(){
        ziehstapel.add(pik8);
        assertEquals(pik8, service.ziehenKarteVomZiehstapel(ziehstapel));
    }

    @Test
    public void testMussGemischtWerden(){
        assertTrue("Es müsste gemischt werden", service.mussGemischtWerden(ziehstapel));
    }

    @Test
    public void testMussNichtGemischtWerden(){
        ziehstapel.add(pik8);
        assertFalse("Es muss nicht gemischt werden", service.mussGemischtWerden(ziehstapel));
    }

    @Test
    public void testAnzahlStartkartenBestimmen4SpielerBei32Karten(){
        List<Spieler> spielerliste = Arrays.asList(new Spieler(),new Spieler(),new Spieler(),new Spieler());
        assertEquals(5, service.anzahlStartkartenbestimmen(spielerliste));
    }

    @Test
    public void testAnzahlStartkartenBestimmen4SpielerBei52Karten(){
        List<Spieler> spielerliste = Arrays.asList(new Spieler(),new Spieler(),new Spieler(),new Spieler());
        assertEquals(7, service.anzahlStartkartenbestimmen(spielerliste));
    }

    @Test
    public void testAnzahlStartkartenBestimmen6SpielerBei32Karten(){
        List<Spieler> spielerliste = Arrays.asList(new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler());
        assertEquals(4, service.anzahlStartkartenbestimmen(spielerliste));
    }

    @Test
    public void testAnzahlStartkartenBestimmen10SpielerBei52Karten(){
        List<Spieler> spielerliste = Arrays.asList(new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler(),new Spieler());
        assertEquals(4, service.anzahlStartkartenbestimmen(spielerliste));
    }
}
