package de.htwberlin.maumau.ui.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpielViewer {
    private Scanner sc = new Scanner(System.in);

    public Integer welcheSpielart(){
        int spielart=0;
        System.out.println("Willkommen beim MauMau Spiel");
        System.out.println("Wenn du ein neues Spiel beginnen willst, gibt bitte die 1 ein");
        System.out.println("Wenn du ein Spiel fortsetzen möchtest, wähle die 2 (noch ohne Funktion)");
        System.out.println("Welche Variante möchtest du spielen?");

        while(spielart==0){
            String eingabe = sc.next();
            if(eingabe.equals("1")){
                spielart=1;
            }else if (eingabe.equals("2")){
                spielart=2;
            }else{
                System.out.println("Deine Eingabe war falsch, bitte gib eine 1 oder eine 2 ein");
            }
        }
        return spielart;
    }

    public boolean sollSpielerHinzugefuegtWerden() {
        System.out.println();
        System.out.println("Möchtest du einen weiteren Spieler zum Spiel hinzufügen");

        return jaNeinAbfrage();
    }

    public boolean spielerSollMenschSein() {
        System.out.println();
        System.out.println("Wenn der Spieler ein Mensch sein soll, gib bitte \"ja\" ein.");
        System.out.println("Soll der Spieler hingegen ein Computerspieler sein, gib \"nein\" ein");

        return jaNeinAbfrage();
    }

    public List<String> spielerHinzufuegen(){
        List<String> spieler = new ArrayList<>();
        System.out.println();
        System.out.println("Name des Spielers");
        String name = sc.next();
        System.out.println("Emailadresse des Spielers");
        String email = sc.next();
        spieler.add(name);
        spieler.add(email);
        return spieler;
    }
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
                System.out.println("Deine Eingabe war fehlerhaft, bitte gib \"ja\" oder \"nein\" ein");
            }
        }
        return rueckgabe;
    }
}
