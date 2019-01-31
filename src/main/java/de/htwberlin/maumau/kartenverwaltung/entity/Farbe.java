/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181213
 */

package de.htwberlin.maumau.kartenverwaltung.entity;

public enum Farbe {



    HERZ {
        public String toString() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";
            String c = Character.toString('\u2665');
            String symbol = ANSI_RED + c + ANSI_RESET;
            return symbol;
        }
    }, PIK {
        public String toString() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_GREEN = "\u001B[32m";
            String c = Character.toString('\u2660');
            String symbol = ANSI_GREEN + c + ANSI_RESET;
            return symbol;
        }
    }, KREUZ {
        public String toString() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_PURPLE = "\u001B[35m";
            String c = Character.toString('\u2663');
            String symbol = ANSI_PURPLE + c + ANSI_RESET;
            return symbol;
        }
    }, KARO {
        public String toString() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_BLUE = "\u001B[34m";
            String c = Character.toString('\u2666');
            String symbol = ANSI_BLUE + c + ANSI_RESET;
            return symbol;
        }
    }
}
