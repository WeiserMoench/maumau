package de.htwberlin.MauMau.services;

/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * Diese Datei enthaelt alle Methoden, die fuer den Spielablauf noetig sind.
 */
public interface services {
    void anlegenKarte();

    void anelgenKartendeck();

    void anlegenNeuerSpieler();

    void anlegenNeuesSpiel();

    void mischenKartendeck();

    void austeilenKartenAnSpieler();

    void ziehenKarteVomZiehstapel();

    void legenKarteAufAblageStapel();

}
