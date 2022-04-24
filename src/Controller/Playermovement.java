package Controller;
import Modeles.Player;

public class Playermovement {
    Player j;
    DewaterZone aZ;
    Playermovement dJ;



    CollectArtifact rA;

    int nbActionsMax = 3;

    public Playermovement (Player j) {
        this.j = j;
        aZ = new DewaterZones(j);
        dJ = new Playermovement(j);
        rA = new CollectArtifact(j);
    }

    public Playermovement getPlayermovement() {
        return dJ;
    }

    public DewaterZonegetaZ() {
        return aZ;
    }

    public CollectArtifact getrA() {
        return rA;
    }

    public boolean estLibre() {
        int nbActionsUsed = aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction();
        //System.out.println(nbActionsUsed);
        return nbActionsUsed < nbActionsMax;
    }

    public int getnbActionsMax() {
        return nbActionsMax;
    }
    public int nbActionsUtilisees() {
        return aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction();
    }
}
