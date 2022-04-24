<<<<<<< HEAD
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
// pas encore implemente un image
import java.io.FIsland;
import java.io.IOException;

import javax.imageio.ImageIO;
=======
package View;
import java.awt.FlowLayout;

>>>>>>> github/Project
import javax.swing.JFrame;

import Modeles.Island;

public class Mview {
<<<<<<< HEAD
    /**
     * JFrame est une classe fournie pas Swing. Elle représente la fenêtre
     * de l'application graphique.
     */
    private JFrame frame;
    /**
     * ViewGrid et ViewCommands sont deux classes définies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private ViewGrid grille;
    private ViewCommands commandes;

    /** Construction d'une vue attachée à un modèle. 
     * @throws IOException */
    public CVue(Island modele) throws IOException {
        /** Définition de la fenêtre principale. */
        frame = new JFrame();
        frame.setTitle("Island Interdite");
        frame.setIconImage(ImageIO.read(new FIsland("res/Icone.png")));
        frame.setLayout(new BorderLayout());

        /** Définition des deux vues et ajout à la fenêtre. */
        grille = new ViewGrid(modele);
        frame.add(grille);
        commandes = new ViewCommands(modele);
        frame.add(commandes,BorderLayout.SOUTH);
        /**
         * Fin de la plomberie :
         *  - Ajustement de la taille de la fenêtre en fonction du contenu.
         *  - Indiquer qu'on quitte l'application si la fenêtre est fermée.
         *  - Préciser que la fenêtre doit bien apparaître à l'écran.
         */
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
=======
        /**
         * JFrame est une classe fournie pas Swing. Elle représente la fenêtre
         * de l'application graphique.
         */
        private JFrame frame;
        /**
         * VueGrille et VueCommandes sont deux classes définies plus loin, pour
         * nos deux parties de l'interface graphique.
         */
        private VueGrille grille;
        private VueCommandes commandes;

        /** Construction d'une vue attachée à un modèle. */
        public CVue(Island Modeles) {
            /** Définition de la fenêtre principale. */
            frame = new JFrame();
            frame.setTitle("Island Interdite");
            /**
             * On précise un mode pour disposer les différents éléments à
             * l'intérieur de la fenêtre. Quelques possibilités sont :
             *  - BorderLayout (défaut pour la classe JFrame) : chaque élément est
             *    disposé au centre ou le long d'un bord.
             *  - FlowLayout (défaut pour un JPanel) : les éléments sont disposés
             *    l'un à la suite de l'autre, dans l'ordre de leur ajout, les lignes
             *    se formant de gauche à droite et de haut en bas. Un élément peut
             *    passer à la ligne lorsque l'on redimensionne la fenêtre.
             *  - GridLayout : les éléments sont disposés l'un à la suite de
             *    l'autre sur une grille avec un nombre de lignes et un nombre de
             *    colonnes définis par le programmeur, dont toutes les cases ont la
             *    même dimension. Cette dimension est calculée en fonction du
             *    nombre de cases à placer et de la dimension du contenant.
             */
            frame.setLayout(new FlowLayout());

            /** Définition des deux vues et ajout à la fenêtre. */
            grille = new VueGrille(Modeles);
            frame.add(grille);
            commandes = new VueCommandes(Modeles);
            frame.add(commandes);
            /**
             * Remarque : on peut passer à la méthode [add] des paramètres
             * supplémentaires indiquant où placer l'élément. Par exemple, si on
             * avait conservé la disposition par défaut [BorderLayout], on aurait
             * pu écrire le code suivant pour placer la grille à gauche et les
             * commandes à droite.
             *     frame.add(grille, BorderLayout.WEST);
             *     frame.add(commandes, BorderLayout.EAST);
             */

            /**
             * Fin de la plomberie :
             *  - Ajustement de la taille de la fenêtre en fonction du contenu.
             *  - Indiquer qu'on quitte l'application si la fenêtre est fermée.
             *  - Préciser que la fenêtre doit bien apparaître à l'écran.
             */
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

>>>>>>> github/Project
}
