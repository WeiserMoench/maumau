package de.htwberlin.maumau.ui.impl;


import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.ui.export.SpielController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpielControllerImpl implements SpielController {

    private SpielServiceImpl spielService = new SpielServiceImpl();
    private SpielViewer view = new SpielViewer();
    private List<List> spielerliste = new ArrayList();
    private Spiel dasSpiel = new Spiel();
    private boolean spielLaeuft = true;
    private int spielrundenindex = 0;
    private boolean erweiterteRegeln;
    private boolean sagteMau = false;
    private Scanner sc = new Scanner(System.in);

    public boolean isSagteMau() {
        return sagteMau;
    }

    public void setSagteMau(boolean sagteMau) {
        this.sagteMau = sagteMau;
    }




    public void run(){

        if(welcheSpielart()==1){
            erweiterteRegeln=erweiterteRegeln();
            System.out.println(erweiterteRegeln);
            do {
                if(sollSpielerMenschSein()==true){
                    //vorhandener Spieler
                    spielerliste.add(spielerHinzufuegen());
                }else{
                    System.out.println("KI Spieler hinzufügen, diese Funktion ist bisher nicht implementiert");
                }
            }while(sollSpielerHinzugefuegtWerden()==true);
            dasSpiel=spielService.anlegenSpiel(spielerliste);
            while(spielLaeuft){
                if(spielrundenindex >0 ){
                    spielService.naechsterSpieler(dasSpiel);
                }
                spielService.zuZiehendeKarte(dasSpiel.getSummeZuziehendeKarten(), dasSpiel.getZiehstapelkarten(), dasSpiel.getAktiverSpieler());
                int kartennummer = 0;
                boolean musslegen = true;
                spielerInfos();
                while(musslegen) {
                    kartennummer=welcheKarteSollGelegtWerden(dasSpiel.getAktiverSpieler().getHandkarten());
                    System.out.println(kartennummer);
                    spielService.legeKarte(dasSpiel.getAktiverSpieler().getHandkarten().get(kartennummer), dasSpiel.getAktiverSpieler(), dasSpiel);
                    musslegen=!dasSpiel.isErfolgreichgelegt();
                    //spieler kann nicht legen und muss ziehen
                }
                spielLaeuft = spielService.ermittleSpielende(dasSpiel.getAktiverSpieler());
                if(spielLaeuft==false){
                    System.out.println("Gewonnen hat " + dasSpiel.getAktiverSpieler().getName());
                }
                spielrundenindex++;
                sagteMau=false;
            }
        }else{
            System.out.println("Danke, dass du ein Spiel fortsetzen möchtest, diese Funktion gibt es noch nicht");
            System.out.println("Bitte komme später wieder");
        }


    }

    private void spielerInfos() {
        Farbe obersteKarteAblagestapelFarbe;
        String obersteKarteAblagestapelWert;
        String spielername;

        obersteKarteAblagestapelFarbe = dasSpiel.getAblagestapelkarten().get(dasSpiel.getAblagestapelkarten().size()-1).getFarbe();
        obersteKarteAblagestapelWert = dasSpiel.getAblagestapelkarten().get(dasSpiel.getAblagestapelkarten().size()-1).getWert();

        spielername = dasSpiel.getAktiverSpieler().getName();

        view.infosfuerNaechstenSpieler(obersteKarteAblagestapelFarbe, obersteKarteAblagestapelWert,spielername);
    }


    private int welcheKarteSollGelegtWerden(List<Karte> handkarten){
        String antwort;
        boolean erneutesFragen=false;
        int antwortAlsZahl;
        int gewuenschteKarte = 0;

        view.welcheKarteAblegen();
        for (int kartennummer = 0; kartennummer<handkarten.size();kartennummer++){
            Farbe farbe = handkarten.get(kartennummer).getFarbe();
            String wert = handkarten.get(kartennummer).getWert();
            view.ausgabeKarte(kartennummer,farbe,wert);
        }
        do{
            antwort=sc.next();
            antwort=antwort.toLowerCase();
            if(antwort=="mau"){
                view.maugesagt();
                setSagteMau(true);
                erneutesFragen=true;
            }else {
                try{
                    antwortAlsZahl = Integer.parseInt(antwort);
                    if(antwortAlsZahl>=0){
                        if(antwortAlsZahl<handkarten.size()){
                            gewuenschteKarte=antwortAlsZahl;
                        }else{
                            erneutesFragen=true;
                            view.kartennummerUnsinnig();
                        }
                    }else{
                        erneutesFragen=true;
                        view.kartennummerUnsinnig();
                    }
                }catch (Exception e){
                    view.kartennummerUnsinnig();
                    erneutesFragen=true;
                }
            }
        }while (erneutesFragen);

        return gewuenschteKarte;
    }

    /**
     * Diese Methode fragt ab, ob ein neues Spiel gestartet werden soll oder ein vorheriges fortgesetzt
     *
     * @return - 1 fuer neues Spiel, 2 fuer fortsetzen
     */
    private Integer welcheSpielart(){
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
        view.sollSpielerHinzugefuegtWerden();
        return jaNeinAbfrage();
    }

    /**
     * Diese Methode liest die Konsoleneingabe und prueft, ob mit ja oder nein geantwortet wurde,
     * sofern dies nicht der Fall ist, wird ein Fehler ausgegeben und der Benutzer wird aufgefordert mit
     * Ja oder nein zu antworten
     *
     * @return - boolean: true fuer ja, false fuer nein
     */
    private boolean jaNeinAbfrage(){
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
        view.sollSpielerMenschSein();

        return jaNeinAbfrage();
    }

    /**
     * Methode fragt den Namen und die E-Mailadresse des hinzuzufuegenden Spielers ab
     * und speichert diese Informationen in einer Liste
     *
     * @return - Liste aus zwei Strings, Name des Spielers - Emailadresse des Spielers
     */
    private List<String> spielerHinzufuegen(){
        List<String> spieler = new ArrayList<>();
        view.spielerNamenAnfragen();
        String name = sc.next();
        view.spielerEMailAnfragen();
        String email = sc.next();
        spieler.add(name);
        spieler.add(email);
        return spieler;
    }

    /**
     * Diese Methode fragt erst ab, ob die Spieler die Regeln lesen wollen und im Anschluss
     * nach welchen Regeln gespielt werden soll.
     *
     * @return - boolean, der angibt ob die erweiterten Regeln gewünscht sind
     */
    private boolean erweiterteRegeln(){
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



}
