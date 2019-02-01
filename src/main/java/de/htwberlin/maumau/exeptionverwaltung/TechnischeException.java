package de.htwberlin.maumau.exeptionverwaltung;

public class TechnischeException extends Exception{

    public TechnischeException(){
        super("Es gab einen technischen Fehler, bitte informiere den Entwickler. Fehler: 1001");
    }

    public TechnischeException(String fehlermeldung){
        super("Es gab einen technischen Fehler, bitte informiere den Entwickler. Fehler: " + fehlermeldung);
    }
}
