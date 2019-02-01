/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20190201
 *
 */
package de.htwberlin.maumau.virtuellerspielerverwaltung.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.virtuellerspielerverwaltung.export.KiService;
import org.apache.log4j.Logger;

import java.util.Random;


public class KiServiceImpl implements KiService {
    private static Logger log = Logger.getRootLogger();

    @Override
    public boolean mauSetzen(Spieler spieler) {
        log.debug("KiServiceImpl - mauSetzen");
        if(spieler.getHandkarten().size()==1){
            Random rand = new Random();
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;

            return randomNum > 3;
        }
        return false;
    }

    @Override
    public String kiAnlegen(int kiZaehler) {
        log.debug("KiServiceImpl - kiAnlegen");
        kiZaehler++;
        String spielername = "Computer"+kiZaehler;
        return spielername;
    }

    @Override
    public Farbe kiMussFarbeWuenschen() {
        Farbe neueFarbe = null;
        Random random =new Random();
        int zahl = random.nextInt(4);
        switch (zahl){
            case 0:
                neueFarbe = Farbe.PIK;
                break;
            case 1:
                neueFarbe = Farbe.KARO;
                break;
            case 2:
                neueFarbe = Farbe.KREUZ;
                break;
            case 3:
                neueFarbe = Farbe.HERZ;
                break;
        }
        return neueFarbe;
    }
}
