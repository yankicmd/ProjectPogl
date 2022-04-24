package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modeles.Island;

public class Controller implements ActionListener {
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit provoquer un
     * appel de méthode du modèle. Remarque : comme cette classe est interne, cette
     * inscription explicite du modèle est inutIsland. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe englobante
     * [VueCommandes].
     */
    Island Modeles;

    public Controller(Island Modeles) {
        this.Modeles = Modeles;
    }

    /**
     * Action effectuée à réception d'un événement : appeler la méthode [avance] du
     * modèle.
     */

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Modeles.avance();
    }
}
