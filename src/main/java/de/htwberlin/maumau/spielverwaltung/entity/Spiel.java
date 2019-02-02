/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 */

package de.htwberlin.maumau.spielverwaltung.entity;

import de.htwberlin.maumau.kartenverwaltung.entity.Farbe;
import de.htwberlin.maumau.kartenverwaltung.entity.Karte;
import de.htwberlin.maumau.spielerverwaltung.entity.Spieler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spiel {

    private Long spielId;
    private Spieler aktiverSpieler;
    private boolean istSpielrichtungRechts = true;
    private int summeZuziehendeKarten;
    private List<Karte> ablagestapelkarten = new ArrayList<>();
    private List<Karte> ziehstapelkarten = new ArrayList<>();
    private List<Spieler> spielerDesSpieles = new ArrayList<>();
    private Farbe farbe;
    private boolean mussFarbeWuenschen = false;
    private boolean erfolgreichgelegt;
    private boolean aussetzen = false;
    private int spielrundenindex;
    private boolean erweiterteRegeln;
    private String sieger;


    @Id
    @GeneratedValue
    public Long getSpielId() {
        return spielId;
    }

    public void setSpielId(Long spielId) {
        this.spielId = spielId;
    }


    public Spieler getAktiverSpieler() {
        return aktiverSpieler;
    }

    public void setAktiverSpieler(Spieler aktiverSpieler) {
        this.aktiverSpieler = aktiverSpieler;
    }

    public boolean isIstSpielrichtungRechts() {
        return istSpielrichtungRechts;
    }

    public void setIstSpielrichtungRechts(boolean istSpielrichtungRechts) {
        this.istSpielrichtungRechts = istSpielrichtungRechts;
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

    public List<Spieler> getSpielerDesSpieles() {
        return spielerDesSpieles;
    }

    public void setSpielerDesSpieles(List<Spieler> spielerDesSpieles) {
        this.spielerDesSpieles = spielerDesSpieles;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public boolean isMussFarbeWuenschen() {
        return mussFarbeWuenschen;
    }

    public void setMussFarbeWuenschen(boolean mussFarbeWuenschen) {
        this.mussFarbeWuenschen = mussFarbeWuenschen;
    }

    public boolean isErfolgreichgelegt() {
        return erfolgreichgelegt;
    }

    public void setErfolgreichgelegt(boolean erfolgreichgelegt) {
        this.erfolgreichgelegt = erfolgreichgelegt;
    }

    public boolean isAussetzen() {
        return aussetzen;
    }

    public void setAussetzen(boolean aussetzen) {
        this.aussetzen = aussetzen;
    }

    public int getSpielrundenindex() {
        return spielrundenindex;
    }

    public void setSpielrundenindex(int spielrundenindex) {
        this.spielrundenindex = spielrundenindex;
    }

    public boolean isErweiterteRegeln() {
        return erweiterteRegeln;
    }

    public void setErweiterteRegeln(boolean erweiterteRegeln) {
        this.erweiterteRegeln = erweiterteRegeln;
    }

    public String getSieger() {
        return sieger;
    }

    public void setSieger(String sieger) {
        this.sieger = sieger;
    }
}
