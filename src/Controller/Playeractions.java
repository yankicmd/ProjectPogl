package Player;


import others.Observable;
import others.Observer;
import Modeles.Player;

public class Playeractions extends Observable implements Observer{
    Player j;
    DewaterZones aZ;
    Playermovement dJ;
    EchangeKeys ec;
    CollectArtifact rA;

    int nbActionsMax = 3;

    public Playeractions (Player j) {
        this.j = j;
        aZ = new DewaterZones(j);
        dJ = new Playermovement(j);
        rA = new CollectArtifact(j);
        ec = new EchangeKeys(j);

        aZ.addObserver(this);
        dJ.addObserver(this);
        rA.addObserver(this);
    }

    public Playermovement getPlayermovement() {
        return dJ;
    }

    public DewaterZones getaZ() {
        return aZ;
    }

    public CollectArtifact getrA() {
        return rA;
    }

    public boolean estLibre() {
        int nbActionsUsed = aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
        //System.out.println(nbActionsUsed);
        return nbActionsUsed < nbActionsMax && !j.getZones().estSubmergee();
    }

    public int getnbActionsMax() {
        return nbActionsMax;
    }
    public int nbActionsUtilisees() {
        return aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
    }

    @Override
    public void update() {
        this.notifyObservers();

    }
}
