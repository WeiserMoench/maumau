/**
 * @author Christian
 */
package de.htwberlin.MauMau.model;

import java.util.List;

public class Ablagestapel {
	
	private int spielID;
	private List<Karte> ablagestapelkarten;
	
	
	public int getSpielID() {
		return spielID;
	}
	public void setSpielID(int spielID) {
		this.spielID = spielID;
	}
	public List<Karte> getAblagestapelkarten() {
		return ablagestapelkarten;
	}
	public void setAblagestapelkarten(List<Karte> ablagestapelkarten) {
		this.ablagestapelkarten = ablagestapelkarten;
	}

}
