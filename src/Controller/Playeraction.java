package Player;


import others.Observable;
import Modeles.Player;
import vue.ViewPlayer;

public abstract class Playeraction extends Observable {
    int nbAction;
    Player j;

    public Playeraction(Player j) {
        this.j = j;
        nbAction = 0;
    }

    public void incrNbAction(){
        this.nbAction++;


    }

    public int getNbAction() {
        return this.nbAction;
    }

}