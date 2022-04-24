package Modeles;

import java.util.Random;

public class Keys extends Objet {

    static Random rand = new Random();

    public Keys(Element e) {
        super(e);
    }

    public static Keys aleaKeyss() {
        int elem = rand.nextInt(Element.values().length-1); //L'élement neutre est exclue.
        Keys c = new Keys(Element.values()[elem]);

        return c;
    }

    public boolean cleEgales(Keys c) {
        return this.e == c.e;
    }

    public String toString() {
        return "Cle " + e.toString();
    }
}
