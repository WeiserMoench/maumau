/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181115
 *
 */

package de.htwberlin.maumau.spiel.impl;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.export.KartenService;
import de.htwberlin.maumau.karten.impl.KartenServiceImpl;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.regelnmaumau.impl.EinfacheRegelnServiceImpl;
import de.htwberlin.maumau.regelnmaumau.impl.ErweiterteRegelnServiceImpl;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpielServiceImpl implements SpielService {

    private static SpielerService spielerService = new SpielerServiceImpl();

    private KartenService kartenService = new KartenServiceImpl();
    private RegelnService regeln;
    private static Logger log = Logger.getRootLogger();

    @Override

    public Spiel anlegenSpiel(List<String> spielerliste, boolean erweiterteRegeln) {
        log.setLevel(Level.WARN);//ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF
        log.debug("anlegenSpiel");
        Spiel spiel = new Spiel();
        if(erweiterteRegeln){
            regeln = new ErweiterteRegelnServiceImpl();
        }else{
            regeln = new EinfacheRegelnServiceImpl();
        }
        List<Spieler> spielerListe = new ArrayList<>();
        List<Karte> ablagestapel = new ArrayList<>();


            for (String wert :spielerliste) {
                Spieler derSpieler = new Spieler();
                derSpieler.setName(wert);
                spielerListe.add(derSpieler);
            }


        spiel.setSpielerDesSpieles(spielerListe);
        spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(0));

        spiel.setZiehstapelkarten(kartenService.mischenKartenstapel(kartenService.anlegenKartenstapel(), false));
        spiel.setZiehstapelkarten(austeilenStart(spiel.getZiehstapelkarten(), spiel.getSpielerDesSpieles(), anzahlStartkartenbestimmen(spiel.getSpielerDesSpieles(), spiel.getZiehstapelkarten())));
        ablagestapel.add(spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size()-1));
        spiel.setZiehstapelkarten(entferneGezogendeKarteVomZiehstapel(spiel.getZiehstapelkarten(), spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size()-1)));
        spiel.setAblagestapelkarten(ablagestapel);
        return spiel;
    }

    //Dustin

    //noetig?
    // ist das nicht intern?
    // aufgabe ueberarbeiten und fixes Kartendeck nutzen
    @Override
    public List<Karte> auswaehlenKartendeck(int kartenblatt) {
//        kartenService.anlegenKartenstapel();
        return null;
    }

    //Dustin
    // ist das nicht intern?
    @Override
    public Spiel ziehenKarteVomZiehstapel(Spiel spiel) {
        log.debug("ziehenKarteVomZiehstapel");
        Karte karte = spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size()-1);
        spielerService.karteZuHandblatthinzufuegen(karte, spiel.getAktiverSpieler());
        spiel.setZiehstapelkarten(entferneGezogendeKarteVomZiehstapel(spiel.getZiehstapelkarten(), karte));
        return spiel;
    }

    public Spiel legeKarte(Karte zulegendeKarte, Spieler spieler, Spiel spiel){
        log.debug("legeKarte");
        if(regeln.darfKartegelegtwerden(spiel.getAblagestapelkarten().get(spiel.getAblagestapelkarten().size()-1),zulegendeKarte, spiel.getFarbe())){
            spiel.setAblagestapelkarten(legenKarteAufAblageStapel(spieler, spiel.getAblagestapelkarten(), zulegendeKarte));
            spiel.setSummeZuziehendeKarten(regeln.mussZweiKartenZiehen(zulegendeKarte, spiel.getSummeZuziehendeKarten()));
            spiel.setIstSpielrichtungRechts(regeln.richtungWechsel(zulegendeKarte));
            spiel.setErfolgreichgelegt(true);
            if(regeln.mussSichFarbeWuenschen(zulegendeKarte)){
                spiel.setMussFarbeWuenschen(true);
            }
            return spiel;
        }
        return spiel;

    }


    public Spiel farbeGewaehlt(Spiel spiel, Farbe farbe){
        log.debug("farbeGewaehlt");
        spiel.setFarbe(farbe);
        spiel.setMussFarbeWuenschen(false);
        return spiel;
    }


    public Spiel naechsterSpieler(Spiel spiel){
        log.debug("naechsterSpieler");
        spiel.setErfolgreichgelegt(false);
        int veraenderung = 1;
        int indexNaechsterSpieler;
        int laengeSpielerliste = spiel.getSpielerDesSpieles().size();

        if (regeln.mussRundeAussetzen(spiel.getAblagestapelkarten().get(spiel.getAblagestapelkarten().size() - 1))) {
            veraenderung++;
        }


        if (spiel.isIstSpielrichtungRechts()) {
            indexNaechsterSpieler = spiel.getSpielerDesSpieles().indexOf(spiel.getAktiverSpieler()) + veraenderung;
        } else {
            indexNaechsterSpieler = spiel.getSpielerDesSpieles().indexOf(spiel.getAktiverSpieler()) - veraenderung;
        }
        if (indexNaechsterSpieler >= laengeSpielerliste) {
            spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(indexNaechsterSpieler-laengeSpielerliste));
        } else {
            if (indexNaechsterSpieler < 0) {
                spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(laengeSpielerliste+indexNaechsterSpieler));
            }else {
                spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(indexNaechsterSpieler));
            }
        }
        return spiel;
    }



    //Dustin
    // ist das nicht intern?
    @Override
    public List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte) {
        log.debug("entferneGezogendeKarteVomZiehstapel");
        karteStapel.remove(karte);
        return karteStapel;
    }

    // ist das nicht intern?
    // macht das so sinn, was ist mit den veraenderten Spielern
    @Override
    public List<Karte> austeilenStart(List<Karte> ziehstapel, List<Spieler> spielerListe, int durchgaenge) {
        log.debug("austeilenStart");
        for (int runden = 0; runden < durchgaenge; runden++) {
            for (int spielerzaehler = 0; spielerzaehler < spielerListe.size(); spielerzaehler++) {
                Karte karte = ziehstapel.get(ziehstapel.size() - 1);
                spielerService.karteZuHandblatthinzufuegen(karte, spielerListe.get(spielerzaehler));
                ziehstapel.remove(karte);
            }
        }
        return ziehstapel;
    }

    // ist das nicht intern?
    @Override
    public List<Karte> zuZiehendeKarte(int anzahl, List<Karte> karteStapel, Spieler spieler) {
        log.debug("zuZiehendeKarte");
        List<Spieler> spielerliste = new ArrayList<>();
        spielerliste.add(spieler);
        austeilenStart(karteStapel, spielerliste, anzahl);
        return karteStapel;
    }

    // ist das nicht intern?
    @Override
    public List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {
        log.debug("legenKarteAufAblageStapel");
        spielerService.karteausHandblattentfernden(karte, spieler);
        kartenAblagestapel.add(karte);
        return kartenAblagestapel;
    }

    //intern und wird von regeln Aufgerufen
    @Override
    public void aendernSpielrichtung(Spiel spiel) {
        log.debug("aendernSpielrichtung");
        spiel.setIstSpielrichtungRechts(!spiel.isIstSpielrichtungRechts());
    }

    //noch noetig? macht doch nun farbeGewaehlt
    @Override
    public Spiel aendernFarbe(Spiel spiel, Farbe neueFarbe) {
        log.debug("aendernFarbe");
        spiel.setFarbe(neueFarbe);
        return spiel;
    }

    public boolean ermittleSpielende(Spieler spieler) {
        log.debug("ermittleSpielende");
        return !spieler.getHandkarten().isEmpty();
    }

    public Spiel pruefeAufMau(Spiel spiel) {
        log.debug("pruefeAufMau");
        List<Spieler> spielerAlsListe = new ArrayList<>();
        spielerAlsListe.add(spiel.getAktiverSpieler());
        if(istMauNoetig(spiel.getAktiverSpieler())){
            if(!spiel.getAktiverSpieler().isMauistgesetzt()){
                austeilenStart(spiel.getZiehstapelkarten(), spielerAlsListe, 2);
            }

        }
        return spiel;
    }

    private boolean istMauNoetig(Spieler spieler) {
        log.debug("istMauNoetig");
        return spieler.getHandkarten().size() == 1;
    }

    public void setzeMau(Spieler spieler, boolean neuerZustand) {
        log.debug("setzeMau");
        spieler.setMauistgesetzt(neuerZustand);
    }

    private int anzahlStartkartenbestimmen(List<Spieler> spielerListe, List<Karte> ziehstapel) {
        log.debug("anzahlStartkartenbestimmen");
        int anzahlkarten;
        anzahlkarten = (int) Math.floor((ziehstapel.size() - 10) / spielerListe.size());
        if (anzahlkarten > 6) {
            anzahlkarten = 6;
        }
        return anzahlkarten;
    }

    //Sollten alle Karten auf den Handkarten der Spieler sein, kommt ers zu einer Exception und dem Abbruch des Spieles,
    //da die Wahrscheinlichkeit sehr gering ist, das so gespielt wird, wird dieses Mal die Exception noch nicht gefangen und bearbeitet,
    //dies erfolgt bei der nächsten Abgabe
    /**
     *
     * @param spiel
     * @return
     */
    public Spiel mussGemischtWerden(Spiel spiel) {
        log.debug("mussGemischtWerden");
        List<Karte> ziehstapel;
        List<Karte> ablagestapel = new ArrayList<>();

        ziehstapel=spiel.getZiehstapelkarten();

        if(ziehstapel.size() == 0){
            ziehstapel=spiel.getAblagestapelkarten();
            ablagestapel.add(ziehstapel.get(ziehstapel.size()-1));
            ziehstapel.remove(ziehstapel.size()-1);
            spiel.setZiehstapelkarten(kartenService.mischenKartenstapel(ziehstapel, false));
            spiel.setAblagestapelkarten(ablagestapel);
        }
        return spiel;
    }
}
