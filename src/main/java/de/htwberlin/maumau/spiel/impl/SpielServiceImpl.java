/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181115
 *
 */

package de.htwberlin.maumau.spiel.impl;

import de.htwberlin.maumau.karten.entity.Farbe;
import de.htwberlin.maumau.karten.entity.Karte;
import de.htwberlin.maumau.spiel.entity.Spiel;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spieler.entity.Spieler;
import de.htwberlin.maumau.spieler.export.SpielerService;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SpielServiceImpl implements SpielService {

    private static SpielerService spielerService = new SpielerServiceImpl();


    @Override
    public Spiel anlegenSpiel() {
        return null;
    }

    //Dustin
    @Override
    public List<Karte> auswaehlenKartendeck(int kartenblatt) {
        return null;
    }
    //Dustin
    @Override
    public Karte ziehenKarteVomZiehstapel(List<Karte> ziehStapel) {
        return null;
    }
    //Dustin
    @Override
    public List<Karte> entferneGezogendeKarteVomZiehstapel(List<Karte> karteStapel, Karte karte) {
        return null;
    }

    @Override
    public List<Karte> austeilenStart(List<Karte> kartenDeck, List<Spieler> spielerListe, int durchgaenge) {
        for(int runden = 0; runden < durchgaenge; runden++){
            for (int spielerzaehler = 0; spielerzaehler < spielerListe.size();spielerzaehler++) {
                Karte karte = kartenDeck.get(kartenDeck.size()-1);
                spielerService.karteausHandblattentfernden(karte, spielerListe.get(spielerzaehler));
                kartenDeck.remove(karte);
            }
        }
        return kartenDeck;
    }

    @Override
    public List<Karte> zuZiehendeKarte(int anzahl, List<Karte> karteStapel, Spieler spieler) {
        List<Spieler> spielerliste = new ArrayList<>();
        spielerliste.add(spieler);
        austeilenStart(karteStapel, spielerliste, anzahl);
        return karteStapel;
    }

    @Override
    public List<Karte> legenKarteAufAblageStapel(Spieler spieler, List<Karte> kartenAblagestapel, Karte karte) {
        return null;
    }

    @Override
    public boolean aendernSpielrichtung() {
        return false;
    }

    @Override
    public Spiel aendernFarbe(Spiel spiel, Farbe neueFarbe) {
        return null;
    }

    @Override
    public boolean ermittleSpielende(Spieler spieler) {
        return false;
    }

    @Override
    public boolean pruefeAufMau(Spieler spieler) {
        return false;
    }

    @Override
    public boolean istMauNoetig(Spieler spieler) {
        return false;
    }

    @Override
    public void setzeMau(Spieler spieler) {

    }

    @Override
    public int anzahlStartkartenbestimmen(List<Spieler> spielerListe) {
        return 0;
    }

    @Override
    public boolean mussGemischtWerden(List<Karte> ziehstapel) {
        return false;
    }
}
