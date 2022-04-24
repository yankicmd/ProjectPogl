package Controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modeless.Island;
import Modeless.Island.AccesHorsIsland;
import Modeless.Player;
import Modeless.Zone;

public class Playermovement  implements MouseListener {
        private Island Island;
        private Player j;
        private final int TAILLE;

        public Playermovement(Island Island) {
            this.Island = Island;
            TAILLE = Island.LARGEUR*Island.HAUTEUR;
        }

        @Override
        public void mouseClicked(MouseEvent arg0){
            j = Island.getPlayers().get(0);
            try {
                int x = (arg0.getX() / 32)+1;
                int y = (arg0.getY() / 32)+1;
                Zone z = Island.getZone(x, y);
                Island.Playermovement(j, z);
            } catch (AccesHorsIsland e) {
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

}
