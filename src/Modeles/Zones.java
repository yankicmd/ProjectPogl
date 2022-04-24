package Modeles;
public class Zones {

    public enum Element {
        Air, Eau, Terre, Feu
    }

    /** On conserve un pointeur vers la classe principale du modèle. */
    private Island Island;

    /** L'état d'une Zone est donné par un booléen. */
    protected boolean etat;

    /**
     * Pas envie de faire de getter/setter d'où protected, un choix à re-considérer
     * peut-être
     */
    protected Situation Situation;
    private Element element;

    /**
     * On stocke les coordonnées pour pouvoir les passer au modèle lors de l'appel à
     * [compteVoisines]. Situation et element initialisé à null car fait plus tard
     * et plus facIsland à détecter si erreur
     */
    private final int x, y;

    public Zone(Island Island, int x, int y) {
        this.Island = Island;
        this.etat = false;
        this.x = x;
        this.y = y;
        this.Situation = null;
        this.setElement(null);
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
    private boolean prochainEtat;

    protected void evalue() {
        switch (this.Island.compteVoisines(x, y)) {
            case 2:
                prochainEtat = etat;
                break;
            case 3:
                prochainEtat = true;
                break;
            default:
                prochainEtat = false;
        }
    }

    /* On peut jouer avec les indices et Enum.values() OPTION A CONSIDERE */
    public void progresse() {
        if (this.Situation == Situation.Normale) {
            this.Situation = Situation.Inondee;
        } else if (this.Situation == Situation.Inondee) {
            this.Situation = Situation.Submergee;
        }
    }

    protected void evolue() {
        etat = prochainEtat;
    }

    /** Un test à l'usage des autres classes (sera utilisé par la vue). */
    public boolean estVivante() {
        return etat;
    }

    /**
     * Changer la visibilité, mettre les classes ailleurs peut peut-être faciliter
     * le code et se passer de ses petites fonctions (Un getter setter si Situation
     * et Element à l'extérieur en public)
     */
    /** Tout ce qui a un rapport avec la Situation */
    public boolean estNormale() {
        return this.Situation == Situation.Normale;
    }

    public boolean estInondee() {
        return this.Situation == Situation.Inondee;
    }

    public boolean estSubmergee() {
        return this.Situation == Situation.Submergee;
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
        // return "Zone de coordonnées x = "+Integer.toString(this.x)+" y =
        // "+Integer.toString(this.y)+
        // "\nSituation: "+this.Situation.name()+
        // "\nElement: "+this.element.name();

        // Version plus propre
        return String.format("Zone de coordonnées x = %d y = %d \nSituation: %s \nElement: %s", this.x, this.y,
                this.Situation.name(), this.getElement().name());
    }

}
