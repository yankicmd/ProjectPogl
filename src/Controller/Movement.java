package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.Island;
import modele.Joueur;
import modele.Zones;

public class Movement implements MouseListener {
    private Island Island;
    private Joueur j;
    private final int TAILLE;

    public DeplacementJoueur(Island Island, int nbPixel) {
        this.Island = Island;
        TAILLE = nbPixel;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int x = (arg0.getX() / 32)+1;
        int y = (arg0.getY() / 32)+1;
       Zoness z = null;

        System.out.println(x + "      " + y);
        try {
            z = Island.getZones(x, y);
        } catch (Exception e) {
            System.out.println("PROBLEME DEPLACEMENT SOURIS");
            System.exit(1);
        }

        j = Island.getJoueurs().get(0);
        Island.deplacementJoueur(j, z);
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
