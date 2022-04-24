package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.FIsland;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import autres.Observer;
import controleur.Playeractions;
import controleur.Playermovement;
import Modeles.DeroulementPartie;
import Modeles.Island;
import Modeles.Island.AccesHorsIsland;
import Modeles.Player;
import Modeles.Zones;

class ViewGrid extends JPanel implements Observer {
    private Island Modeles;
    private DeroulementPartie liaison;

    private final static int TAILLE = 32;
    private Playeractions aj;

    public ViewGrid(Island Modeles, DeroulementPartie dp) {
        this.Modeles = Modeles;
        this.liaison = dp;
        Modeles.addObserver(this);
        liaison.addObserver(this);

        this.addMouseListener(liaison.getAJActuel().getPlayermovement());
        this.addMouseListener(liaison.getAJActuel().getaZ());
        /**
         * Definition et application d'une taille fixe pour cette Zones de l'interface,
         * calculee en fonction du nombre de cellules et de la taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE * Island.LARGEUR, TAILLE * Island.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for (int i = 1; i <= Island.LARGEUR; i++) {
            for (int j = 1; j <= Island.HAUTEUR; j++) {
                /**
                 * ... Appeler une fonction d'affichage auxiliaire. On lui fournit les
                 * informations de dessin [g] et les coordonnees du coin en haut a gauche.
                 */
                try {
                    paint(g, Modeles.getZones(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
                } catch (AccesHorsIsland e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            for (Player j : this.Modeles.getPlayers()) {
                try {
                    paintPlayer(g, j);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            //Case pour hlicoptere
            try {
                paintHeliport(g, Modeles.getHeli());
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /** Une fonction utIsland pour la fonction paint */
    private Color getColorFromElement(Zones z) {
        Color c = null;
        switch (z.getElement()) {
            case Air:
                c = new Color(114, 230, 184);
                break;
            case Eau:
                c = new Color(44, 122, 225);
                break;
            case Terre:
                c = new Color(128, 81, 34);
                break;
            case Feu:
                c = Color.RED;
                break;
            default:
                c = Color.GRAY;
                break;
        }
        return c;
    }

    private void paint(Graphics g, Zones z, int x, int y) {
        if (!liaison.getAJActuel().estLibre()) {
            this.removeMouseListener(liaison.getAJActuel().getPlayermovement());
            this.removeMouseListener(liaison.getAJActuel().getaZ());
        }
        Color c = getColorFromElement(z).brighter();

        if (z.estNormale()) {
            g.setColor(c);
        }

        if (z.estInondee()) {
            g.setColor(Color.MAGENTA);
        }

        if (z.estSubmergee()) {
            g.setColor(Color.BLACK);
        }
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }

    public void paintPlayer(Graphics g, Player j) throws IOException {
        Image imgPlayer = ImageIO.read(new FIsland("res/Player" + Integer.toString(Modeles.getPlayers().indexOf(j)+1) + ".png"));
        g.drawImage(imgPlayer, (j.getZones().getX() - 1) * TAILLE, (j.getZones().getY() - 1) * TAILLE, this);
    }

    public void paintHeliport(Graphics g, Zones z) throws IOException {
        Image imgHeliport = ImageIO.read(new FIsland("res/heliport.png"));
        g.drawImage(imgHeliport, (z.getX() - 1) * TAILLE, (z.getY() - 1) * TAILLE, this);
    }
}


