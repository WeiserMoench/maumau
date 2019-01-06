/////**
//// * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
//// * @version 20181113
//// *
//// */
////
////package de.htwberlin.maumau.spiel;
////
////import de.htwberlin.maumau.karten.entity.Farbe;
////import de.htwberlin.maumau.karten.entity.Karte;
////import de.htwberlin.maumau.karten.export.KartenService;
////import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
////import de.htwberlin.maumau.spiel.entity.Spiel;
////import de.htwberlin.maumau.spiel.export.SpielService;
////import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
////import de.htwberlin.maumau.spieler.entity.Spieler;
////import de.htwberlin.maumau.spieler.export.SpielerService;
////import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;
////import org.junit.Before;
////import org.junit.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////
////import java.util.ArrayList;
////import java.util.Arrays;
////import java.util.List;
////
////import static org.junit.Assert.*;
////import static org.mockito.Mockito.when;
//
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SpielServiceTest {
//
////    @InjectMocks
//    private SpielService spielService;
//    @Mock
//    private SpielerService spielerService;
//    @Mock
//    private RegelnService regelnService;
//    @Mock
//    private KartenService kartenService;
//
//
//    private Karte herz7;
//    private Karte pik8;
//    private Karte pik9;
//    private Karte pikkoenig;
//    private List<Karte> ziehstapel;
//    private Spieler paul;
//    private List<Spieler> spielerliste;
//    private Spieler sven;
//    private Spiel spiel = new Spiel();
//
//
//    @Before
//    public void initialize() {
//        spielService = new SpielServiceImpl(spielerService, kartenService, regelnService, regelnService);
////        spielerService = new SpielerServiceImpl();
//        herz7 = new Karte((Farbe.HERZ), "7");
//        pik8 = new Karte(Farbe.PIK, "8");
//        pik9 = new Karte(Farbe.PIK, "9");
//        pikkoenig = new Karte(Farbe.PIK, "König");
//        ziehstapel = new ArrayList<>();
//        paul = new Spieler();
//        sven = new Spieler();
//        spiel.setFarbe(Farbe.PIK);
//        spielerliste = new ArrayList<>();
//        spielerliste.add(paul);
//        spielerliste.add(sven);
//        ziehstapel.add(pik8);
//        ziehstapel.add(pik9);
//        ziehstapel.add(herz7);
//
//        //Spiel vorbereiten
//        spiel.setAblagestapelkarten(ziehstapel);
//        spiel.setZiehstapelkarten(ziehstapel);
//        spiel.setAktiverSpieler(paul);
//        spiel.setIstSpielrichtungRechts(true);
//        spiel.setFarbe(Farbe.HERZ);
//        spiel.setSpielerDesSpieles(spielerliste);
//
//    }
//
////
////    @Test
////    public void testZieheKarteVomZiehStapel() {
////        ziehstapel.add(pik8);
////        assertEquals(pik8, service.ziehenKarteVomZiehstapel(ziehstapel));
////    }
////
//////    @Test
//////    public void testMussGemischtWerden() {
//////        assertTrue("Es müsste gemischt werden", service.mussGemischtWerden(ziehstapel));
//////    }
////
////    @Test
////    public void testMussNichtGemischtWerden() {
////        ziehstapel.add(pik8);
////        assertFalse("Es muss nicht gemischt werden", service.mussGemischtWerden(ziehstapel));
////    }
////
////    @Test
////    public void testAnzahlStartkartenBestimmen4SpielerBei32Karten() {
////        List<Spieler> spielerliste = Arrays.asList(new Spieler(), new Spieler(), new Spieler(), new Spieler());
////        List<Karte> stapel = new ArrayList<>();
////        for (int i = 0; i < 32; i++) {
////            stapel.add(new Karte(Farbe.PIK, "8"));
////        }
////        assertEquals(5, service.anzahlStartkartenbestimmen(spielerliste, stapel));
////    }
////
////    @Test
////    public void testAnzahlStartkartenBestimmen4SpielerBei52Karten() {
////        List<Spieler> spielerliste = Arrays.asList(new Spieler(), new Spieler(), new Spieler(), new Spieler());
////        List<Karte> stapel = new ArrayList<>();
////        for (int i = 0; i < 52; i++) {
////            stapel.add(new Karte(Farbe.PIK, "8"));
////        }
////        assertEquals(6, service.anzahlStartkartenbestimmen(spielerliste, stapel));
////    }
////
////    @Test
////    public void testAnzahlStartkartenBestimmen6SpielerBei32Karten() {
////        List<Spieler> spielerliste = Arrays.asList(new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler());
////        List<Karte> stapel = new ArrayList<>();
////        for (int i = 0; i < 32; i++) {
////            stapel.add(new Karte(Farbe.PIK, "8"));
////        }
////        assertEquals(3, service.anzahlStartkartenbestimmen(spielerliste, stapel));
////    }
////
////    @Test
////    public void testAnzahlStartkartenBestimmen10SpielerBei52Karten() {
////        List<Spieler> spielerliste = Arrays.asList(new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler(), new Spieler());
////        List<Karte> stapel = new ArrayList<>();
////        for (int i = 0; i < 52; i++) {
////            stapel.add(new Karte(Farbe.PIK, "8"));
////        }
////        assertEquals(4, service.anzahlStartkartenbestimmen(spielerliste, stapel));
////    }
////
////    @Test
////    public void testEntfernteGezogeneKarteVomStapelRest1() {
////        ziehstapel.add(pik8);
////        ziehstapel.add(pik9);
////
////        assertEquals(1, service.entferneGezogendeKarteVomZiehstapel(ziehstapel, pik8).size());
////    }
////
////    @Test
////    public void testEntfernteGezogeneKarteVomStapelRest0() {
////        ziehstapel.add(pik8);
////        System.out.println(ziehstapel.get(0).getFarbe());
////        assertEquals(0, service.entferneGezogendeKarteVomZiehstapel(ziehstapel, pik8).size());
////    }
////
////    @Test
////    public void testAusteilenStart() {
////        ziehstapel.add(pik8);
////        ziehstapel.add(pik9);
////        ziehstapel.add(pikkoenig);
////        spielerliste.add(paul);
////        spielerliste.add(sven);
////        assertEquals(1, service.austeilenStart(ziehstapel, spielerliste, 1).size());
////    }
////
////    @Test
////    public void test1ZuZiehendeKarte() {
////        ziehstapel.add(pik8);
////        ziehstapel.add(pik9);
////        spielerliste.add(paul);
////        assertEquals(1, service.zuZiehendeKarte(1, ziehstapel, paul).size());
////    }
////
////    @Test
////    public void test2ZuZiehendeKarte() {
////        ziehstapel.add(pik8);
////        ziehstapel.add(pik9);
////        spielerliste.add(paul);
////        assertEquals(0, service.zuZiehendeKarte(2, ziehstapel, paul).size());
////    }
////
////    @Test
////    public void testPruefeDasMauGesetzt() {
////        paul.setMauistgesetzt(true);
////        assertTrue("Spieler müsste Mau gesetzt haben", service.pruefeAufMau(paul));
////    }
////
////    @Test
////    public void testPruefeDasMauNichtGesetzt() {
////        assertFalse("Spieler müsste Mau nicht gesetzt haben", service.pruefeAufMau(paul));
////    }
////
////    @Test
////    public void testAendereSpielrichtungVonRechts() {
////        spiel.setIstSpielrichtungRechts(true);
////        service.aendernSpielrichtung(spiel);
////        assertEquals(false, spiel.isIstSpielrichtungRechts());
////    }
////
////    @Test
////    public void testAendereSpielrichtungVonNichtRechts() {
////        spiel.setIstSpielrichtungRechts(false);
////        service.aendernSpielrichtung(spiel);
////        assertEquals(true, spiel.isIstSpielrichtungRechts());
////    }
////
////    @Test
////    public void testLegeKarteAufAblagestabelBeiVorher0() {
////        assertEquals(1, service.legenKarteAufAblageStapel(paul, ziehstapel, pik8).size());
////    }
////
////    @Test
////    public void testLegeKarteAufAblagestabelBeiVorher1() {
////        ziehstapel.add(pik9);
////        assertEquals(2, service.legenKarteAufAblageStapel(paul, ziehstapel, pik8).size());
////    }
////
////
////    @Test
////    public void testAendernFarbe() {
////        Spiel testSpiel = service.aendernFarbe(spiel, Farbe.HERZ);
////        assertEquals(Farbe.HERZ, testSpiel.getFarbe());
////    }
////
////    @Test
////    public void testAendernFarbeAberKeineVeraenderung() {
////        Spiel testSpiel = service.aendernFarbe(spiel, Farbe.PIK);
////        assertEquals(Farbe.PIK, testSpiel.getFarbe());
////    }
////
////    @Test
////    public void testErmittleSpielende() {
////        assertTrue("das spiel hätte zu Ende sein müssen", service.ermittleSpielende(paul));
////    }
////
////    @Test
////    public void testErmittleSpielendeNegativ() {
////        spielerService.karteZuHandblatthinzufuegen(pik8, paul);
////        assertFalse("das spiel hätte nicht zu Ende sein müssen", service.ermittleSpielende(paul));
////    }
////
////    @Test
////    public void testeSetzeMau() {
////        service.setzeMau(paul);
////        assertTrue("Mau hätte gesetzt sein müssen", paul.isMauistgesetzt());
////    }
////
////    @Test
////    public void testAnlegenSpiel() {
////        Spiel testSpiel = spielService.anlegenSpiel();
////        assertEquals(spiel, testSpiel);
////
////    }
////
////
//
//
//    @Test
//    public void testMussGemischtWerden() {
//        Spiel inSpiel = new Spiel();
//        List<Karte> stapel = new ArrayList<>();
//        stapel.addAll(ziehstapel);
//        List<Karte> ziehKarten = new ArrayList<>();
//        inSpiel.setZiehstapelkarten(ziehKarten);
//        inSpiel.setAblagestapelkarten(stapel);
//        ziehKarten.add(pikkoenig);
//        ziehKarten.add(pik8);
////        when(kartenService.mischenKartenstapel(stapel, false)).thenReturn(ziehKarten);
//
//
//        assertTrue(!spielService.mussGemischtWerden(inSpiel).getZiehstapelkarten().isEmpty());
//
//
//    }
//}
