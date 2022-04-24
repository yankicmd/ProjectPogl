package others;

import java.util.ArrayList;

import java.util.ArrayList;

public abstract class Obv {
    /**
     * On a une liste [obver] d'observateurs, initialement vide,  laquelle
     * viennent s'inscrire les observateurs via la mthode [addObver].
     */
    private ArrayList<Obver> obver;
    public Obv() {
        this.obver = new ArrayList<Obver>();
    }
    public void addObver(Obver o) {
        obver.add(o);
    }

    /**
     * Lorsque l'tat de l'objet observ change, il est convenu d'appeler la
     * mthode [notifyobver] pour prvenir l'ensemble des observateurs
     * enregistrs.
     * On le fait ici concrtement en appelant la mthode [update] de chaque
     * observateur.
     */
    public void notifyobver() {
        for(Obver o : obver) {
            o.update();
        }
    }
}
