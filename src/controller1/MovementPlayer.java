package controller1;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modele1.Island;
import modele1.Island.AccesHorsIsland;
import modele1.Player;
import modele1.Zone;

public class MovementPlayer extends ActionPlayer implements MouseListener {
    //Attributs

    //'Taille' un entier correpondant a une taille
    //int TAILLE;
    //'Island' une Island.
    Island Island;

    //Constructeur

    /**MovementPlayer() :
     * 		Player j : un Player.
     * 		Definit j comme etant le Player concerne par l'action de se deplacer et initialise l'attribut Island a l'Island de j.
     **/
    public MovementPlayer(Player j) {
        super(j);
        this.Island = j.getIsland();
    }

    //Methodes
    /**void mouseClicked() :
     * 		MouseEvent argo : un evenement lie a l'utilisation d'une souris.
     *  		Deplace un Player vers une zone proche si un clique-gauche est effectue sur cette zone.
     **/
    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (SwingUtilities.isLeftMouseButton(arg0)) {
            try {
                int x = (arg0.getX() / 32) + 1;
                int y = (arg0.getY() / 32) + 1;
                Zone z = Island.getZone(x, y);
                if (Island.MovementPlayer(j, z)) {
                    this.incrNbAction();
                    this.notifyObservers();
                }
                ;
            } catch (AccesHorsIsland e) {

            }
        }
    }

    //Methodes propres a l'interface MouseListener mais non utilisees ici.
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
