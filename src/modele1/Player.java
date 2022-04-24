package modele1;

import java.util.ArrayList;

import controller1.CollectArtifact;

public class Player {
    // Attributs
    // 'Island' une Island.
    /** On conserve un pointeur vers la classe principale du modele. */
    private Island island;
    // 'z' une zone.
    private Zone z;
    // 'items' une liste d'objets.
    private ArrayList<Objet> items;

    // Constructeurs

    /**
     * Player() : Island Island : une Island. Zone z : une zone. int n : un entier.
     * Initialise l'attribut Island avec l'Island donnee en parametre, l'attribut z avec
     * la zone donnee en parametre initialise la liste items des objets du Player a
     * une liste vide.
     **/
    public Player(Island island, Zone z) {
        this.island = island;
        this.z = z;
        this.items = new ArrayList<Objet>();
    }

    // Methodes

    /**
     * void seDeplace() : Zone nz : une zone. Donne la valeur nz a l'attribut zone z
     * du Player.
     **/
    public void seDeplace(Zone nz) {
        this.z = nz;
    }

    /**
     * String toString() : Return : Une chaine de caracteres correspondant au Player
     * et a la zone ou il se trouve.
     **/
    public String toString() {
        return "--Player--\n" + z.toString() + "------------";
    }

    /**
     * Island getIsland() : Return : l'Island ou se trouve le Player.
     **/
    public Island getIsland() {
        return this.island;
    }

    /**
     * Island getZone() : Return : la zone ou se trouve le Player.
     **/
    public Zone getZone() {
        return this.z;
    }

    /**
     * void recupereObjet() : Objet o : un objet. Ajoute l'objet o a la liste items
     * des objets du Player.
     **/
    public void recupereObjet(Objet o) {
        items.add(o);
    }

    /**
     * int combienCles() : Element e : un element parmi un type enumere. Return : le
     * nombre de Keyss de type e possedees par le Player.
     **/
    public int combienCles(Element e) {
        // System.out.println("Element" + e.toString());
        int nb = 0;
        for (int i = 0; i < this.items.size(); i++) {
            Object obj = this.items.get(i);
            // System.out.println("Element ds la liste " + e.toString());
            if (obj instanceof Keys) {
                // System.out.println("type cles");
                if (((Keys) obj).e.equals(e)) {
                    // System.out.println("egaux");
                    nb++;
                }
            }
        }
        // System.out.println("Le Player " + this.nbPlayer + " a " + nb + " cles" );
        return nb;
    }

    /**
     * int getNbCles() : Return : le nombre de Keyss que possede le Player.
     **/
    public int getNbCles() {
        int res = 0;
        for (Objet o : items) {
            if (o instanceof Keys)
                res++;
        }
        return res;
    }

    /**
     * int getNbArtifacts() :
     *
     * Return : le nombre d'Artifact que possede le Player.
     **/
    public int getNbArtifacts() {
        int res = 0;
        for (Objet o : items) {
            if (o instanceof Artifact)
                res++;
        }
        return res;
    }

    /**
     * int getNbObjet() : Return : le nombre d'objets tous confondus que possede le
     * Player.
     **/
    public int getNbObjet() {
        return items.size();
    }

    /**
     * Arraylist getInventaire() : Return : l'inventaire items du Player.
     **/
    public ArrayList<Objet> getInventaire() {
        return this.items;
    }

    /**
     * void enleveCles() : Keys c : une Keys. int combien : un entier. Retire un
     * nombre correspondant au parametre combien de Keyss correspondant a un type de
     * Keys de l'inventaire items du Player.
     **/
    public void enleveCles(Keys c, int combien) {
        int nb = 0;
       while (nb < combien) {
            for (int i = 0; i < this.items.size(); i++) {
                if (this.items.get(i) instanceof Keys) {
                    if (c.cleEgales((Keys) this.items.get(i))) {
                        this.items.remove(i);
                        nb++;
                    }
                }
            }
        }
    }

    /**
     * void enleveArtifact() : Artifact a : un Artifact. Retire un Artifact a de
     * l'inventaire items du Player.
     **/
    public void enleveArtifact(Artifact a) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i) instanceof Artifact) {
                if (a.ArtifactEgal((Artifact) this.items.get(i))) {
                    this.items.remove(i);
                }
            }
        }
    }
}
