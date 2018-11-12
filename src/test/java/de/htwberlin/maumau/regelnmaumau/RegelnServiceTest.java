package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.impl.RegelnServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RegelnServiceTest {

    private static RegelnService service;
    Karte pik8 = new Karte();
    Karte pikkoenig = new Karte();
    Karte pik7 = new Karte();
    Karte herz7 = new Karte();

    @Before
    public void initialize() {
        service = new RegelnServiceImpl();
        pik8.setFarbe(Farbe.PIK);
        pik8.setWert("8");
        pik8.setKarteID(8);
        pikkoenig.setFarbe(Farbe.PIK);
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        pik7.setFarbe(Farbe.PIK);
        pik7.setWert("7");
        pik7.setKarteID(7);
        herz7.setFarbe(Farbe.HERZ);
        herz7.setWert("7");
        herz7.setKarteID(107);
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
        assertTrue("Farbe identisch Wert nicht", service.darfKartegelegtwerden(pikkoenig, pik7, 0)); //warum int???
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicherWert(){
        assertTrue("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, 0));
    }

    // nächste beiden Anpassen
//    @Test
//    public void testKarteDarfGelegtWerdenNachWunsch(){
//        assertTrue("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, 0));
//    }
//
//    @Test
//    public void testKarteDarfGelegtWerdenNachWunschAberFalsch(){
//        assertFalse("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, 0));
//    }

    @Test
    public void testKarteDarfNICHTGelegt(){
        assertFalse("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pikkoenig, 0));
    }

    @Test
    public void testRichtungswechsel(){
        Karte pik9 = new Karte();
        pik9.setFarbe(Farbe.PIK);
        pik9.setWert("9");
        pik9.setKarteID(9);
        assertTrue("Richtungswechsel durch pik 9", service.richtungWechsel(pik9));
    }

    @Test
    public void testKeinRichtungswechsel(){
        assertFalse("Kein Richtungswechsel durch pik koenig", service.richtungWechsel(pikkoenig));
    }
}
