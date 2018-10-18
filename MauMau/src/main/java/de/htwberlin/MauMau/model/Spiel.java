package de.htwberlin.MauMau.model;

public class Spiel extends BaseEntity {
	
	private int ID_aktiver_Spieler;
	private int Spielrichtung;
	private int Summe_Ziehende_Karten;
	
	
	public int getID_aktiver_Spieler() {
		return ID_aktiver_Spieler;
	}
	public void setID_aktiver_Spieler(int iD_aktiver_Spieler) {
		ID_aktiver_Spieler = iD_aktiver_Spieler;
	}
	public int getSpielrichtung() {
		return Spielrichtung;
	}
	public void setSpielrichtung(int spielrichtung) {
		Spielrichtung = spielrichtung;
	}
	public int getSumme_Ziehende_Karten() {
		return Summe_Ziehende_Karten;
	}
	public void setSumme_Ziehende_Karten(int summe_Ziehende_Karten) {
		Summe_Ziehende_Karten = summe_Ziehende_Karten;
	}
	
	
	
}
