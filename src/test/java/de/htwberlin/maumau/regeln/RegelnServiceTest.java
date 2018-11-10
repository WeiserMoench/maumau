package de.htwberlin.maumau.regeln;

import de.htwberlin.maumau.karten.entity.Karte;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegelnServiceTest {


    private RegelnService regelnService = new RegelnServiceImpl();
    private Karte karte1 = new Karte("pik", "10", 1);
    private Karte karte2 = new Karte("pik", "9", 2);
    private Karte karte3 = new Karte("herz", "9", 3);
    private Karte bube = new Karte("herz", "bube", 4);
    private Karte karte7 = new Karte("pik", "7", 5);
    private Karte karte8 = new Karte("pik", "8", 6);

    @Test
    public void testDarfKartegelegtwerden() {

        assertTrue(regelnService.darfKartegelegtwerden(karte1, karte2, "pik"));
        assertFalse((regelnService.darfKartegelegtwerden(karte1, karte3, "pik")));
    }

    @Test
    public void testMussSichFarbeWuenschen() {
        assertTrue(regelnService.mussSichFarbeWuenschen(bube));
        assertFalse(regelnService.mussSichFarbeWuenschen(karte1));
    }

    @Test
    public void testMussZweiKartenZiehen() {

        assertEquals(2, regelnService.mussZweiKartenZiehen(karte7, 0));
        assertEquals(4, regelnService.mussZweiKartenZiehen(karte7, 2));
    }

    @Test
    public void testMussRundeAussetzen() {
        assertTrue(regelnService.mussRundeAussetzen(karte8));
        assertFalse(regelnService.mussRundeAussetzen(karte1));
    }

    @Test
    public void testRichtungWechsel() {
        assertTrue(regelnService.richtungWechsel(karte2));
        assertFalse(regelnService.richtungWechsel(bube));
    }
}
