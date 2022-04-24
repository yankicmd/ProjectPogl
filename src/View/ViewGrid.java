package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import autres.Obver;
import Modelesss.Island;
import Modelesss.Zoness;

class ViewGrid extends JPanel implements Obver {
    /** On maintient une référence vers le modèle. */
    privateIsland Modelesss;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE = 32;

    /** Constructeur. */
    public ViewGrid(Island Modelesss) {
        this.Modelesss = Modelesss;
        /** On enregistre la View [this] en tant qu'observateur de [Modelesss]. */
        Modelesss.addObver(this);
        /**
         * Définition et application d'une taille fixe pour cetteZoness de l'interface,
         * calculée en fonction du nombre de cellules et de la taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE *Island.LARGEUR, TAILLE *Island.HAUTEUR);
        this.setPreferredSize(dim);
    }

    /**
     * L'interface [Obver] demande de fournir une méthode [update], qui sera
     * appelée lorsque la View sera notifiée d'un changement dans le modèle. Ici on
     * se content de réafficher toute la grille avec la méthode prédéfinie
     * [repaint].
     */
    public void update() {
        repaint();
    }

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode [paintComponent]
     * qui définit l'action à accomplir pour afficher cet élément. On la redéfinit
     * ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les éléments de style sur le dessin, comme la
     * couleur actuelle.
     */
    public void paintComponent(Graphics g) {
        super.repaint();
        /** Pour chaque cellule... */
        for (int i = 1; i <=Island.LARGEUR; i++) {
            for (int j = 1; j <=Island.HAUTEUR; j++) {
                /**
                 * ... Appeler une fonction d'affichage auxiliaire. On lui fournit les
                 * informations de dessin [g] et les coordonnées du coin en haut à gauche.
                 */
                paint(g, Modelesss.getZoness(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    /** Une fonction utIsland pour la fonction paint */
    private Color getColorFromElement(Zoness z) {
        Color c = null;
        switch (z.getElement()) {
            case Air:
                c = new Color(114,230,184);
                break;
            case Eau:
                c = new Color(44,122,225);
                break;
            case Terre:
                c = new Color(128,81,34);
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

    /**
     * Fonction auxiliaire de dessin d'une cellule. Ici, la classe [Zoness] ne peut
     * être désignée que par l'intermédiaire de la classe [Island] à laquelle elle est
     * interne, d'où le type [Island.Zoness]. Ceci serait impossible si [Zoness] était
     * déclarée privée dans [Island].
     */
    private void paint(Graphics g,Zoness z, int x, int y) {
        Color c = getColorFromElement(z).brighter();

        if (z.estNormale()) {
            g.setColor(c);
        }

        if (z.estInondee()) {
            g.setColor(c.MAGENTA);
        }

        if (z.estSubmergee()) {
            g.setColor(Color.BLACK);
        }

        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }
}
