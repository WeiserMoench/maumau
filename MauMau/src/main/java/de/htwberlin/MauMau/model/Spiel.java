package de.htwberlin.MauMau.model;
/**
 * 
 * @author Studium
 *
 */

public class Spiel extends baseEntity{
	
	private int idAktiverSpieler;
	private int spielrichtung;
	private int summeZuziehendeKarten;
	
	
	public int getIdAktiverSpieler() {
		return idAktiverSpieler;
	}
	public void setIdAktiverSpieler(int idAktiverSpieler) {
		this.idAktiverSpieler = idAktiverSpieler;
	}
	public int getSpielrichtung() {
		return spielrichtung;
	}
	public void setSpielrichtung(int spielrichtung) {
		this.spielrichtung = spielrichtung;
	}
	public int getSummeZuziehendeKarten() {
		return summeZuziehendeKarten;
	}
	public void setSummeZuziehendeKarten(int summeZuziehendeKarten) {
		this.summeZuziehendeKarten = summeZuziehendeKarten;
	}
	
	
	
}
