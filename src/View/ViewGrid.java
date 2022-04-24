package View;

public class ViewGrid {import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import others.Obv;
import Modeles.Island;
import Modeles.Zones;

    class VueGrille extends JPanel implements Obv {
        /** On maintient une référence vers le modèle. */
        private Island Modeles;
        /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
        private final static int TAILLE = 12;

        /** Constructeur. */
        public VueGrille(Island Modeles) {
            this.Modeles = Modeles;
            /** On enregistre la vue [this] en tant qu'observateur de [Modeles]. */
            Modeles.addObv(this);
            /**
             * Définition et application d'une taille fixe pour cette Zones de
             * l'interface, calculée en fonction du nombre de cellules et de la
             * taille d'affichage.
             */
            Dimension dim = new Dimension(TAILLE*Island.LARGEUR,
                    TAILLE*Island.HAUTEUR);
            this.setPreferredSize(dim);
        }

        /**
         * L'interface [Obv] demande de fournir une méthode [update], qui
         * sera appelée lorsque la vue sera notifiée d'un changement dans le
         * modèle. Ici on se content de réafficher toute la grille avec la méthode
         * prédéfinie [repaint].
         */
        public void update() { repaint(); }

        /**
         * Les éléments graphiques comme [JPanel] possèdent une méthode
         * [paintComponent] qui définit l'action à accomplir pour afficher cet
         * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
         *
         * La classe [Graphics] regroupe les éléments de style sur le dessin,
         * comme la couleur actuelle.
         */
        public void paintComponent(Graphics g) {
            super.repaint();
            /** Pour chaque cellule... */
            for(int i=1; i<=Island.LARGEUR; i++) {
                for(int j=1; j<=Island.HAUTEUR; j++) {
                    /**
                     * ... Appeler une fonction d'affichage auxiliaire.
                     * On lui fournit les informations de dessin [g] et les
                     * coordonnées du coin en haut à gauche.
                     */
                    paint(g, Modeles.getZones(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
                }
            }
        }
        /**
         * Fonction auxiliaire de dessin d'une cellule.
         * Ici, la classe [Zones] ne peut être désignée que par l'intermédiaire
         * de la classe [Island] à laquelle elle est interne, d'où le type
         * [Island.Zones].
         * Ceci serait impossible si [Zones] était déclarée privée dans [Island].
         */
        private void paint(Graphics g, Zones c, int x, int y) {
            /** Sélection d'une couleur. */
            if (c.estVivante()) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            /** Coloration d'un rectangle. */
            g.fillRect(x, y, TAILLE, TAILLE);
        }
    }

}
