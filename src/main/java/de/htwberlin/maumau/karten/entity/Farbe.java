/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181213
 */

package de.htwberlin.maumau.karten.entity;

public enum Farbe {
    HERZ {
        public String toString() {
            return Character.toString('\u2665');
        }
    }, PIK {
        public String toString() {
            return Character.toString('\u2660');
        }
    }, KREUZ {
        public String toString() {
            return Character.toString('\u2663');
        }
    }, KARO {
        public String toString() {
            return Character.toString('\u2666');
        }
    }
}
