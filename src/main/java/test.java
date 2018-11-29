import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<Integer> spieler = new ArrayList<>();
        spieler.add(1);
        spieler.add(2);
        spieler.add(3);
        spieler.add(4);
        spieler.add(5);
        spieler.add(6);
        spieler.add(7);
        spieler.add(8);
        spieler.add(9);
        spieler.add(10);
        int aktiv = 9;
        boolean aussetzen = false;
        boolean richtung = true;
        int veraenderung = 1;
        int indexNaechsterSpieler;
        if(aussetzen){
            veraenderung++;
        }

        if (richtung) {
            indexNaechsterSpieler = spieler.indexOf(aktiv) + veraenderung;
        } else {
            indexNaechsterSpieler = spieler.indexOf(aktiv) - veraenderung;
        }
        int laengeSpielerliste = spieler.size();
        if (indexNaechsterSpieler >= laengeSpielerliste) {
            System.out.println("in if " + spieler.get(indexNaechsterSpieler-laengeSpielerliste));
        } else {
            System.out.println("test " + laengeSpielerliste);
            if (indexNaechsterSpieler < 0) {
                System.out.println("in if " + spieler.get(laengeSpielerliste+indexNaechsterSpieler));
            }else {
                System.out.println("out if " + spieler.get(indexNaechsterSpieler));
            }
        }
    }
}
