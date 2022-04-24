package controller1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele1.Island;
import modele1.Player;

public class Controleur implements ActionListener {
    //Attributs

    //'modele une Island.
    Island modele;

    //Constructeur
    /**Controleur() :
     * 		Island modele : une Island.
     * 		Definit modele comme etant l'Island attribut modele concerne par le controleur.
     **/
    public Controleur(Island modele) {
        this.modele = modele;
    }

    //Methodes
    /**
     * Action effectuee a la reception d'un evenement : appeler la methode [avance] du
     * modele.
     */
    /**void actionPerformed() :
     * 		ActionEvent argo : une action.
     * 		Met a jour le modele.
     **/
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.modele.avance();
    }
}
