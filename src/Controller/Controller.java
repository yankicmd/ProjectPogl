package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modeles.Island;
import Modeles.Player;

public class Controller implements ActionListener {
    Island Modeles;

    public Controller(Island Modeles) {
        this.Modeles = Modeles;
    }

    /**
     * Action effectu�e � r�ception d'un �v�nement : appeler la m�thode [avance] du
     * mod�le.
     */

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.Modeles.avance();
    }
}
