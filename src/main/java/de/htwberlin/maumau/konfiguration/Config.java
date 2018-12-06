package de.htwberlin.maumau.konfiguration;

import de.htwberlin.maumau.karten.impl.KartenServiceImpl;
import de.htwberlin.maumau.regelnmaumau.impl.EinfacheRegelnServiceImpl;
import de.htwberlin.maumau.regelnmaumau.impl.ErweiterteRegelnServiceImpl;
import de.htwberlin.maumau.spiel.impl.SpielServiceImpl;
import de.htwberlin.maumau.spieler.impl.SpielerServiceImpl;
import de.htwberlin.maumau.ui.impl.SpielControllerImpl;
import de.htwberlin.maumau.ui.export.SpielController;
import de.htwberlin.maumau.ui.impl.SpielViewer;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.ConstructorInjection;

public class Config {
    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    public static void main(String[] arg){
        registriereKomponenten();
        container.getComponent(SpielController.class).run();
    }

    private static void registriereKomponenten() {
//        KartenServiceImpl kartenService = new KartenServiceImpl();
//        EinfacheRegelnServiceImpl einfacherRegelnService = new EinfacheRegelnServiceImpl();
//        ErweiterteRegelnServiceImpl erweitereterRegelnService = new ErweiterteRegelnServiceImpl();
//        SpielServiceImpl spielService = new SpielServiceImpl();
//        SpielerServiceImpl spielerService = new SpielerServiceImpl();
//        SpielViewer spielViewer = new SpielViewer();
//        SpielControllerImpl spielController = new SpielControllerImpl();

        container.addComponent(KartenServiceImpl.class);
        container.addComponent("einfacheRegeln", EinfacheRegelnServiceImpl.class);
        container.addComponent("erweiterteRegeln", ErweiterteRegelnServiceImpl.class);
        container.addComponent(SpielServiceImpl.class);
        container.addComponent(SpielerServiceImpl.class);
        container.addComponent(SpielViewer.class);
        container.addComponent(SpielControllerImpl.class);
    }
}
