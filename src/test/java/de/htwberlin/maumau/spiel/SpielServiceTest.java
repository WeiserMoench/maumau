package de.htwberlin.maumau.spiel;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.spieler.entity.Spieler;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpielServiceTest {

    private static SpielService service;

    @BeforeClass
    public static void initialize() {service = new SpielServiceImpl();}

    @Test
    public void testMauistNoetig(){
        Spieler paul = new Spieler();
        Karte pik8 = new Karte();
        pik8.neueKarte(Farbe.PIK, "8", 8);
        List<Karte> handkarten = new ArrayList<>();
        handkarten.add(pik8);
        paul.setHandkarten(handkarten);
        assertTrue("Paul muss Mau sagen", service.istMauNoetig(paul));
    }

    @Test
    public void testMauistNichtNoetig(){
        Spieler paul = new Spieler();
        Karte pik8 = new Karte();
        Karte pik9 = new Karte();
        pik8.neueKarte(Farbe.PIK, "8", 8);
        pik9.neueKarte(Farbe.PIK, "9", 9);
        List<Karte> handkarten = new ArrayList<>();
        handkarten.add(pik8);
        handkarten.add(pik9);
        paul.setHandkarten(handkarten);
        assertFalse("Paul muss Mau sagen", service.istMauNoetig(paul));
    }

    @Test
    public void testAuswaehlenKartenDeck(){
        List<Karte> karten = new ArrayList<Karte>();
        assertEquals(karten, );

    }

}
