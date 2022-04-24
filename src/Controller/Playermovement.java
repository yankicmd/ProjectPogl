package Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import Modeles.Island;
import Modeles.Island.AccesHorsIsland;
import Modeles.Player;
import Modeles.Zones;

public class Playermovement extends Playeraction implements MouseListener {
    int TAILLE;
    Island Island;

    public Playermovement(Player j) {
        super(j);
        this.Island = j.getIsland();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (SwingUtilities.isLeftMouseButton(arg0)) {
            try {
                int x = (arg0.getX() / 32) + 1;
                int y = (arg0.getY() / 32) + 1;
                Zones z = Island.getZones(x, y);
                if (Island.Playermovement(j, z)) {
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
