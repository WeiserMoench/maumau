/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181113
 *
 *
 */

package de.htwberlin.maumau.karten.entity;

public enum Farbe {
    HERZ{
        public String toString(){
            return Character.toString('\u2661');
        }
    }, PIK {
        public String toString(){
            return Character.toString('\u2664');
        }
    }, KREUZ{
        public String toString(){
            return Character.toString('\u2667');
        }
    }, KARO{
        public String toString(){
            return Character.toString('\u2662');
        }
    }
}
