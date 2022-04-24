package controller1;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import modele1.Keys;
import modele1.Element;
import modele1.Player;

public class ExhangeKeys extends ActionPlayer implements ActionListener {

    //Attributs
    //'rand' un generateur d'entiers aleatoires.
    static Random rand = new Random();

    //Constructeur

    /**ExhangeKeys() :
     * 		Player j : un Player.
     *
     * 		Definit j comme etant le Player concerne par l'action d'echanger une Keys.
     **/
    public ExhangeKeys(Player j) {
        super(j);
        // TODO Auto-generated constructor stub
    }

    //Methodes

    /**void echange() :
     * 		Player j : un Player.
     * 		Players : une liste de Players.
     * 		Permet au Player j de donner une de ses Keyss de maniere aleatoire a un autre Player proche parmi la liste des Players.
     **/
    public void echange() {
        ArrayList<Player> Players = j.getIsland().getPlayers();
        boolean echangeRealise = false;
        if (j.getNbCles() != 0) {
            for (Player j2 : Players) {
                if (j.getZone().memeZone(j2.getZone()) && j != j2) {
                   while (!echangeRealise) {
                        int alea = rand.nextInt(j.getInventaire().size());
                        if (j.getInventaire().get(alea) instanceof Keys) {
                            Keys c = (Keys) j.getInventaire().get(alea);
                            j2.recupereObjet(c);
                            j.enleveCles(c, 1);

                            this.incrNbAction();
                            this.notifyObservers();
                            j.getIsland().notifyObservers();

                            echangeRealise = true;
                        }
                    }
                }
            }
        }
    }

    //Methode propre a l'interface ActionListener mais non utilisee ici.
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

