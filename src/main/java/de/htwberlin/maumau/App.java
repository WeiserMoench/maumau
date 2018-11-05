package de.htwberlin.maumau;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 */
public class App {

    static Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        log.warn("end of Main");
    }
}
