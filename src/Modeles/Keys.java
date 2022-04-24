package Modeless;

import Modeles.Zones.Element;
import java.util.Random;

public class Keys {

    static Random rand = new Random();

    private Element element;

    public Keys(Element e) {
        this.element = null;
    }

    public static Keys aleaKeys() {
        int elem = rand.nextInt(4);
        Keys c = new Keys(null);
        if(elem == 0) {
            c.element = Element.Air;
        }
        if(elem == 1) {
            c.element = Element.Eau;
        }
        if(elem == 2) {
            c.element = Element.Terre;
        }
        if(elem == 3) {
            c.element = Element.Feu;
        }
        return c;
    }

    public Element getElement() {
        return this.element;
    }

}
