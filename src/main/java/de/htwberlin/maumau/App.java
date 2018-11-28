package de.htwberlin.maumau;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    static Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        log.warn("end of Main");


        List<String> test  =new ArrayList<>();

        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add(0, "Null");
        System.out.println(test);
        test.remove(0);
        System.out.println(test.get(0));
        System.out.println(test.get(test.size()-1));


    }
}
