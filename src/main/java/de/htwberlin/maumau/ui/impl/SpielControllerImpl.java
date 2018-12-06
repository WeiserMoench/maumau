package de.htwberlin.maumau.ui.impl;


import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.ui.export.SpielController;

import java.util.ArrayList;
import java.util.List;

public class SpielControllerImpl implements SpielController {

    SpielServiceImpl spielService = new SpielServiceImpl();
    SpielViewer view = new SpielViewer();
    List<List> spielerliste = new ArrayList();
    Spiel dasSpiel = new Spiel();
    boolean spielLaeuft = true;
    int spielrundenindex = 0;


    public void run(){

        System.out.println("Weiteren Spieler hinzufügen?");
        //solange wie ja
            spielerliste.add(spielerhinzufuegen());
        //sonst
        spielService.anlegenSpiel(spielerliste);

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

    }

    private List<String> spielerhinzufuegen(){
        List<String> spieler = new ArrayList<>();
        System.out.println("Name des Spielers");
        String name = "Name";
        System.out.println("Emailadresse des Spielers");
        String email = "email";
        spieler.add(name);
        spieler.add(email);
        return spieler;
    }

}
