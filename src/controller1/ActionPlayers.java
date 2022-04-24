package controller1;
import others1.Observab;
import others1.Observer;
import modele1.Player;
public class ActionPlayers extends Observab implements Observer{
    //Attributs
    //'j' un Player.
    Player j;
    //'az' la capacite d'assecher une zone.
    DewaterZone aZ;
    //'dJ' la capacite de se deplacer.
    MovementPlayer dJ;
    //'ec' la capacite d'echanger des Keyss.
    ExhangeKeys ec;
    //'rA' la capacite de ramasser un Artifact.
    CollectArtifact rA;
    //'nbActionMax" un entier correspondant au nombre maximum d'actions disponibles d'un Player.
    int nbActionsMax = 3;

    //Constructeur

    /**ActionPlayers() :
     * 		Player j : un Player.
     * 		Definit j comme etant le Player attribut j concerne par les actions avec l'argument j, lui donne la capacite aZ d'assecher une zone, dJ
     * 		de se deplacer, rA de ramasser un Artifact, ec d'echanger des Keyss et ajoute aZ, dJ et rA aux observateurs des actions du Players.
     **/
    public ActionPlayers (Player j) {
        this.j = j;
        aZ = new DewaterZone(j);
        dJ = new MovementPlayer(j);
        rA = new CollectArtifact(j);
        ec = new ExhangeKeys(j);

        aZ.addObserver(this);
        dJ.addObserver(this);
        rA.addObserver(this);
        ec.addObserver(this);
    }

    //Methodes
    /**MovementPlayer getMovementPlayer() :
     * 	Return : dJ la capacite a se deplacer du Player concerne par les actions.
     **/
    public MovementPlayer getMovementPlayer() {
        return dJ;
    }

    /**DewaterZone getaZ() :
     * 		Return : aZ la capacite a assecher une zone du Player concerne par les actions.
     **/
    public DewaterZone getaZ() {
        return aZ;
    }

    public ExhangeKeys getExhangeKeys() {
        return ec;
    }

    /**CollectArtifact getrA() :
     * Return : rA la capacite a ramasser un Artifact du Player concerne par les actions.
     **/
    public CollectArtifact getrA() {
        return rA;
    }

    /**boolean estLibre() :
     * Return : True si il reste des actions a faire au Player concerne et s'il n'est pas sur une zone submergee, false sinon.
     **/
    public boolean estLibre() {
        int nbActionsUsed = aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
        return nbActionsUsed < nbActionsMax && !j.getZone().estSubmergee();
    }

    /**int getnbActionsMax() :
     * Return : le nombre d'actions maximum que peut faire le Player concerne nbActionsMax.
     **/
    public int getnbActionsMax() {
        return nbActionsMax;
    }

    /**int nbActionsUtilisees() :
     * Return : le nombre d'actions deja utilisees par le Player concerne.
     **/
    public int nbActionsUtilisees() {
        return aZ.getNbAction()+dJ.getNbAction()+rA.getNbAction() + ec.getNbAction();
    }

    @Override
    public void update() {
        this.notifyObservers();
    }
}

