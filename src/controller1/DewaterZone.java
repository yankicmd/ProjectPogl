package controller1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele1.Island;
import modele1.Player;
import modele1.Zone;
import modele1.Island.AccesHorsIsland;

public class DewaterZone extends ActionPlayer implements MouseListener {
    //Attributs
    //'TAILLE' un entier correspondant a la taille de la zone.
    //int TAILLE;
    //'Island' une Island.
    Island island;

    //Constructeur
    /**DewaterZone() :
     * Player j : un Player.
     * Definit j comme etant le Player concerne par l'action d'assecher une zone et initialise l'attribut Island a l'Island de j.
     **/
    public DewaterZone(Player j) {
        super(j);
        this.island = j.getIsland();
    }

    //Methodes
    /**void mouseClicked() :
     * MouseEvent argo : un evenement lie a l'utilisation d'une souris.
     * Asseche une zone proche du Player si un clique-droit est effectue sur cette zone.
     **/
    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (SwingUtilities.isRightMouseButton(arg0)) {
            try {
                int x = (arg0.getX() / 32) + 1;
                int y = (arg0.getY() / 32) + 1;
                Zone z = island.getZone(x, y);
                if (island.DewaterZone(j, z)) {
                    this.incrNbAction();
                    this.notifyObservers();
                }
                ;
            } catch (AccesHorsIsland e) {

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}

