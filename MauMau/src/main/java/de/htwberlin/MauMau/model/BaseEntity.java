package de.htwberlin.MauMau.model;

/**
 * Created by Dustin on 12.10.2018
 */
public class BaseEntity {

    private int id;
    private String name;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
