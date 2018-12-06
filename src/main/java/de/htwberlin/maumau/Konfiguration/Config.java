package de.htwberlin.maumau.Konfiguration;

import de.htwberlin.maumau.karten.impl.KartenServiceImpl;
import de.htwberlin.maumau.regelnmaumau.impl.EinfacheRegelnServiceImpl;
import de.htwberlin.maumau.regelnmaumau.impl.ErweiterteRegelnServiceImpl;
import de.htwberlin.maumau.spiel.export.SpielService;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.ConstructorInjection;

public class Config {
    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    public static void main(String[] arg){
        registriereKomponenten();
        container.getComponent(SpielService.class).anlegenSpiel();
    }

    private static void registriereKomponenten() {
        KartenServiceImpl kartenService = new KartenServiceImpl();
        EinfacheRegelnServiceImpl einfacherRegelnService = new EinfacheRegelnServiceImpl();
        ErweiterteRegelnServiceImpl erweitereterRegelnService = new ErweiterteRegelnServiceImpl();
        SpielServiceImpl spielService = new SpielServiceImpl();
        SpielerServiceImpl spielerService = new SpielerServiceImpl();

        container.addComponent(kartenService);
        container.addComponent("einfacheRegeln", einfacherRegelnService);
        container.addComponent("erweiterteRegeln", erweitereterRegelnService);
        container.addComponent(spielService);
        container.addComponent(spielerService);

    }
}
