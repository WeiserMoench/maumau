package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class Spieler extends BaseEntity {

	private String name;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}