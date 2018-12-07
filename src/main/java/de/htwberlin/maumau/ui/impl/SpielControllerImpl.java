package de.htwberlin.maumau.ui.impl;


import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.ui.export.SpielController;

import java.util.ArrayList;
import java.util.List;

public class SpielControllerImpl implements SpielController {

    private SpielServiceImpl spielService = new SpielServiceImpl();
    private SpielViewer view = new SpielViewer();
    private List<List> spielerliste = new ArrayList();
    private Spiel dasSpiel = new Spiel();
    private boolean spielLaeuft = true;
    private int spielrundenindex = 0;


    public void run(){

        if(view.welcheSpielart()==1){
            do {
                if(view.sollSpielerMenschSein()==true){
                    //vorhandener Spieler
                    spielerliste.add(view.spielerHinzufuegen());
                }else{
                    System.out.println("KI Spieler hinzufügen, diese Funktion ist bisher nicht implementiert");
                }
            }while(view.sollSpielerHinzugefuegtWerden()==true);
            dasSpiel=spielService.anlegenSpiel(spielerliste);
            System.out.println("Danke fürs einrichten des Spieles");

            while(spielLaeuft){
                if(spielrundenindex >=0 ){
                    spielService.naechsterSpieler(dasSpiel);
                }
                spielService.zuZiehendeKarte(dasSpiel.getSummeZuziehendeKarten(), dasSpiel.getZiehstapelkarten(), dasSpiel.getAktiverSpieler());
                int kartennummer = 0;
                boolean musslegen = true;
                while(musslegen) {
                    System.out.println("Deine Handkarten sind: XXX , gib die Nummer der Karte ein, die du ablegen willst");
                    spielService.legeKarte(dasSpiel.getAktiverSpieler().getHandkarten().get(kartennummer), dasSpiel.getAktiverSpieler(), dasSpiel);
                    musslegen=!dasSpiel.isErfolgreichgelegt();
                    //spieler kann nicht legen und muss ziehen
                }
                spielLaeuft =  spielService.ermittleSpielende(dasSpiel.getAktiverSpieler());
                if(spielLaeuft==false){
                    System.out.println("Gewonnen hat " + dasSpiel.getAktiverSpieler().getName());
                }
                spielrundenindex++;
            }
        }else{
            System.out.println("Danke, dass du ein Spiel fortsetzen möchtest, diese Funktion gibt es noch nicht");
            System.out.println("Bitte komme später mal wieder");
        }


    }

//    private List<String> spielerhinzufuegen(){
//        List<String> spieler = new ArrayList<>();
//        System.out.println("Name des Spielers");
//        String name = "Name";
//        System.out.println("Emailadresse des Spielers");
//        String email = "email";
//        spieler.add(name);
//        spieler.add(email);
//        return spieler;
//    }

}
