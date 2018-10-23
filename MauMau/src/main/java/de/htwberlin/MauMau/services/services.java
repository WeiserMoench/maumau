package de.htwberlin.MauMau.services;

import de.htwberlin.MauMau.model.Kartendeck;
import de.htwberlin.MauMau.model.Spiel;
import de.htwberlin.MauMau.model.Spieler;

/**
 * 
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * Diese Datei enthaelt alle Methoden, die fuer den Spielablauf noetig sind.
 */
public interface services {
//	public void anlegenKarte();
	
//	public void anelgenKartendeck();
	/**
	 * Diese Methode sorgt dafuer, dass ein gewuenschtes Kartenblatt dem Spiel hinzugefuegt wird
	 * 
	 * @param spiel - es wird das zuvor erzeugte Spiel uebergeben
	 * @param kartendeck - benoetigt???
	 */
	public void auswaehlenKartendeck(Spiel spiel, Kartendeck kartendeck);
	
	/**
	 * Diese Methode fuegt einen gewuenschten Spieler hinzu und speichert diesen im Spiel
	 * 
	 * @param spiel - das zuvor angelegte Spiel wird uebergeben
	 */
	public void auswaehlenSpielerFuerSpiel(Spiel spiel);

	/**
	 * KARTENDECK MUSS NICHT ÜBERGEBEN WERDEN SONDERN DAS SPIEL, da es die Karten enthaelt
	 * 
	 * Diese Methode mischt den ziehstapel einmalig durch
	 * @param spiel - Das Spiel, dessen Kartenstapel gemischt werden soll
	 */
	public void mischenKartenstapel(Spiel spiel);
	
	/**
	 * Diese Methode soll die anzahl der benoetigten Karten aus dem 
	 * Ziehstapel entnehmen und den Handkarten des Spielers hinzufuegen
	 * 
	 * @param spiel - Das Spiel wird uebergeben, daraus wird der Ziehkartenstapel und 
	 * 					die Anzahl der zu ziehenden Karten extrahiert
	 * @param spieler - Der Spieler der nun an der Reihe ist und die neuen Karten bekommen soll
	 * @param anzahlKarten - es ist 0 anzugeben, wenn der Spieler aus Voraktionen (Strafkarten) zuziehen hat,
	 * 						wenn der Spieler nicht legen kann, und daher ziehen will, ist die 1 zuuebergeben
	 */
	public void ziehenKarteVomZiehstapel(Spiel spiel, Spieler spieler, int anzahlKarten);
	
	/**
	 * Diese Methode entfernt eine Handkarte aus den Handkarten des Spielers und legt diese auf den Ablagestapel
	 * 
	 * @param spiel - Das laufende Spiel wird übergeben, damit die Karte auf den Ablagestapel kommen kann
	 * @param spieler - Das an der Reihe seiende Spieler
	 */
	public void legenKarteAufAblageStapel(Spiel spiel, Spieler spieler);
	
	/**
	 * Diese Methode aendert die Anzahl der Karten, die der naechste Spieler ziehen muss.
	 * 
	 * @param spiel - Das Spiel, in dem die Anzahl der zuziehenden KArten geändert werden soll
	 * @param anzahlKarten - die Anzahl um die die Zahl zu ziehender Karten erhoeht werden soll 
	 */
	public void aendernAnzahlZiehkarten(Spiel spiel, int anzahlKarten);
	
	/**
	 * Methode dreht die Spielrichtung einfach um
	 * 
	 * @param spiel - Das Spiel in dem die Spielrichtung geaendert werden soll
	 */
	public void aendernSpielrichtung(Spiel spiel);
	
	
	
}
