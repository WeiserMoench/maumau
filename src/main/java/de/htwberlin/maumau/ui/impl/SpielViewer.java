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
        System.err.println("Deine Eingabe war fehlerhaft, bitte gib \"ja\" oder \"nein\" ein");
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
                "Legt ein Spieler eine \"7\", so muss der nächste Spieler 2 Karten ziehen.\n" +
                "Legt ein Spieler eine \"8\", so muss der nachfolgende Aussetzen.\n" +
                "Legt hingegen ein Spieler eine \"9\" so wird die Spielrichtung umgedreht.");
        System.out.println();
    }

    /**
     * Feedback, wenn der Spieler Mau gesagt hat
     */
    public void maugesagt(){
        System.out.println("Du hast soeben \"Mau\" gesagt.");
        System.out.println("Welche Karte möchtest du legen?");
    }

    /**
     * Gibt die Infos zu einer Karte aus
     * @param kartennummer - Kartennummer auf der Hand
     * @param farbe - Farbe der Karte
     * @param wert - Wert der Karte
     */
    public void ausgabeKarte(int kartennummer, Farbe farbe, String wert) {
        System.out.println("Kartennummer " + kartennummer + " : " + farbe + " " + wert);
    }

    /**
     * Methode fragt, welche Karte der Spieler ablegen will oder ob er eine Ziehen will
     * Außerdem das der Spieler hier Mau sagen könnte
     */
    public void welcheKarteAblegen() {
        System.out.println("Welche Karte möchtest du legen? (Sollte \"Mau\" nötig sein, gib es jetzt ein)\n" +
                "Wenn keine Karte möglich ist, einfach \"ziehen\" eingeben. "+
                "Gib bitte die Kartennummer ein.");
    }

    /**
     * Methode sagt dem Spieler das die Nummer siner Handkarte unsinnig ist
     */
    public void kartennummerUnsinnig() {
        System.out.println("Du hast eine Eingabe getätigt, die bei deinen Handkarten nicht sinnig ist.\n" +
                "Bitte gib eine Zahl an, die möglich ist");
    }

    /**
     * Methode begruesst den Spieler zu einem neuen Spiel
     */
    public void willkommen() {
        System.out.println("Willkommen beim MauMau Spiel");
        System.out.println("Wenn du ein neues Spiel beginnen willst, gibt bitte die 1 ein");
        System.out.println("Wenn du ein Spiel fortsetzen möchtest, wähle die 2 (noch ohne Funktion)");
        System.out.println("Welche Variante möchtest du spielen?");
    }

    /**
     * Methode weisst den Spieler daraufhin, dass eine 1 oder 2 erwartet wurde
     */
    public void fehlerhafteEingabeEinsZwei() {
        System.err.println("Deine Eingabe war falsch, bitte gib eine 1 oder eine 2 ein");
    }

    /**
     * Methode fragt nach dem Namen des Spieler
     */
    public void spielerNamenAnfragen() {
        System.out.println();
        System.out.println("Name des Spielers");
    }

//    /**
//     * Methode fragt nach der Emailadresse des Spielers
//     */
//    public void spielerEMailAnfragen() {
//        System.out.println("Emailadresse des Spielers");
//    }

    /**
     * Methode fragt, ob die Regeln angezeigt werden sollen
     */
    public void sollenRegelnAngezeigtWerden() {
        System.out.println();
        System.out.println("Möchtet ihr euch die Regeln anzeigen lassen,\n" +
                "bevor ihr entscheidet, ob ihr mit einfachen oder erweiterten Regeln spielt?\n" +
                "ACHTUNG: Die Regeln können nur jetzt angesehen werden.");
    }

    /**
     * Methode fragt, ob nach erweiterten Regeln gespielt werden soll
     */
    public void sollNachErweitertenRegelnGespieltWerden() {
        System.out.println("Möchtet ihr nach erweiterten Regeln spielen? NOCH OHNE FUNKTION");
    }

    /**
     * Diese Methode zeigt dem jeweils naechsten Spieler alle wichtigen Infos zu seinem Zug an
     *
     * @param obersteKarteAblagestapelFarbe - Farbe der zuletzt gelegten Karte
     * @param obersteKarteAblagestapelWert - Wert der zuletzt gelegten Karte
     * @param spielername - Name des Spielers
     * @param anzahlGezogenerKarten - Anzahl der Karten die er zu beginn seines Zuges ziehen musste
     */
    public void infosfuerNaechstenSpieler(Farbe obersteKarteAblagestapelFarbe, String obersteKarteAblagestapelWert, String spielername, int anzahlGezogenerKarten) {
        System.out.println("\n\n-- Aktueller Spieler --");
        System.out.println(spielername);
        System.out.println("\nDu musstest " + anzahlGezogenerKarten + " Karten ziehen");
        System.out.println("\nDie obsterste Karte des Ablagestapels zeigt: " + obersteKarteAblagestapelFarbe + " " + obersteKarteAblagestapelWert+ "\n");
    }

    /**
     * Methode weisst den Spieler darauf hin, dass er eine falsche Karte ziehen wollte
     */
    public void falscheKarte() {
        System.err.println("Du hast versucht eine nicht mögliche Karte zulegen, versuche es erneut");
    }

    public void farbeWaehlen() {
        System.out.println("Du hast einen Buben gelegt bitte wähle die Zahl der Farbe\n" +
                "Zur Auswahl stehen\n1: Herz\n2: Kreuz\n3: Karo\n4: Pik\n");
    }

    public void fehlerhafteEingabeFarbe() {
        System.out.println("Du hast eine fehlerhafte Eingabe getätigt. Bitte wiederhole deine Eingabe.");
    }


    public void spielerInfoNachBube(Farbe farbeNachBube) {
        System.out.println("Der letzte Spieler hat einen Buben gelegt und sich die Farbe \"" + farbeNachBube + "\" gewünscht");
    }

    public void strafkartenVergessenesMau() {
        System.err.println("Du hast vergessen Mau zu sagen, daher hast du zwei Starfkarten auf die Hand bekommen");
    }

    public void weitererSpielerNoetig() {
        System.out.println("Da für ein Spiel mindestens zwei Spieler nötig sind,\nmuss ein weiterer hinzugefügt werden");
    }
}
