package controller1;

import others1.Observab;
import modele1.Player;
import view1.ViewPlayer;

public abstract class ActionPlayer extends Observab {
    //Attributs
    //'nbAction' un entier representant le nombre d'actions que peut faire un Player j.
    int nbAction;
    //'j' un Player.
    Player j;

    //Constructeur
    /**ActionPlayer() :
     * 		Player j : un Player.
     * 		Definit j comme etant le Player concerne par les actions avec l'argument j
     *		et initialise son nombre d'action a 0.
     **/
    public ActionPlayer(Player j) {
        this.j = j;
        nbAction = 0;
    }

    //Methodes
    /**void incrNbAction() :
     * 		Incremente de 1 le nombre d'actions nbAction du Player concerne par les actions.
     **/
    public void incrNbAction(){
        this.nbAction++;
    }

    /**int getNbAction() :
     * 		Return : un entier correspondant au nombre d'action nbAction a faire du Player concerne par les actions.
     **/
    public int getNbAction() {
        return this.nbAction;
    }

}
