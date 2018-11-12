package de.htwberlin.maumau.spiel.impl;

import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spieler.entity.Spieler;

import java.util.List;

public class SpielServiceImpl implements SpielService {

    @Override
    public void anlegenSpiel() {

    }

    @Override
    public List<Karte> auswaehlenKartendeck(Spiel spiel, int kartenblatt) {
        return null;
    }

    @Override
    public Karte ziehenKarteVomZiehstapel(List<Karte> ziehStapel) {
        return null;
    }

    @Override
    public List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte) {
        return null;
    }

    @Override
    public void austeilenStart(List<Karte> kartenDeck, List<Spieler> spielerListe, int durchgaenge) {

    }

    @Override
    public void zuZiehendeKarte(int anzahl, List<Karte> karteStapel, Spieler spieler) {

    }

    @Override
    public void legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {

    }

    @Override
    public boolean spielerLegtKarteAb(Spieler spieler, Karte karte) {
        return false;
    }

    @Override
    public void aendernSpielrichtung() {

    }

    @Override
    public void aendernFarbe(int neueFarbe) {

    }

    @Override
    public void ermittleSpielende(List<Spieler> spielerList) {

    }

    @Override
    public void pruefeAufMau(Spieler spieler) {

    }

    @Override
    public boolean istMauNoetig(Spieler spieler) {
        int anzahlHandKarten = spieler.getHandkarten().size();
        return anzahlHandKarten == 1;
    }

    @Override
    public void setzeMau(Spieler spieler) {

    }

    @Override
    public int anzahlStartkartenbestimmen(List<Spieler> spielerListe) {
        return 0;
    }
}
