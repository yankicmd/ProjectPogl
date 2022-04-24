package Controller;

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modeles.Island;
import Modeles.Joueur;

public class Controleur implements ActionListener {
    Island Modeles;

    public Controleur(Island modele) {
        this.modele = modele;
    }

    /**
     * Action effectu�e � r�ception d'un �v�nement : appeler la m�thode [avance] du
     * mod�le.
     */

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.modele.avance();
    }
}
