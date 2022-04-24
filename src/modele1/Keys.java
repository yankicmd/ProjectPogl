package modele1;

import java.util.Random;

public class Keys extends Objet {

    //Attributs
    //'rand' un generateur d'entiers aleatoires.
    static Random rand = new Random();

    //Constructeur
    /**Keys() :
     * 		Element e : un element parmi un type enumere.
     * 		Definit e comme etant l'element de la Keys.
     **/
    public Keys(Element e) {
        super(e);
    }

    //Methodes
    /**Keys aleaKeyss() :
     * 		Return : une Keys possedant un element aléatoire
     **/
    public static Keys aleaKeyss() {
        //L'element neutre est exclu.
        int elem = rand.nextInt(Element.values().length-1);
        Keys c = new Keys(Element.values()[elem]);
        return c;
    }

    /**boolean cleEgales() :
     * 		Keys c : une Keys.
     * 		Return : True si la Keys donne en parametre a le meme element que la Keys considere, false sinon.
     **/
    public boolean cleEgales(Keys c) {
        return this.e == c.e;
    }

    /**String toString() :
     * 		Return : Une chaine de caracteres correspondant au mot cle suivit de l'element de la cle considere.
     **/
    public String toString() {
        return "Cle " + e.toString();
    }
}
