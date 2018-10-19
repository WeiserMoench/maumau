package de.htwberlin.MauMau.model;

import java.util.List;

/**
 * 
 * @author Christian
 *
 */

public class Spiel extends BaseEntity {
	
	private int idAktiverSpieler;
	private int spielrichtung;
	private int summeZuziehendeKarten;
	private List<Karte> ablagestapelkarten;
	private List<Karte> ziehstapelkarten;
	private int kartendeckID;
	
	
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
	public List<Karte> getAblagestapelkarten() {
		return ablagestapelkarten;
	}
	public void setAblagestapelkarten(List<Karte> ablagestapelkarten) {
		this.ablagestapelkarten = ablagestapelkarten;
	}
	public List<Karte> getZiehstapelkarten() {
		return ziehstapelkarten;
	}
	public void setZiehstapelkarten(List<Karte> ziehstapelkarten) {
		this.ziehstapelkarten = ziehstapelkarten;
	}
	public int getKartendeckID() {
		return kartendeckID;
	}
	public void setKartendeckID(int kartendeckID) {
		this.kartendeckID = kartendeckID;
	}
	
	
	
}
