/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 */

package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.regelnmaumau.impl.EinfacheRegelnServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EinfacherRegelnServiceTest {

    private  RegelnService service;
    private Karte pik8;
    private Karte pikkoenig;
    private Karte pik7;
    private Karte herz7;
    private Karte herzBube;

    @Before
    public void initialize() {
        service = new EinfacheRegelnServiceImpl();
        pik8 = new Karte(Farbe.PIK, "8");
        pikkoenig = new Karte(Farbe.PIK, "König");
        pik7 = new Karte(Farbe.PIK, "7");
        herz7 = new Karte(Farbe.HERZ, "7");
        herzBube = new Karte(Farbe.HERZ, "Bube");
    }

    @Test
    public void testMussNichtRundeAussetzenBeiAcht(){
        assertFalse("Karte pik8 naechster muss nicht aussetzen", service.mussRundeAussetzen(pik8));
    }

    @Test
    public void testMussNichtRundeAussetzen(){
        assertFalse("Karte Pik König, naechster darf spielen", service.mussRundeAussetzen(pikkoenig));
    }

    @Test
    public void testKeineZweiKartenZiehenBeiSieben(){
        assertSame(0, service.mussZweiKartenZiehen(pik7,0));
    }

    @Test
    public void testKeineZweiKatenZiehen(){
        assertSame(0, service.mussZweiKartenZiehen(pikkoenig,0));
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicheFarbe(){
        assertTrue("Farbe identisch Wert nicht", service.darfKartegelegtwerden(pikkoenig, pik7, Farbe.PIK));
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicherWert(){
        assertTrue("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, Farbe.HERZ));
    }

    @Test
    public void testKarteDarfGelegtWerdenWeilBubeGleicherWert(){
        assertTrue("Farbe passt zu Wunsch", service.darfKartegelegtwerden(herzBube, herz7, Farbe.PIK));
    }

    @Test
    public void testKarteDarfNichtGelegtWerdenTrotzBube(){
        assertFalse("Farbe passt zu Wunsch", service.darfKartegelegtwerden(herzBube, pik7, Farbe.PIK));
    }


    @Test
    public void testKarteDarfNichtGelegt(){
        assertFalse("Weder Farbe noch wert passen", service.darfKartegelegtwerden(herz7, pikkoenig, Farbe.HERZ));
    }

    @Test
    public void testKeinRichtungswechselTrotzNeun(){
        Karte pik9 = new Karte(Farbe.PIK, "9");
        assertFalse("Richtungswechsel durch pik 9", service.richtungWechsel(pik9));
    }

    @Test
    public void testKeinRichtungswechsel(){
        assertFalse("Kein Richtungswechsel durch pik koenig", service.richtungWechsel(pikkoenig));
    }

    @Test
    public void testMussSichKeineFarbeWuenschenTrotzBube(){
        assertFalse("Spieler müsste sich Farbe wünschen", service.mussSichFarbeWuenschen(herzBube));

    }

    @Test
    public void testMussSichKeineFarbeWuenschen(){
        assertFalse("Spieler darf sich keine Farbe wünschen", service.mussSichFarbeWuenschen(pikkoenig));

    }
}
