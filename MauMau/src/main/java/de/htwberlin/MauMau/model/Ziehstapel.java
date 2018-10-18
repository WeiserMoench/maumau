/**
 * @author Christian
 */
package de.htwberlin.MauMau.model;

import java.util.List;

public class Ziehstapel {
	
	private int spielID;
	private List<Karte> ziehstapelkarten;
	
	
	public int getSpielID() {
		return spielID;
	}
	public void setSpielID(int spielID) {
		this.spielID = spielID;
	}
	public List<Karte> getZiehstapelkarten() {
		return ziehstapelkarten;
	}
	public void setZiehstapelkarten(List<Karte> ziehstapelkarten) {
		this.ziehstapelkarten = ziehstapelkarten;
	}

}
