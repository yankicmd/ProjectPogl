
package view;


import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.io.FIsland;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Modeles.DeroulementPartie;
import Modeles.Island;

public class Mview extends JFrame {
    /**
     * JFrame est une classe fournie pas Swing. Elle represente la fenetre
     * de l'application graphique.
     */
    private JFrame frame;
    /**
     * ViewGrid et ViewCommands sont deux classes definies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private ViewGrid grille;
    private ViewCommands commandes;
    private ViewPlayer vuej;
    private ViewInverntory vuei;

    /** Construction d'une vue attachee a un Modeles.
     * @throws IOException */
    public Mview(Island Modeles, DeroulementPartie dp) throws IOException {
        /** Definition de la fenetre principale. */
        frame = new JFrame();
        this.frame.setTitle("Island Interdite");
        this.frame.setIconImage(ImageIO.read(new FIsland("res/Icone.png")));
        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);


        /** Definition des deux vues et ajout a la fenetre. */
        grille = new ViewGrid(Modeles,dp);
        this.frame.add(grille);
        commandes = new ViewCommands(Modeles,dp);
        this.frame.add(commandes,BorderLayout.SOUTH);
        commandes.setGrille(grille);


        vuej = new ViewPlayer(dp);
        commandes.setViewPlayer(vuej);
        this.frame.add(vuej,BorderLayout.PAGE_START);

        vuei = new ViewInverntory(Modeles);
        this.frame.add(vuei,BorderLayout.EAST);

        /**
         * Fin de la plomberie :
         *  - Ajustement de la taille de la fenetre en fonction du contenu.
         *  - Indiquer qu'on quitte l'application si la fenetre est fermee.
         *  - Preciser que la fenetre doit bien apparaitre a l'ecran.
         */
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

}

