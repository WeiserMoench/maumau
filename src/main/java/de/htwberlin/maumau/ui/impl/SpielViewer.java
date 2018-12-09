package de.htwberlin.maumau.ui.impl;

import de.htwberlin.maumau.karten.entity.Farbe;

public class SpielViewer {


    /**
     * Diese Methode fragt ab, ob ein weiterer Spieler hinzugefuegt werden soll
     *
     * @return boolean, ob weiterer Spieler erwuenscht ist
     */
    public void sollSpielerHinzugefuegtWerden() {
        System.out.println();
        System.out.println("Möchtest du einen weiteren Spieler zum Spiel hinzufügen?");
    }

    /**
     * Diese Methode fragt ab, ob der neue Spieler ein Mensch sein soll, oder sonst ein KI
     *
     * @return - boolean, der angibt ob der neuste Spieler ein Mensch sein soll
     */
    public void sollSpielerMenschSein() {
        System.out.println();
        System.out.println("Wenn der Spieler ein Mensch sein soll, gib bitte \"ja\" ein.");
        System.out.println("Soll der Spieler hingegen ein Computerspieler sein, gib \"nein\" ein");
    }

    /**
     * Diese Methode liest die Konsoleneingabe und prueft, ob mit ja oder nein geantwortet wurde,
     * sofern dies nicht der Fall ist, wird ein Fehler ausgegeben und der Benutzer wird aufgefordert mit
     * Ja oder nein zu antworten
     *
     * @return - boolean: true fuer ja, false fuer nein
     */
    public void jaNeinAbfrageFehlermeldung(){
        System.out.println("Deine Eingabe war fehlerhaft, bitte gib \"ja\" oder \"nein\" ein");
    }

    /**
     * Diese Methode zeigt einfach die Regeln des Spieles an.
     */
    public void anzeigenRegeln(){
        System.out.println();
        System.out.println("----  DIE REGELN ----");
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

    public void maugesagt(){
        System.out.println("Du hast soeben \"Mau\" gesagt.");
        System.out.println("Welche Karte möchtest du legen?");

    }

    public void ausgabeKarte(int kartennummer, Farbe farbe, String wert) {
        System.out.println("Kartennummer " + kartennummer + " : " + farbe + " " + wert);
    }

    public void welcheKarteAblegen() {
        System.out.println("Welche Karte möchtest du legen? (Sollte \"Mau\" nötig sein, gib es jetzt ein)\n" +
                "Gib bitte die Kartennummer ein.");
    }

    public void kartennummerUnsinnig() {
        System.out.println("Du hast eine Eingabe getätigt, die bei deinen Handkarten nicht sinnig ist.\n" +
                "Bitte gib eine Zahl an, die möglich ist");
    }

    public void willkommen() {
        System.out.println("Willkommen beim MauMau Spiel");
        System.out.println("Wenn du ein neues Spiel beginnen willst, gibt bitte die 1 ein");
        System.out.println("Wenn du ein Spiel fortsetzen möchtest, wähle die 2 (noch ohne Funktion)");
        System.out.println("Welche Variante möchtest du spielen?");
    }

    public void fehlerhafteEingabeEinsZwei() {
        System.out.println("Deine Eingabe war falsch, bitte gib eine 1 oder eine 2 ein");
    }

    public void spielerNamenAnfragen() {
        System.out.println();
        System.out.println("Name des Spielers");
    }

    public void spielerEMailAnfragen() {
        System.out.println("Emailadresse des Spielers");
    }

    public void sollenRegelnAngezeigtWerden() {
        System.out.println();
        System.out.println("Möchtet ihr euch die Regeln anzeigen lassen,\n" +
                "bevor ihr entscheidet, ob ihr mit einfachen oder erweiterten Regeln spielt?\n" +
                "ACHTUNG: Die Regeln können nur jetzt angesehen werden.");
    }

    public void sollNachErweitertenRegelnGespieltWerden() {
        System.out.println("Möchtet ihr nach erweiterten Regeln spielen?");
    }
}
