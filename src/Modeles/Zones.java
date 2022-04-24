package Modeless;

enum Situation {
    Normale, Inondee, Submergee
}

public class Zones {

    /** On conserve un pointeur vers la classe principale du Modeles. */
    private Island Island;

    /** L'etat d'une Zones est donne par un booleen. */
    protected boolean etat;

    /**
     * Pas envie de faire de getter/setter d'ou protected, un choix  a reconsiderer
     * peut-etre
     */
    protected Situation situation;
    private Element Element;

    /**
     * On stocke les coordonnees pour pouvoir les passer au Modeles lors de l'appel a
     * [compteVoisines]. situation et Element initialise a null car fait plus tard
     * et plus facIsland a detecter si erreur
     */
    protected final int x, y;

    /**
     * On designe une Zones comme etant l'heliport ou non a l'aide d'un boolean
     *
     */
    private boolean heliport;

    public Zones(Island Island, int x, int y) {
        this.Island = Island;
        this.etat = false;
        this.x = x;
        this.y = y;
        this.situation = Situation.Normale;
        this.Element = null;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Le passage a la generation suivante se fait en deux etapes : - D'abord on
     * calcule pour chaque Zones ce que sera sont etat a la generation suivante
     * (methode [evalue]). On stocke le resultat dans un attribut supplementaire
     * [prochainEtat]. - Ensuite on met a jour l'ensemble des cellules (methode
     * [evolue]). Objectif : eviter qu'une evolution immediate d'une Zones pollue la
     * decision prise pour une Zones voisine.
     */

    /* On peut jouer avec les indices et Enum.values() OPTION A CONSIDERE */
    public void progresse() {
        if (this.situation == Situation.Normale) {
            this.situation = Situation.Inondee;
        } else if (this.situation == Situation.Inondee) {
            this.situation = Situation.Submergee;
        }
    }

    /**
     * Changer la visibilite, mettre les classes ailleurs peut peut-etre faciliter
     * le code et se passer de ses petites fonctions (Un getter setter si Situation
     * et Element a l'exterieur en public)
     */
    /** Tout ce qui a un rapport avec la Situation */
    public boolean estNormale() {
        return this.situation == Situation.Normale;
    }

    public boolean estInondee() {
        return this.situation == Situation.Inondee;
    }

    public boolean estSubmergee() {
        return this.situation == Situation.Submergee;
    }

    public Element getElement() {
        return Element;
    }

    public void setElement(Element Element) {
        this.Element = Element;
    }

    /** Tout ce qui a un rapport avec l'Element */

    public boolean estAir() {
        return this.getElement() == Element.Air;
    }

    public boolean estEau() {
        return this.getElement() == Element.Eau;
    }

    public boolean estTerre() {
        return this.getElement() == Element.Terre;
    }

    public boolean estFeu() {
        return this.getElement() == Element.Feu;
    }

    public boolean estNeutre() {
        return this.getElement() == Element.Neutre;
    }

    public String toString() {
        return String.format("Zones de coordonnees x = %d y = %d \nSituation: %s \nElement: %s\n", this.x, this.y,
                this.situation.name(), this.getElement().name());
    }

    public boolean estUnDeplacementPossible(Zones z) {
        return Math.abs(this.x - z.x) == 1 && Math.abs(this.y - z.y) == 0 || Math.abs(this.x - z.x) == 0 && Math.abs(this.y - z.y) == 1;
    }

    public boolean estAdjacente(Zones z) {
        return Math.abs(this.x - z.x ) <= 1 && Math.abs(this.y - z.y) <=1;
    }
    public boolean memeZones(Zones z) {
        return this==z;
    }

    public boolean isHeliport() {
        return this.heliport;
    }

    public void setHeliport() {
        this.heliport = true;
    }
}


