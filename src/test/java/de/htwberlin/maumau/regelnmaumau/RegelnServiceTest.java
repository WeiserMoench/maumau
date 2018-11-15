/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.impl.ErweiterteRegelnServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RegelnServiceTest {

    private static RegelnService service;
    Karte pik8;
    Karte pikkoenig;
    Karte pik7;
    Karte herz7;
    Karte herzBube;

    @Before
    public void initialize() {
        service = new ErweiterteRegelnServiceImpl();
        pik8.setFarbe(Farbe.PIK);
        pik8.setWert("8");
        pikkoenig.setFarbe(Farbe.PIK);
        pikkoenig.setWert("König");
        pik7.setFarbe(Farbe.PIK);
        pik7.setWert("7");
        herz7.setFarbe(Farbe.HERZ);
        herz7.setWert("7");
        herzBube = new Karte(Farbe.HERZ, "Bube");
    }

    @Test
    public void testMussRundeAussetzen(){
        assertTrue("Karte pik8 naechster muss Aussetzen", service.mussRundeAussetzen(pik8));
    }

    @Test
    public void testMussNichtRundeAussetzen(){
        assertFalse("Karte Pik König, naechster darf spielen", service.mussRundeAussetzen(pikkoenig));
    }

    @Test
    public void testZweiKartenZiehenBeiVorherNull(){
        assertSame(2, service.mussZweiKartenZiehen(pik7,0));
    }

    @Test
    public void testZweiKartenZiehenBeiVorherVier(){
        assertSame(6, service.mussZweiKartenZiehen(herz7,4));
    }

    @Test
    public void testKeineZweiKatenZiehen(){
        assertSame(0, service.mussZweiKartenZiehen(pikkoenig,0));
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicheFarbe(){
        assertTrue("Farbe identisch Wert nicht", service.darfKartegelegtwerden(pikkoenig, pik7, Farbe.PIK)); //warum int???
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicherWert(){
        assertTrue("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, Farbe.HERZ));
    }

    @Test
    public void testKarteDarfGelegtWerdenNachWunsch(){
        assertTrue("Farbe passt zu Wunsch", service.darfKartegelegtwerden(herz7, pik7, Farbe.PIK));
    }


    @Test
    public void testKarteDarfNICHTGelegt(){
        assertFalse("Weder Farbe noch wert passen", service.darfKartegelegtwerden(herz7, pikkoenig, Farbe.HERZ));
    }

    @Test
    public void testRichtungswechsel(){
        Karte pik9 = new Karte(Farbe.PIK, "9");
        assertTrue("Richtungswechsel durch pik 9", service.richtungWechsel(pik9));
    }

    @Test
    public void testKeinRichtungswechsel(){
        assertFalse("Kein Richtungswechsel durch pik koenig", service.richtungWechsel(pikkoenig));
    }

    @Test
    public void testMussSichFarbeWuenschen(){
        assertTrue("Spieler müsste sich Farbe wünschen", service.mussSichFarbeWuenschen(herzBube));

    }

    @Test
    public void testMussSichKeineFarbeWuenschen(){
        assertTrue("Spieler darf sich keine Farbe wünschen", service.mussSichFarbeWuenschen(pikkoenig));

    }
}
