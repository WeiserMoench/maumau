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
import de.htwberlin.maumau.regelnmaumau.impl.ErweiterteRegelnServiceImpl;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SpielServiceImpl implements SpielService {

    private static SpielerService spielerService = new SpielerServiceImpl();

    private KartenService kartenService = new KartenServiceImpl();
    private ErweiterteRegelnServiceImpl regeln = new ErweiterteRegelnServiceImpl(); //DJ sollte hier sein



    @Override

    public Spiel anlegenSpiel(List<List> spielerliste) {
        //Testimplementierung für Controller und Viewer
        for (List<String> spieler : spielerliste) {
            for (String wert :spieler) {
                System.out.println("Wert: " + wert);
            }
            
        }
        return null;
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
    public Karte ziehenKarteVomZiehstapel(List<Karte> ziehStapel) {
        Karte karte = ziehStapel.get(0);

        return karte;
    }

    public Spiel legeKarte(Karte zulegendeKarte, Spieler spieler, Spiel spiel){
        if(regeln.darfKartegelegtwerden(spiel.getAblagestapelkarten().get(spiel.getAblagestapelkarten().size()-1),zulegendeKarte, spiel.getFarbe())){
            spiel.setAblagestapelkarten(legenKarteAufAblageStapel(spieler, spiel.getAblagestapelkarten(), zulegendeKarte));
            spiel.setSummeZuziehendeKarten(regeln.mussZweiKartenZiehen(zulegendeKarte, spiel.getSummeZuziehendeKarten()));
            spiel.setIstSpielrichtungRechts(regeln.richtungWechsel(zulegendeKarte));
            spiel.setErfolgreichgelegt(true);
            if(regeln.mussSichFarbeWuenschen(zulegendeKarte)){
                spiel.setMussFarbeWuenschen(true);
            }else{
//                 spiel=naechsterSpieler(spiel);
            }
            return spiel;
        }
        return spiel;

    }


    public Spiel farbeGewaehlt(Spiel spiel, Farbe farbe){
        spiel.setFarbe(farbe);
        spiel.setMussFarbeWuenschen(false);
        spiel=naechsterSpieler(spiel);
        return spiel;
    }


    public Spiel naechsterSpieler(Spiel spiel){
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
            indexNaechsterSpieler = spiel.getSpielerDesSpieles().indexOf(spiel.getAktiverSpieler()) + veraenderung;
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
        karteStapel.remove(karte);
        return karteStapel;
    }

    // ist das nicht intern?
    // macht das so sinn, was ist mit den veraenderten Spielern
    @Override
    public List<Karte> austeilenStart(List<Karte> kartenDeck, List<Spieler> spielerListe, int durchgaenge) {
        for (int runden = 0; runden < durchgaenge; runden++) {
            for (int spielerzaehler = 0; spielerzaehler < spielerListe.size(); spielerzaehler++) {
                Karte karte = kartenDeck.get(kartenDeck.size() - 1);
                spielerService.karteausHandblattentfernden(karte, spielerListe.get(spielerzaehler));
                kartenDeck.remove(karte);
            }
        }
        return kartenDeck;
    }

    // ist das nicht intern?
    @Override
    public List<Karte> zuZiehendeKarte(int anzahl, List<Karte> karteStapel, Spieler spieler) {
        List<Spieler> spielerliste = new ArrayList<>();
        spielerliste.add(spieler);
        austeilenStart(karteStapel, spielerliste, anzahl);
        return karteStapel;
    }

    // ist das nicht intern?
    @Override
    public List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {
        spielerService.karteausHandblattentfernden(karte, spieler);
        kartenAblagestapel.add(karte);
        return kartenAblagestapel;
    }

    //intern und wird von regeln Aufgerufen
    @Override
    public void aendernSpielrichtung(Spiel spiel) {
        spiel.setIstSpielrichtungRechts(!spiel.isIstSpielrichtungRechts());
    }

    //noch noetig? macht doch nun farbeGewaehlt
    @Override
    public Spiel aendernFarbe(Spiel spiel, Farbe neueFarbe) {
        spiel.setFarbe(neueFarbe);
        return spiel;
    }

    public boolean ermittleSpielende(Spieler spieler) {
        return !spieler.getHandkarten().isEmpty();
    }

    private boolean pruefeAufMau(Spieler spieler) {
        return spieler.isMauistgesetzt() == true;
    }

    private boolean istMauNoetig(Spieler spieler) {
        return spieler.getHandkarten().size() == 1;
    }

    public void setzeMau(Spieler spieler) {
        spieler.setMauistgesetzt(true);
    }

    private int anzahlStartkartenbestimmen(List<Spieler> spielerListe, List<Karte> ziehstapel) {
        int anzahlkarten;
        anzahlkarten = (int) Math.floor((ziehstapel.size() - 10) / spielerListe.size());
        if (anzahlkarten > 6) {
            anzahlkarten = 6;
        }
        return anzahlkarten;
    }

    private boolean mussGemischtWerden(List<Karte> ziehstapel) {
        return ziehstapel.size() == 0;
    }
}
