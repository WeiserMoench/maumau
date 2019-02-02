/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 *
 */

package de.htwberlin.maumau.spielerverwaltung.entity;

import de.htwberlin.maumau.kartenverwaltung.entity.Karte;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Spieler {

//    @Id
//    private Long spielerId;
    private String name;
    private List<Karte> handkarten = new ArrayList<>();
    private boolean mauistgesetzt = false;
    private boolean ki = false;

//    public Long getSpielerId() {
//        return spielerId;
//    }
//
//    public void setSpielerId(Long spielerId) {
//        this.spielerId = spielerId;
//    }

    public boolean isMauistgesetzt() {
        return mauistgesetzt;
    }

    public void setMauistgesetzt(boolean mauistgesetzt) {
        this.mauistgesetzt = mauistgesetzt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Karte> getHandkarten() {
        return handkarten;
    }

    public void setHandkarten(List<Karte> handkarten) {
        this.handkarten = handkarten;
    }

    public boolean isKi() {
        return ki;
    }

    public void setKi(boolean ki) {
        this.ki = ki;
    }
}