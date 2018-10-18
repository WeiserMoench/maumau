package de.htwberlin.MauMau.services;
/**
 * 
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * Diese Datei enthaelt alle Methoden, die fuer den Spielablauf noetig sind.
 */
public interface services {
	public void anlegenKarte();
	
	public void anelgenKartendeck();
	
	public void anlegenNeuerSpieler();
	
	public void anlegenNeuesSpiel();

	public void mischenKartendeck();
	
	public void austeilenKartenAnSpieler();
	
	public void ziehenKarteVomZiehstapel();
	
	public void legenKarteAufAblageStapel();
	
}
