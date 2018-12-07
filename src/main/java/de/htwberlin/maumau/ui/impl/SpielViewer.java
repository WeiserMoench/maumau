package de.htwberlin.maumau.ui.impl;

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

    public boolean spielerHinzufuegen() {
        boolean weitererDurchgang=true;
        System.out.println("Möchtest du einen weiteren Spieler zum Spiel hinzufügen");
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
