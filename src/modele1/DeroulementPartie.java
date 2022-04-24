package modele1;

import java.util.ArrayList;
import java.util.Random;

import others1.Observab;
import others1.Observer;
import controller1.ActionPlayers;

public class DeroulementPartie extends Observab implements Observer {

    //Attributs

    //'rand' un generateur d'entiers aleatoires.
    Random rand = new Random();
    //'Island' une Island.
    /** On conserve un pointeur vers la classe principale du modele. */
    private Island island;
    //'j' un Player.
    private Player j;
    //'ajActuel' les actions du Player actuellement en action.
    private ActionPlayers ajActuel;
    //'indicePlayer' un entier correspondant a l'indice du Player actuellement en action.
    private int indicePlayer;
    //'actionsRestantes' un entier correpondant au nombre d'actions restantes du Player en action.
    private int actionsRestantes;
    /**DeroulementPartie() :
     * 		Island Island : une Island.
     * 		Definit Island comme etant l'attribut Island de la partie. initialise indicePlayer a 0 pour definir le premier Player de la liste comme etant le Player
     * 		en action, accorde a ce Player des actions ajActuels et un nombre d'action restante actionsRestantes et ajoute ses actions a la liste des observateurs.
     **/
    public DeroulementPartie(Island island) {
        this.island = island;
        indicePlayer = 0;
        this.j = island.getPlayers().get(indicePlayer);
        ajActuel = new ActionPlayers(j);
        actionsRestantes = ajActuel.getnbActionsMax();
        ajActuel.addObserver(this);
    }

    //Methodes
    /**void prochainPlayer() :
     * 		Fait passer le Player actuel au Player suivant.
     **/
    public void prochainPlayer() {
        indicePlayer++;
        j = island.getPlayers().get(indicePlayer % island.getPlayers().size());
        ajActuel = new ActionPlayers(j);

        actionsRestantes = ajActuel.getnbActionsMax();

        ajActuel.addObserver(this);
        this.notifyObservers();
    }

    /**ActionPlayers getAJActuel() :
     * 		Return : les actions du Player actuel.
     **/
    public ActionPlayers getAJActuel() {
        return ajActuel;
    }

    /**int getindicePlayerModulo() :
     * 		Return : l'indice du Player actuel modulo la taille de la liste de Players.
     **/
    public int getindicePlayerModulo() {
        return indicePlayer % island.getPlayers().size();
    }

    /**int getActionsRestantes() :
     * 		Return : le nombre d'actions restantes a effectuer par le Player actuel.
     **/
    public int getActionsRestantes() {
        return ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees();
    }

    /**Player getPlayer() :
     * 		Return : le Player actuel.
     **/
    public Player getPlayer() {
        return this.j;
    }

    /**void giveAleaKeys() :
     * 		Donne une Keys d'un element aleatoire ou rien a un Player de maniere aleatoire. Le Player a 20% de chance de recevoir
     * 		une Keys (utilisee a la fin de chaque tour).
     **/
    public void giveAleaKeys() {
        int chance = rand.nextInt(100);
        if (0 <= chance && chance < 20) {
            Player PlayerA = getPlayer();
            Keys cAlea = Keys.aleaKeyss();
            PlayerA.recupereObjet(cAlea);
            this.notifyObservers();
            island.notifyObservers();
        }
    }

    /**boolean gagne() :
     * 		Return : True si les conditions d'une victoire sont reunies, false sinon.
     **/
    public boolean gagne() {
        ArrayList<Player> listJ = island.getPlayers();
        ArrayList<Object> Artifact = new ArrayList<Object>();
        for (Player j : listJ) {
            //on verifie qu'il est sur l'heliport
            if (!j.getZone().equals(island.getHeli())) {
                return false;
            }
            //on parcourt ces items
            ArrayList<Objet> items = j.getInventaire();
            for (Object o : items) {
                if ((o instanceof Artifact) && (!Artifact.contains(o))){
                    Artifact.add(o);
                }
            }
        }
        if (!(Artifact.size() == island.nbArtifact)) {
            return false;
        }
        return true;
    }

    /**boolean defaite() :
     * 		Return : True si les conditions d'une defaite sont reunies, false sinon.
     **/
    public boolean defaite() {
        return PlayerSubmergees();
    }

    /**boolean PlayerSubmergees() :
     * 		Return : True si au moins un Player est sur une zone submergee, false sinon.
     **/
    public boolean PlayerSubmergees() {
        for(Player j: island.getPlayers()) {
            if (j.getZone().estSubmergee()) return true;
        }
        return false;
    }

    @Override
    public void update() {
        actionsRestantes = getActionsRestantes();
        this.notifyObservers();
    }

    /**Island getIsland() :
     * 		Return : l'Island concerne par la partie.
     **/
    public Island getIsland() {
        return this.island;
    }
}
