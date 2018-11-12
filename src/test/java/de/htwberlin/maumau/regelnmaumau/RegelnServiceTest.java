package de.htwberlin.maumau.regelnmaumau;

import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.regelnmaumau.impl.RegelnServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegelnServiceTest {

    private static RegelnService service;

    @BeforeClass
    public static void initialize() {service = new RegelnServiceImpl();}

    @Test
    public void testMussRundeAussetzen(){
        Karte pik8 = new Karte();
        pik8.setFarbe("PIK");
        pik8.setWert("8");
        pik8.setKarteID(8);
        Assert.assertTrue("Karte pik8 naechster muss Aussetzen", service.mussRundeAussetzen(pik8));
    }

    @Test
    public void testMussNichtRundeAussetzen(){
        Karte pikkoenig = new Karte();
        pikkoenig.setFarbe("PIK");
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        Assert.assertFalse("Karte Pik König, naechster darf spielen", service.mussRundeAussetzen(pikkoenig));
    }

    @Test
    public void testZweiKartenZiehenBeiVorherNull(){
        Karte pik7 = new Karte();
        pik7.setFarbe("PIK");
        pik7.setWert("7");
        pik7.setKarteID(7);
        Assert.assertSame(2, service.mussZweiKartenZiehen(pik7,0));
    }

    @Test
    public void testZweiKartenZiehenBeiVorherVier(){
        Karte herz7 = new Karte();
        herz7.setFarbe("HERZ");
        herz7.setWert("7");
        herz7.setKarteID(107);
        Assert.assertSame(6, service.mussZweiKartenZiehen(herz7,4));
    }

    @Test
    public void testKeineZweiKatenZiehen(){
        Karte pikkoenig = new Karte();
        pikkoenig.setFarbe("PIK");
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        Assert.assertSame(0, service.mussZweiKartenZiehen(pikkoenig,0));
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicheFarbe(){
        Karte pikkoenig = new Karte();
        pikkoenig.setFarbe("PIK");
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        Karte pik7 = new Karte();
        pik7.setFarbe("PIK");
        pik7.setWert("7");
        pik7.setKarteID(7);
        Assert.assertTrue("Farbe identisch Wert nicht", service.darfKartegelegtwerden(pikkoenig, pik7, 0)); //warum int???
    }

    @Test
    public void testKarteDarfGelegtWerdenGleicherWert(){
        Karte herz7 = new Karte();
        herz7.setFarbe("HERZ");
        herz7.setWert("7");
        herz7.setKarteID(107);
        Karte pik7 = new Karte();
        pik7.setFarbe("PIK");
        pik7.setWert("7");
        pik7.setKarteID(7);
        Assert.assertTrue("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pik7, 0));
    }

    @Test
    public void testKarteDarfNICHTGelegt(){
        Karte herz7 = new Karte();
        herz7.setFarbe("HERZ");
        herz7.setWert("7");
        herz7.setKarteID(107);
        Karte pikkoenig = new Karte();
        pikkoenig.setFarbe("PIK");
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        Assert.assertFalse("Wert identisch Farbe nicht", service.darfKartegelegtwerden(herz7, pikkoenig, 0));
    }

    @Test
    public void testRichtungswechsel(){
        Karte pik9 = new Karte();
        pik9.setFarbe("PIK");
        pik9.setWert("9");
        pik9.setKarteID(9);
        Assert.assertTrue("Richtungswechsel durch pik 9", service.richtungWechsel(pik9));
    }

    @Test
    public void testKeinRichtungswechsel(){
        Karte pikkoenig = new Karte();
        pikkoenig.setFarbe("PIK");
        pikkoenig.setWert("König");
        pikkoenig.setKarteID(12);
        Assert.assertFalse("Kein Richtungswechsel durch pik koenig", service.richtungWechsel(pikkoenig));
    }
}
