/**
 * @author Joerg Lehmann, Christian Fiebelkorn, Dustin Lange
 * @version 20181212
 *
 */

package de.htwberlin.maumau.ui.export;

import de.htwberlin.maumau.exeptionverwaltung.TechnischeException;

public interface SpielController {

    /**
     * Die Methode, die den Ablauf des Spieles steuert
     */
    void run() throws TechnischeException;
}
