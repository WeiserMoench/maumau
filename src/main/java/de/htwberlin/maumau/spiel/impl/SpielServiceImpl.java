/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.spiel.impl;


import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.karten.export.KartenService;
import de.htwberlin.maumau.regelnmaumau.export.RegelnService;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpielServiceImpl implements SpielService {

    public SpielServiceImpl(SpielerService spielerService, KartenService kartenService, RegelnService einfacheRegeln, RegelnService erweiterteRegeln) {
        this.spielerService = spielerService;
        this.kartenService = kartenService;
        this.regelnEinfach = einfacheRegeln;
        this.regelnErweitert = erweiterteRegeln;
    }

    private SpielerService spielerService;
    private KartenService kartenService;
    private RegelnService regelnEinfach;
    private RegelnService regelnErweitert;
    private RegelnService regeln;
    private static Logger log = Logger.getRootLogger();

    @Override
    public Spiel anlegenSpiel(List<String> spielerliste, boolean erweiterteRegeln) {
        log.debug("anlegenSpiel");
        Spiel spiel = new Spiel();
        if (erweiterteRegeln) {
            regeln = regelnErweitert;
        } else {
            regeln = regelnEinfach;
        }
        List<Spieler> spielerListe = new ArrayList<>();
        List<Karte> ablagestapel = new ArrayList<>();


        for (String wert : spielerliste) {
            Spieler derSpieler = spielerService.neuerSpielerAnlegen(wert);
            spielerListe.add(derSpieler);
        }
        spiel.setSpielerDesSpieles(spielerListe);
        spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(0));
        //TODO Set Ziehstapel aufraeumen
        spiel.setZiehstapelkarten(kartenService.mischenKartenstapel(kartenService.anlegenKartenstapel(), false));
        spiel.setZiehstapelkarten(austeilenVonKarten(spiel.getZiehstapelkarten(), spiel.getSpielerDesSpieles(), anzahlStartkartenbestimmen(spiel.getSpielerDesSpieles(), spiel.getZiehstapelkarten())));
        ablagestapel.add(spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size() - 1));
        spiel.setZiehstapelkarten(entferneGezogendeKarteVomZiehstapel(spiel.getZiehstapelkarten(), spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size() - 1)));
        spiel.setAblagestapelkarten(ablagestapel);
        return spiel;
    }

    @Override
    public Spiel ziehenKarteVomZiehstapel(Spiel spiel) {
        log.debug("ziehenKarteVomZiehstapel");
        Karte karte = spiel.getZiehstapelkarten().get(spiel.getZiehstapelkarten().size() - 1);
        spielerService.karteZuHandblatthinzufuegen(karte, spiel.getAktiverSpieler());
        spiel.setZiehstapelkarten(entferneGezogendeKarteVomZiehstapel(spiel.getZiehstapelkarten(), karte));
        return spiel;
    }

    @Override
    public Spiel legeKarte(Karte zulegendeKarte, Spieler spieler, Spiel spiel) {
        log.debug("legeKarte");
        if (regeln.darfKartegelegtwerden(spiel.getAblagestapelkarten().get(spiel.getAblagestapelkarten().size() - 1), zulegendeKarte, spiel.getFarbe())) {
            spiel.setAblagestapelkarten(legenKarteAufAblageStapel(spieler, spiel.getAblagestapelkarten(), zulegendeKarte));
            spiel.setSummeZuziehendeKarten(regeln.mussZweiKartenZiehen(zulegendeKarte, spiel.getSummeZuziehendeKarten()));
            if(regeln.richtungWechsel(zulegendeKarte)){
                spiel.setIstSpielrichtungRechts(!spiel.isIstSpielrichtungRechts());
            }
            spiel.setErfolgreichgelegt(true);
            if (regeln.mussSichFarbeWuenschen(zulegendeKarte)) {
                spiel.setMussFarbeWuenschen(true);
            }
            return spiel;
        }
        return spiel;

    }

    @Override
    public Spiel farbeGewaehlt(Spiel spiel, Farbe farbe) {
        log.debug("farbeGewaehlt");
        spiel.setFarbe(farbe);
        spiel.setMussFarbeWuenschen(false);
        return spiel;
    }

    @Override
    public Spiel naechsterSpieler(Spiel spiel) {
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
            spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(indexNaechsterSpieler - laengeSpielerliste));
        } else {
            if (indexNaechsterSpieler < 0) {
                spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(laengeSpielerliste + indexNaechsterSpieler));
            } else {
                spiel.setAktiverSpieler(spiel.getSpielerDesSpieles().get(indexNaechsterSpieler));
            }
        }
        return spiel;
    }

    private List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte) {
        log.debug("entferneGezogendeKarteVomZiehstapel");
        karteStapel.remove(karte);
        return karteStapel;
    }

    private List<Karte> austeilenVonKarten(List<Karte> ziehstapel, List<Spieler> spielerListe, int durchgaenge) {
        log.debug("austeilenVonKarten");
        for (int runden = 0; runden < durchgaenge; runden++) {
            for (int spielerzaehler = 0; spielerzaehler < spielerListe.size(); spielerzaehler++) {
                Karte karte = ziehstapel.get(ziehstapel.size() - 1);
                spielerService.karteZuHandblatthinzufuegen(karte, spielerListe.get(spielerzaehler));
                ziehstapel.remove(karte);
            }
        }
        return ziehstapel;
    }

    @Override
    public List<Karte> karteZiehen(int anzahl, List<Karte> karteStapel, Spieler spieler) {
        log.debug("karteZiehen");
        List<Spieler> spielerliste = new ArrayList<>();
        spielerliste.add(spieler);
        austeilenVonKarten(karteStapel, spielerliste, anzahl);
        return karteStapel;
    }

    private List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {
        log.debug("legenKarteAufAblageStapel");
        spielerService.karteausHandblattentfernden(karte, spieler);
        kartenAblagestapel.add(karte);
        return kartenAblagestapel;
    }

    @Override
    public boolean ermittleSpielende(Spieler spieler) {
        log.debug("ermittleSpielende");
        return !spieler.getHandkarten().isEmpty();
    }

    @Override
    public Spiel pruefeAufMau(Spiel spiel) {
        log.debug("pruefeAufMau");
        List<Spieler> spielerAlsListe = new ArrayList<>();
        spielerAlsListe.add(spiel.getAktiverSpieler());
        if (istMauNoetig(spiel.getAktiverSpieler())) {
            if (!spiel.getAktiverSpieler().isMauistgesetzt()) {
                austeilenVonKarten(spiel.getZiehstapelkarten(), spielerAlsListe, 2);
            }
        }
        return spiel;
    }

    /**
     * Schaut ob Mau von noeten ist
     *
     * @param spieler die Aktuellen Spieler
     * @return Ob ja oder nein
     */
    private boolean istMauNoetig(Spieler spieler) {
        log.debug("istMauNoetig");
        return spieler.getHandkarten().size() == 1;
    }

    @Override
    public void setzeMau(Spieler spieler, boolean neuerZustand) {
        log.debug("setzeMau");
        spieler.setMauistgesetzt(neuerZustand);
    }

    /**
     * Bestimmt die Anzahl der Karten die jeder Spieler bekommt
     *
     * @param spielerListe Liste der Spieler
     * @param ziehstapel   Der Stapel der ausgeteilt wird
     * @return Gibt die Anzahl der Karten zurueck
     */
    private int anzahlStartkartenbestimmen(List<Spieler> spielerListe, List<Karte> ziehstapel) {
        log.debug("anzahlStartkartenbestimmen");
        int anzahlkarten;
        anzahlkarten = (int) Math.floor((ziehstapel.size() - 10) / spielerListe.size());
        if (anzahlkarten > 6) {
            anzahlkarten = 6;
        }
        return anzahlkarten;
    }

    /*TODO
    Sollten alle Karten auf den Handkarten der Spieler sein, kommt ers zu einer Exception und dem Abbruch des Spieles,
    da die Wahrscheinlichkeit sehr gering ist, das so gespielt wird, wird dieses Mal die Exception noch nicht gefangen und bearbeitet,
    dies erfolgt bei der nächsten Abgabe
    */
    @Override
    public Spiel mussGemischtWerden(Spiel spiel) {
        log.debug("mussGemischtWerden");
        List<Karte> ziehstapel;
        List<Karte> ablagestapel = new ArrayList<>();

        ziehstapel = spiel.getZiehstapelkarten();

        if (ziehstapel.size() == 0) {
            ziehstapel = spiel.getAblagestapelkarten();
            ablagestapel.add(ziehstapel.get(ziehstapel.size() - 1));
            ziehstapel.remove(ziehstapel.size() - 1);
            spiel.setZiehstapelkarten(kartenService.mischenKartenstapel(ziehstapel, false));
            spiel.setAblagestapelkarten(ablagestapel);
        }
        return spiel;
    }
}
