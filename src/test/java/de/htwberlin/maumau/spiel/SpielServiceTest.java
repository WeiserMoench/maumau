package de.htwberlin.maumau.spiel;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
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

    private SpielService service;
    private Karte pik8;
    private Karte pik9;
    private List<Karte> ziehstapel;
    private Spieler paul;
    private List<Spieler> spielerliste;
    private Spieler sven;
    private Spiel spiel = new Spiel();


    @Before
    public void initialize() {
        service = new SpielServiceImpl();
        pik8 = new Karte(Farbe.PIK, "8");
        pik9 = new Karte(Farbe.PIK, "9");
        ziehstapel = new ArrayList<Karte>();
        paul = new Spieler();
        sven = new Spieler();
        spiel.setFarbe(Farbe.PIK);

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

    @Test
    public void testEntfernteGezogeneKarteVomStapelRest1(){
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        assertEquals(1, service.entferneGezogendeKarteVomZiehstapel(ziehstapel, pik8).size());
    }

    @Test
    public void testEntfernteGezogeneKarteVomStapelRest0(){
        ziehstapel.add(pik8);
        assertEquals(0, service.entferneGezogendeKarteVomZiehstapel(ziehstapel, pik8).size());
    }

    @Test
    public void testAusteilenStart(){
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        ziehstapel.add(pik9);
        spielerliste.add(paul);
        spielerliste.add(sven);
        assertEquals(1, service.austeilenStart(ziehstapel, spielerliste, 1).size());
    }

    @Test
    public void test1ZuZiehendeKarte(){
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        spielerliste.add(paul);
        assertEquals(1, service.zuZiehendeKarte(1, ziehstapel, paul).size());
    }

    @Test
    public void test2ZuZiehendeKarte(){
        ziehstapel.add(pik8);
        ziehstapel.add(pik9);
        spielerliste.add(paul);
        assertEquals(0, service.zuZiehendeKarte(2, ziehstapel, paul).size());
    }

    @Test
    public void testPruefeDasMauGesetzt(){
        paul.setMauistgesetzt(true);
        assertTrue("Spieler müsste Mau gesetzt haben", service.pruefeAufMau(paul));
    }

    @Test
    public void testPruefeDasMauNichtGesetzt(){
        assertFalse("Spieler müsste Mau nicht gesetzt haben", service.pruefeAufMau(paul));
    }

    @Test
    public void testAendereSpielrichtungNachNichtRechts(){
        assertFalse("Spielrichtung müsste gegen Rechts sein", service.aendernSpielrichtung());
    }

    @Test
    public void testAendereSpielrichtungNachRechts(){
        assertTrue("Spielrichtung müsste gegen Rechts sein", service.aendernSpielrichtung());
    }

    @Test
    public void testLegeKarteAufAblagestabelBeiVorher0(){
        assertEquals(1, service.legenKarteAufAblageStapel(paul, ziehstapel, pik8).size());
    }

    @Test
    public void testLegeKarteAufAblagestabelBeiVorher1(){
        ziehstapel.add(pik9);
        assertEquals(2, service.legenKarteAufAblageStapel(paul, ziehstapel, pik8).size());
    }


    @Test
    public void testAendernFarbe() {
        Spiel testSpiel = service.aendernFarbe(spiel, Farbe.HERZ);
        assertEquals(Farbe.HERZ, testSpiel.getFarbe());
    }

    @Test
    public void testAendernFarbeAberKeineVeraenderung() {
        Spiel testSpiel = service.aendernFarbe(spiel, Farbe.PIK);
        assertEquals(Farbe.PIK, testSpiel.getFarbe());
    }

    @Test
    public void testErmittleSpielende(){
        assertTrue("das spiel hätte zu Ende sein müssen", service.ermittleSpielende(paul));
    }

    @Test
    public void testeSetzeMau(){
        service.setzeMau(paul);
        assertTrue("Mau hätte gesetzt sein müssen", paul.isMauistgesetzt());
    }
}
