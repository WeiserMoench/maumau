/**
 * @author Christian
 */
package de.htwberlin.MauMau.model;

import java.util.List;

public class Handkarten {
	
	private int spielerId;
	private List<Karte> handkarten;
	
	
	public int getSpielerId() {
		return spielerId;
	}
	public void setSpielerId(int spielerId) {
		this.spielerId = spielerId;
	}
	public List<Karte> getKarten() {
		return handkarten;
	}
	public void setKarten(List<Karte> karten) {
		this.handkarten = karten;
	}
	
}
