package de.htwberlin.maumau.virtuellerspielerverwaltung;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.virtuellerspielerverwaltung.impl.KiServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.Assert.*;

public class KiServiceTest {

    private Karte herz7;

    @InjectMocks
    private KiServiceImpl kiService;

    @Mock
    private Random random;


    private Spieler spieler;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        herz7 = new Karte((Farbe.HERZ), "7");
//        kiService = new KiServiceImpl();
    }

    @Test
    public void testMausSetzen() {
        spieler = new Spieler();
        spieler.getHandkarten().add(herz7);
        Mockito.when(random.nextInt((10 - 0) + 1)).thenReturn(2).thenReturn(9);

        assertFalse(kiService.mauSetzen(spieler));
        assertTrue(kiService.mauSetzen(spieler));

    }

    @Test
    public void testKiAnlegen() {
        String cp = "Computer1";
        assertEquals(cp,kiService.kiAnlegen(1) );

    }

    @Test
    public void testKiMussFarbeWuenschen() {
        Mockito.when(random.nextInt(4)).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3);
        assertEquals(Farbe.PIK, kiService.kiMussFarbeWuenschen());
        assertEquals(Farbe.KARO, kiService.kiMussFarbeWuenschen());
        assertEquals(Farbe.KREUZ, kiService.kiMussFarbeWuenschen());
        assertEquals(Farbe.HERZ, kiService.kiMussFarbeWuenschen());

    }
}
