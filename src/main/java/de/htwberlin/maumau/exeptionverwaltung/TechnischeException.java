/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20190202
 *
 */
package de.htwberlin.maumau.exeptionverwaltung;

public class TechnischeException extends Exception{

    /**
     * Methode schmeisst eine Exception ohne genauere Beschreibung des technischen Fehlers
     */
    public TechnischeException(){
        super("Es gab einen technischen Fehler, bitte informiere den Entwickler. Fehler: 1001");
    }

    /**
     * Methode wirft eine Exception und bietet die Moeglichkeit am Ende ncoh eine Info mit zu geben
     *
     * @param fehlermeldung - Meldung die angezeigt werden soll
     */
    public TechnischeException(String fehlermeldung){
        super("Es gab einen technischen Fehler, bitte informiere den Entwickler. " + fehlermeldung);
    }
}
