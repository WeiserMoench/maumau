package de.htwberlin.maumau.exeptionverwaltung.fachlicheexceptions;

public class FachlicheExceptions extends RuntimeException{
    public FachlicheExceptions(){
        super("Fachlicher Fehler");
    }

    public FachlicheExceptions(String fehlermeldung){
        super(fehlermeldung);
    }
}
