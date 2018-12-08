package de.htwberlin.maumau.ui.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpielViewer {
    private Scanner sc = new Scanner(System.in);

    /**
     * Diese Methode fragt ab, ob ein neues Spiel gestartet werden soll oder ein vorheriges fortgesetzt
     *
     * @return - 1 fuer neues Spiel, 2 fuer fortsetzen
     */
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

    /**
     * Diese Methode fragt ab, ob ein weiterer Spieler hinzugefuegt werden soll
     *
     * @return boolean, ob weiterer Spieler erwuenscht ist
     */
    public boolean sollSpielerHinzugefuegtWerden() {
        System.out.println();
        System.out.println("Möchtest du einen weiteren Spieler zum Spiel hinzufügen?");

        return jaNeinAbfrage();
    }

    /**
     * Diese Methode fragt ab, ob der neue Spieler ein Mensch sein soll, oder sonst ein KI
     *
     * @return - boolean, der angibt ob der neuste Spieler ein Mensch sein soll
     */
    public boolean sollSpielerMenschSein() {
        System.out.println();
        System.out.println("Wenn der Spieler ein Mensch sein soll, gib bitte \"ja\" ein.");
        System.out.println("Soll der Spieler hingegen ein Computerspieler sein, gib \"nein\" ein");

        return jaNeinAbfrage();
    }

    /**
     * Methode fragt den Namen und die E-Mailadresse des hinzuzufuegenden Spielers ab
     * und speichert diese Informationen in einer Liste
     *
     * @return - Liste aus zwei Strings, Name des Spielers - Emailadresse des Spielers
     */
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

    /**
     * Diese Methode fragt erst ab, ob die Spieler die Regeln lesen wollen und im Anschluss
     * nach welchen Regeln gespielt werden soll.
     *
     * @return - boolean, der angibt ob die erweiterten Regeln gewünscht sind
     */
    public boolean erweiterteRegeln(){
        boolean antwort;
        System.out.println();
        System.out.println("Möchtet ihr euch die Regeln anzeigen lassen,\n" +
                "bevor ihr entscheidet, ob ihr mit einfachen oder ereiterten Regeln spielt?\n" +
                "ACHTUNG: Die Regeln können nur jetzt angesehen werden.");
        antwort=jaNeinAbfrage();
        if(antwort==true){
            anzeigenRegeln();
        }
        System.out.println("Möchtet ihr nach erweiterten Regeln spielen?");
        antwort=jaNeinAbfrage();
    return antwort;
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
                System.out.println("Deine Eingabe war fehlerhaft, bitte gib \"ja\" oder \"nein\" ein");
            }
        }
        return rueckgabe;
    }

    /**
     * Diese Methode zeigt einfach die Regeln des Spieles an.
     */
    private void anzeigenRegeln(){
        System.out.println();
        System.out.println("----  DIE REGELN ----\n");
        System.out.println("- Die einfachen Regeln -");
        System.out.println("Jeder Spieler das im Uhrzeigersinn der Reihe nach jeweils eine Karte ablegen.\n" +
                "Es darf nur eine Karte gelegt werden, wenn diese entweder in Farbe (Herz, Kreuz, Pik, Karo) \n" +
                "oder in Wert (zum Beispiel: König oder 7) übereinstimmen.\n" +
                "Sollte ein Spieler nicht legen können, so muss der eine Karte ziehen und der nächste ist an der Reihe.\n\n" +
                "Wenn ein Spieler seine vorletzte Karte legen wollen, muss er zuvor \"Mau\" eingeben, dies geht immer,\n" +
                "wenn er eine Karte legen soll. Wird \"Mau\" vergessen, bekommt der Spieler 2 Strafkarten auf die Hand.");
        System.out.println();
        System.out.println("- Die erweiterten Regeln -");
        System.out.println("Die nachfolgenden Reglen gelten zusätzlich zu den einfachen Regeln.\n" +
                "Wenn ein Spieler einen \"Buben\" legt, muss er sich eine Farbe wünschen, dabei spielt es keine Rolle,\n" +
                "um welche Farbe es sich handelt.\n" +
                "Legt ein Spieler eine \"7\", so muss der nächste Spieler 2 Karten ziehen, anstelle zu ziehen,\n" +
                "kann auch er eine weitere \"7\" legen, sodass der nächste dann 4 Karten ziehen muss usw.\n" +
                "Legt ein Spieler eine \"8\", so muss der nachfolgende Aussetzen.\n" +
                "Legt hingegen ein Spieler eine \"9\" so wird die Spielrichtung umgedreht.");
        System.out.println();
    }
}
