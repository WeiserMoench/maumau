package de.htwberlin.maumau.konfiguration;

import de.htwberlin.maumau.exeptionverwaltung.TechnischeException;
import de.htwberlin.maumau.kartenverwaltung.impl.KartenServiceImpl;
import de.htwberlin.maumau.regelnverwaltung.impl.EinfacheRegelnServiceImpl;
import de.htwberlin.maumau.regelnverwaltung.impl.ErweiterteRegelnServiceImpl;
import de.htwberlin.maumau.spielerverwaltung.impl.SpielerServiceImpl;
import de.htwberlin.maumau.spielverwaltung.impl.SpielServiceImpl;
import de.htwberlin.maumau.ui.export.SpielController;
import de.htwberlin.maumau.ui.impl.SpielControllerImpl;
import de.htwberlin.maumau.ui.impl.SpielViewer;
import de.htwberlin.maumau.virtuellerspielerverwaltung.impl.KiServiceImpl;
import org.picocontainer.Characteristics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.ConstructorInjection;


public class Config {
    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    public static void main(String[] arg) throws TechnischeException {
        try {
            registriereKomponenten();
        }catch(Exception e){
            throw new TechnischeException("Komponentenregistrierungsfehler");
        }

        try {
            container.getComponent(SpielController.class).run();
        }catch(java.lang.NullPointerException e){
            throw new TechnischeException("Fehler beim Komponente öffnen");
        }
    }

    private static void registriereKomponenten() {
        container.addComponent(KartenServiceImpl.class);
        container.addComponent("einfacheRegeln", EinfacheRegelnServiceImpl.class);
        container.addComponent("erweiterteRegeln", ErweiterteRegelnServiceImpl.class);
        container.addComponent(SpielerServiceImpl.class);
        container.addComponent(SpielViewer.class);
        container.addComponent(SpielControllerImpl.class);
        container.addComponent(KiServiceImpl.class);
        container.as(Characteristics.USE_NAMES).addComponent(SpielServiceImpl.class);
    }
}
