/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 */

package de.htwberlin.maumau.spielverwaltung.impl;


import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.kartenverwaltung.export.KartenService;
import de.htwberlin.maumau.regelnverwaltung.export.RegelnService;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;
import de.htwberlin.maumau.spielerverwaltung.export.SpielerService;
import de.htwberlin.maumau.spielverwaltung.entity.Spiel;
import de.htwberlin.maumau.spielverwaltung.export.SpielService;
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
        regeln=regelwerkHinzufuegen(erweiterteRegeln);

        List<Spieler> spielerListe = new ArrayList<>();
        List<Karte> ablagestapel = new ArrayList<>();

        spiel.setErweiterteRegeln(erweiterteRegeln);
        for (String wert : spielerliste) {
            Spieler derSpieler =null;
            String kiPruefung;
            kiPruefung=wert.replaceAll("\\d", "");
            derSpieler = spielerService.neuerSpielerAnlegen(wert);
            if(kiPruefung.equals("Computer")){
                derSpieler.setKi(true);
            }
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

    public RegelnService regelwerkHinzufuegen(boolean erweiterteRegeln) {
        if (erweiterteRegeln) {
            regeln = regelnErweitert;
        } else {
            regeln = regelnEinfach;
        }
        return regeln;
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
            spiel.setAussetzen(regeln.mussRundeAussetzen(zulegendeKarte));
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
        if (spiel.isAussetzen()) {
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

    @Override
    public List<Karte> karteZiehen(int anzahl, List<Karte> karteStapel, Spieler spieler) {
        log.debug("karteZiehen");
        List<Spieler> spielerliste = new ArrayList<>();
        spielerliste.add(spieler);
        austeilenVonKarten(karteStapel, spielerliste, anzahl);
        return karteStapel;
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

    @Override
    public void setzeMau(Spieler spieler, boolean neuerZustand) {
        log.debug("setzeMau");
        spieler.setMauistgesetzt(neuerZustand);
    }

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

    /**
     * Diese Methode entfernt eine bestimmte Karte vom Kartenstapel
     *
     * @param karteStapel
     * @param karte
     * @return
     */
    private List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte) {
        log.debug("entferneGezogendeKarteVomZiehstapel");
        karteStapel.remove(karte);
        return karteStapel;
    }

    /**
     * Verteilt karten auf Spielerhaende
     *
     * @param ziehstapel - der Stapel von dem die Karten genommen werden sollen
     * @param spielerListe - Liste der Spieler die die Karten bekommen sollen
     * @param durchgaenge - Anzahl der neuen Karten pro Spieler
     * @return - der reduzierte ziehstapel
     */
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

    /**
     * Entfernt eine Karte von Spielerhand und legt sie auf den Ablagestapel
     *
     * @param spieler - bei dem die Karte entfernt werden soll
     * @param kartenAblagestapel - Ablagestapel auf den die Karte gelegt werden soll
     * @param karte - besagte Karte
     * @return - der veraenderte Ablagestapel
     */
    private List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {
        log.debug("legenKarteAufAblageStapel");
        spielerService.karteausHandblattentfernden(karte, spieler);
        kartenAblagestapel.add(karte);
        return kartenAblagestapel;
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

    /**
     * Bestimmt die Anzahl der Karten die jeder Spieler bekommt
     *
     * @param spielerListe - Liste der Spieler
     * @param ziehstapel - Der Stapel der ausgeteilt wird
     * @return - Gibt die Anzahl der Karten zurueck
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
}
