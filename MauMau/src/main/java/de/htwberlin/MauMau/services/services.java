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
	
	public void auswaehlenKartendeck(Spiel spiel, Kartendeck kartendeck);
	
	public Spiel anlegenNeuesSpiel();
	
	public void auswaehlenSpielerFuerSpiel(Spiel spiel);

	public void mischenKartendeck(Kartendeck kartendeck);
	
	public void ziehenKarteVomZiehstapel(int anzahlKarten, Spiel spiel, Spieler spieler);
	
	public void legenKarteAufAblageStapel(Spiel spiel, Spieler spieler);
	
	public void aendernAnzahlZiehkarten(Spiel spiel);
	
	public void aendernSpielrichtung(Spiel spiel);
	
	
	
}
