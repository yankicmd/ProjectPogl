
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FIsland;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Modeles.Island;

public class Mview {
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
    public Mview(Island Modeles) throws IOException {
        /** Définition de la fenêtre principale. */
        frame = new JFrame();
        frame.setTitle("Island Interdite");
        frame.setIconImage(ImageIO.read(new FIsland("res/Icone.png")));
        frame.setLayout(new BorderLayout());

        /** Définition des deux vues et ajout à la fenêtre. */
        grille = new ViewGrid(Modeles);
        frame.add(grille);
        commandes = new ViewCommands(Modeles);
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
}
