package Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import Modeles.Island;
import Modeles.Player;
import Modeles.Zones;
import Modeles.Island.AccesHorsIsland;

public class DewaterZones extends Playeraction implements MouseListener {
    int TAILLE;
    Island Island;

    public DewaterZones(Player j) {
        super(j);
        this.Island = j.getIsland();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (SwingUtilities.isRightMouseButton(arg0)) {
            try {
                int x = (arg0.getX() / 32) + 1;
                int y = (arg0.getY() / 32) + 1;
                Zones z = Island.getZones(x, y);
                if (Island.DewaterZones(j, z)) {
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
