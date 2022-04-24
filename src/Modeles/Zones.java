package modele;


enum Situation {
    Normale, Inondee, Submergee
}

public class Zone {

    public enum Element {
        Air, Eau, Terre, Feu, Neutre
    }

    /** On conserve un pointeur vers la classe principale du modèle. */
    private Island island;

    /** L'état d'une Zone est donné par un booléen. */
    protected boolean etat;

    /**
     * Pas envie de faire de getter/setter d'où protected, un choix à re-considérer
     * peut-être
     */
    protected Situation situation;
    private Element element;

    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors de l'appel à
     * [compteVoisines]. situation et element initialisé à null car fait plus tard
     * et plus facIsland à détecter si erreur
     */
    private final int x, y;

    public Zone(Island island, int x, int y) {
        this.Island = island;
        this.etat = false;
        this.x = x;
        this.y = y;
        this.situation = Situation.Normale;
        this.element = null;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Le passage à la génération suivante se fait en deux étapes : - D'abord on
     * calcule pour chaque Zone ce que sera sont état à la génération suivante
     * (méthode [evalue]). On stocke le résultat dans un attribut supplémentaire
     * [prochainEtat]. - Ensuite on met à jour l'ensemble des cellules (méthode
     * [evolue]). Objectif : éviter qu'une évolution immédiate d'une Zone pollue la
     * décision prise pour une Zone voisine.
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
     * Changer la visibilité, mettre les classes ailleurs peut peut-être faciliter
     * le code et se passer de ses petites fonctions (Un getter setter si Situation
     * et Element à l'extérieur en public)
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
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    /** Tout ce qui a un rapport avec l'élément */

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

    public String toString() {
        return String.format("Zone de coordonnées x = %d y = %d \nSituation: %s \nElement: %s\n", this.x, this.y,
                this.situation.name(), this.getElement().name());
    }

    public boolean estAdjacente(Zone z) {
        return Math.abs(this.x - z.x) <= 1 && Math.abs(this.y - z.y) <=1;
    }

}
