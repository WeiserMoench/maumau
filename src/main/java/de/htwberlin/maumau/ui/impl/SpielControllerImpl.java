/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 *
 */

package de.htwberlin.maumau.ui.impl;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.spielverwaltung.entity.Spiel;
import de.htwberlin.maumau.spielverwaltung.export.SpielService;
import de.htwberlin.maumau.ui.export.SpielController;
import de.htwberlin.maumau.virtuellerspielerverwaltung.export.KiService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SpielControllerImpl implements SpielController {
    public SpielControllerImpl(SpielService spielService, KiService kiService) {
        this.spielService = spielService;
        this.kiService = kiService;
    }

    //view waere cooler
    private KiService kiService;
    private SpielService spielService;
    private SpielViewer view = new SpielViewer();
    private List<String> spielerliste;
    private Spiel dasSpiel;
    private boolean spielLaeuft;
    private int spielrundenindex;
    private boolean erweiterteRegeln;
    private Scanner sc = new Scanner(System.in);
    private static Logger log = Logger.getRootLogger();


    public void run(){
        log.debug("run");
        do {
            dasSpiel = new Spiel();
            spielLaeuft = true;
            spielerliste = new ArrayList();
            spielrundenindex = 0;
            view.willkommen();
            //        if(welcheSpielart()==1){ //vorbereitung persistenz
            erweiterteRegeln = erweiterteRegeln();

            spielerliste=kiSpielerAnlegen(spielerliste);

            do {
                    spielerliste.add(spielerHinzufuegen());
            } while (weitererSpieler() == true);
            dasSpiel = spielService.anlegenSpiel(spielerliste, erweiterteRegeln);
            while (spielLaeuft) {
                if (spielrundenindex > 0) {
                    spielService.naechsterSpieler(dasSpiel);
                    dasSpiel.setAussetzen(false);
                }
                //jpa laden objectdb???
                spielService.karteZiehen(dasSpiel.getSummeZuziehendeKarten(), dasSpiel.getZiehstapelkarten(), dasSpiel.getAktiverSpieler());

                if (!dasSpiel.getAktiverSpieler().isKi()) { //menschlicher Spieler
                    dasSpiel = menschlicherSpielerSpielt(dasSpiel);
                } else {//KI Spieler
                    log.debug("KI Spieler am Zug");
                    dasSpiel = kiSpielt(dasSpiel);

                }
                spielLaeuft = spielService.ermittleSpielende(dasSpiel.getAktiverSpieler());
                if (!spielLaeuft) {
                    view.siegerAusgabe(dasSpiel.getAktiverSpieler().getName());
                }
                spielrundenindex++;
                //jpa speichern
            }
            //        }else{ //vorbereitung persistenz
            //            System.out.println("Danke, dass du ein Spiel fortsetzen möchtest, diese Funktion gibt es noch nicht");
            //            System.out.println("Bitte komme später wieder");
            //        }
        }
        while(weitereRunde());
        view.spielende();
    }

    private List<String> kiSpielerAnlegen(List<String> spielerliste) {
        int anzahl = 0;
        int minimaleZahl = 0;
        int maximaleZahl = 5;

        view.anzahlKI();

        anzahl=zahlEingabe(minimaleZahl, maximaleZahl);

        for(int i = 0; i<anzahl;i++){
            spielerliste.add(kiService.kiAnlegen(i));
        }
        return spielerliste;
    }

    private Spiel kiSpielt(Spiel dasSpiel) {
        log.debug("kiSpielt");
        int durchgangszaehler = 0;
        boolean erneutesFragen;

        anzeigeObersteKarte(dasSpiel);

        dasSpiel.setSummeZuziehendeKarten(0);


        do{
            log.debug("do schleife karte legen");

            if(durchgangszaehler>dasSpiel.getAktiverSpieler().getHandkarten().size()-1){
                log.debug("Ki muss Karte ziehen, da legen nicht möglich");
                try{
                    dasSpiel=spielService.ziehenKarteVomZiehstapel(dasSpiel);
                    view.pchatgezogen(dasSpiel.getAktiverSpieler().getName());
                } catch (IndexOutOfBoundsException e){
                    view.spielerBetruegen();
                }
                erneutesFragen=false;
            }else{
                spielService.legeKarte(dasSpiel.getAktiverSpieler().getHandkarten().get(durchgangszaehler), dasSpiel.getAktiverSpieler(), dasSpiel);
                erneutesFragen=!dasSpiel.isErfolgreichgelegt();
                durchgangszaehler++;
            }
        }while(erneutesFragen);

        if(dasSpiel.isMussFarbeWuenschen()){
            log.debug("if schleife Farbe wünschen");
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

            dasSpiel.setFarbe(neueFarbe);
            dasSpiel.setMussFarbeWuenschen(false);
        }

        dasSpiel.getAktiverSpieler().setMauistgesetzt(kiService.mauSetzen(dasSpiel.getAktiverSpieler()));

        view.kiHatGespielt(dasSpiel.getAktiverSpieler().getName());
        if(dasSpiel.getAktiverSpieler().isMauistgesetzt()){
            view.kiSagteMau();
        }


        anzeigeObersteKarte(dasSpiel);
        view.leereZeileMitStichen();

        return dasSpiel;
    }

    private Spiel menschlicherSpielerSpielt(Spiel dasSpiel) {
        dasSpiel=spielerInfos(dasSpiel);
        dasSpiel=kartelegen(dasSpiel);
        dasSpiel=mauPruefung(dasSpiel);
        spielService.setzeMau(dasSpiel.getAktiverSpieler(),false);
        spielService.mussGemischtWerden(dasSpiel); // Wenn die Spieler betrügen, kann es zu einer Exception kommen, diese wird bei der nächsten Abgabe gefangen
        return dasSpiel;
    }

    /**
     * Diese Methode fragt ob ein weiterer Spieler gewuenscht ist
     *
     * @return boolean - der angibt, ob es einen weiteren Spieler geben soll
     */
    private boolean weitererSpieler(){
        log.debug("weitererSpieler");
        if(spielerliste.size()<2){
            view.weitererSpielerNoetig();
            return true;
        }else{
            return sollSpielerHinzugefuegtWerden();
        }
    }

    /**
     * Methode prueft ob es der Spieler Mau gesagt hat, oder ob er Strafkarten ziehen muss und sorgt
     * falls nicht dafuer, dass Strafkarten gezogen werden und fuer eine Info
     *
     * @param spiel - Das Spiel mit dem enthaltenen Spieler, der geprueft werden soll
     * @return - Das Spiel mit dem ggfs geaenderten Spieler
     */
    private Spiel mauPruefung(Spiel spiel) {
        log.debug("mauPruefung");
        int anzahlHandkartenVorPruefung;
        int anzahlHandkartenNachPruefung;

        anzahlHandkartenVorPruefung=spiel.getAktiverSpieler().getHandkarten().size();
        spiel = spielService.pruefeAufMau(spiel);
        anzahlHandkartenNachPruefung=spiel.getAktiverSpieler().getHandkarten().size();

        if (anzahlHandkartenNachPruefung>anzahlHandkartenVorPruefung){
            view.strafkartenVergessenesMau();
        }
        return spiel;
    }

    /**
     * Gibt alle Infos aus, die der Spieler zu Beginn seiner Runde braucht
     * Ausserdem werden dem Spieler ggfs Karten auf die Hand gegeben, falls er ziehen muss
     * Und der Zahler im Spiel, wie viele Karten gezogen werden muessen wird resettet
     * Außerdem werden Infos ueber die anderen Spieler angezeigt
     *
     * @param spiel - Aus dem die Infos entnommen werden sollen
     * @return - Das angepasste Spiel
     */
    private Spiel spielerInfos(Spiel spiel) {
        log.debug("spielerInfos");

        String spielername;

        int anzahlGezogenerKarten;


        spielername = spiel.getAktiverSpieler().getName();
        anzahlGezogenerKarten = spiel.getSummeZuziehendeKarten();



        view.infosfuerNaechstenSpieler(spielername, anzahlGezogenerKarten);


        anzeigeObersteKarte(dasSpiel);


        for (int i = 0; i < spiel.getSpielerDesSpieles().size(); i++) {
            if(!spiel.getSpielerDesSpieles().get(i).equals(spiel.getAktiverSpieler())){
                String mitspielername = spiel.getSpielerDesSpieles().get(i).getName();
                int mitspielerhandkarten = spiel.getSpielerDesSpieles().get(i).getHandkarten().size();
                view.infosUeberAndereSpieler(mitspielername, mitspielerhandkarten);
            }

        }
        dasSpiel.setSummeZuziehendeKarten(0);

        return spiel;
    }

    private void anzeigeObersteKarte(Spiel dasSpiel) {
        Farbe farbeNachBube;
        Farbe obersteKarteAblagestapelFarbe;
        String obersteKarteAblagestapelWert;

        obersteKarteAblagestapelFarbe = dasSpiel.getAblagestapelkarten().get(dasSpiel.getAblagestapelkarten().size()-1).getFarbe();
        obersteKarteAblagestapelWert = dasSpiel.getAblagestapelkarten().get(dasSpiel.getAblagestapelkarten().size()-1).getWert();
        farbeNachBube = dasSpiel.getFarbe();

        if(obersteKarteAblagestapelWert.equals("Bube")){
            if(dasSpiel.getFarbe()!=null) {
                view.spielerInfoNachBube(farbeNachBube);
            }
        }

        view.ablagestapelZeigt(obersteKarteAblagestapelFarbe, obersteKarteAblagestapelWert);
    }

    /**
     * Diese Methode kuemmert sich um das legen einer Karte. Dafuer werden dem Spieler erst einmal alle benoetigten
     * Informationen zu seinen Moeglichkleiten angezeigt
     * Der Spieler muss eine Entscheidung treffen und die Karte wird gespielt
     *
     * @param spiel - das zu aendernde Spiel
     * @return - das Spiel in neuer Form
     */
    private Spiel kartelegen(Spiel spiel){
        log.debug("kartelegen");


        view.welcheKarteAblegen();
        for (int kartennummer = 0; kartennummer<spiel.getAktiverSpieler().getHandkarten().size();kartennummer++){
            Farbe farbe = spiel.getAktiverSpieler().getHandkarten().get(kartennummer).getFarbe();
            String wert = spiel.getAktiverSpieler().getHandkarten().get(kartennummer).getWert();
            view.ausgabeKarte(kartennummer,farbe,wert);
        }

        spiel=eingabeZumKartelegen(spiel);

        if(spiel.isMussFarbeWuenschen()){
            spiel=farbeWaehlen(spiel);
        }

        return spiel;
    }

    private Spiel eingabeZumKartelegen(Spiel spiel) {
        String antwort;
        boolean erneutesFragen;
        int antwortAlsZahl;

        do{
            antwort=sc.next();
            antwort=antwort.toLowerCase();
            if(antwort.equals("m")){
                view.maugesagt();
                spielService.setzeMau(spiel.getAktiverSpieler(), true);
                erneutesFragen=true;
            }else if(antwort.equals("z")){
                try {
                    spiel = spielService.ziehenKarteVomZiehstapel(spiel);
                } catch(IndexOutOfBoundsException e) {
                    view.spielerBetruegen();
                }
                erneutesFragen=false;
            }else{
                try{
                    antwortAlsZahl = Integer.parseInt(antwort);
                    if(antwortAlsZahl>=0 || antwortAlsZahl<spiel.getAktiverSpieler().getHandkarten().size()){
                        spielService.legeKarte(spiel.getAktiverSpieler().getHandkarten().get(antwortAlsZahl), spiel.getAktiverSpieler(), spiel);
                        erneutesFragen=!spiel.isErfolgreichgelegt();
                        if(erneutesFragen){
                            view.falscheKarte();
                            }
                    }else{
                        erneutesFragen=true;
                        view.kartennummerUnsinnig();
                    }
                }catch (java.lang.NumberFormatException e){
                    view.kartennummerUnsinnig();
                    erneutesFragen=true;
                }
            }
        }while (erneutesFragen);

        return spiel;
    }

    /**
     * Methode ist da, damit der Spieler nach einem legen eines Buben aufgefordert wird eine Farbe zu waehlen.
     *
     * @param spiel - Das veraendert werden soll
     * @return - dasSpiel, was uebergeben wurde, nachdem es veraendert wurde
     */
    private Spiel farbeWaehlen(Spiel spiel) {
        log.debug("farbewaehlen");
        int antwort;
        Farbe farbe = null;

            view.farbeWaehlen();

            antwort=zahlEingabe(1, 4);
            switch (antwort){
                case 1:   farbe=Farbe.HERZ;
                            break;
                case 2:   farbe=Farbe.KREUZ;
                            break;
                case 3:   farbe=Farbe.KARO;
                            break;
                case 4:   farbe=Farbe.PIK;
                            break;
            }

        spielService.farbeGewaehlt(spiel, farbe);

        return spiel;
    }

    /**
     * Diese Methode fragt ab, ob ein neues Spiel gestartet werden soll oder ein vorheriges fortgesetzt
     *
     * @return - 1 fuer neues Spiel, 2 fuer fortsetzen
     */
    private Integer welcheSpielart(){
        log.debug("welcheSpielart");
        int spielart=0;
        view.willkommen();

        while(spielart==0){
            String eingabe = sc.next();
            if(eingabe.equals("1")){
                spielart=1;
            }else if (eingabe.equals("2")){
                spielart=2;
            }else{
                view.fehlerhafteEingabeEinsZwei();
            }
        }
        return spielart;
    }

    /**
     * Diese Methode fragt ab, ob ein weiterer Spieler hinzugefuegt werden soll
     *
     * @return boolean, ob weiterer Spieler erwuenscht ist
     */
    private boolean sollSpielerHinzugefuegtWerden() {
        log.debug("sollSpielerHinzugefuegtWerden");
        view.sollSpielerHinzugefuegtWerden();
        return jaNeinAbfrage();
    }

    /**
     * Diese Methode liest die Konsoleneingabe und prueft, ob mit ja oder nein geantwortet wurde,
     * sofern dies nicht der Fall ist, wird ein Fehler ausgegeben und der Benutzer wird aufgefordert mit
     * ja oder nein zu antworten
     *
     * @return - boolean: true fuer ja, false fuer nein
     */
    private boolean jaNeinAbfrage(){
        log.debug("jaNeinAbfrage");
        boolean weitererDurchgang=true;
        boolean rueckgabe=false;

        while(weitererDurchgang){
            String antwort = sc.next();
            antwort=antwort.toLowerCase();
            if(antwort.equals("ja")){
                rueckgabe = true;
                weitererDurchgang=false;
            }else if(antwort.equals("nein")){
                rueckgabe = false;
                weitererDurchgang=false;
            }else{
                view.jaNeinAbfrageFehlermeldung();
            }
        }
        return rueckgabe;
    }

    /**
     * Diese Methode fragt ab, ob der neue Spieler ein Mensch sein soll, oder sonst ein KI
     *
     * @return - boolean, der angibt ob der neuste Spieler ein Mensch sein soll
     */
    private boolean sollSpielerMenschSein() {
        log.debug("sollSpielerMenschSein");
        view.sollSpielerMenschSein();

        return jaNeinAbfrage();
    }

    /**
     * Methode fragt den Namen des hinzuzufuegenden Spielers ab
     * und speichert diese Information als String
     *
     * @return - String mit dem Name des Spielers
     */
    private String spielerHinzufuegen(){
        log.debug("spielerHinzufuegen");
        view.spielerNamenAnfragen();
        String name = sc.next();
        return name;
    }

    /**
     * Diese Methode fragt erst ab, ob die Spieler die Regeln lesen wollen und im Anschluss
     * nach welchen Regeln gespielt werden soll.
     *
     * @return - boolean, der angibt ob die erweiterten Regeln gewuenscht sind
     */
    private boolean erweiterteRegeln(){
        log.debug("erweiterteRegeln");
        boolean antwort;
        view.sollenRegelnAngezeigtWerden();
        antwort=jaNeinAbfrage();
        if(antwort==true){
            view.anzeigenRegeln();
        }
        view.sollNachErweitertenRegelnGespieltWerden();
        antwort=jaNeinAbfrage();
        return antwort;
    }

    private boolean weitereRunde() {
        view.weitereSpielStarten();
        return jaNeinAbfrage();
    }

    private int zahlEingabe(int mininaleZahl, int maximaleZahl){
        int eingeleseneZahl = 0;
        boolean fehler;
        String eingabe;
        do {
                eingabe = sc.next();
            try {
                eingeleseneZahl = Integer.parseInt(eingabe);
                fehler = false;
            }catch(java.lang.NumberFormatException e){
                view.eingabeZahlFehlerhaft(mininaleZahl, maximaleZahl);
                fehler=true;
            }
            if(eingeleseneZahl<mininaleZahl || eingeleseneZahl>maximaleZahl){
                view.eingabeZahlFehlerhaft(mininaleZahl, maximaleZahl);
                fehler = true;
            }
//            if(eingeleseneZahl>maximaleZahl){
//                view.eingabeZahlFehlerhaft(mininaleZahl, maximaleZahl);
//                fehler = true;
//            }
        }  while(fehler);

        return eingeleseneZahl;
    }

//                    try{
//        antwortAlsZahl = Integer.parseInt(antwort);
//        if(antwortAlsZahl>=0){
//            if(antwortAlsZahl<spiel.getAktiverSpieler().getHandkarten().size()){
//                spielService.legeKarte(spiel.getAktiverSpieler().getHandkarten().get(antwortAlsZahl), spiel.getAktiverSpieler(), spiel);
//                erneutesFragen=!spiel.isErfolgreichgelegt();
//                if(erneutesFragen){
//                    view.falscheKarte();
//                }else if(spiel.isMussFarbeWuenschen()){
//                    spiel=farbeWaehlen(spiel);
//                }
//            }else{
//                erneutesFragen=true;
//                view.kartennummerUnsinnig();
//            }
//        }else{
//
//            erneutesFragen=true;
//            view.kartennummerUnsinnig();
//        }
//    }catch (java.lang.NumberFormatException e){
//        view.kartennummerUnsinnig();
//        erneutesFragen=true;
//    }

}
