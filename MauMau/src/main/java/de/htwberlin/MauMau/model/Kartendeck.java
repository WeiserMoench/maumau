package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class Kartendeck extends BaseEntity {


    private String beschreibung;
    private String name;

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
