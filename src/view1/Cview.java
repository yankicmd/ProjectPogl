package view1;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele1.DeroulementPartie;
import modele1.Island;

public class Cview extends JFrame {
    //Attributs
    /**
     * JFrame est une classe fournie pas Swing. Elle represente la fenetre
     * de l'application graphique.
     */
    //'frame' une frame de la classe JFrame.
    private JFrame frame;
    /**
     * ViewGrille et ViewCommandes sont deux classes definies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    //'grille' une ViewGrille.
    private ViewGrid grille;
    //'commandes' une ViewCommandes.
    private ViewCommands commandes;
    //'Viewj' une ViewPlayer.
    private ViewPlayer Viewj;
    //'Viewi' une ViewInventaire.
    private ViewInventory Viewi;

    //Constructeur
    /**
     * @throws IOException
     **/
    /**CView() :
     * 		Ile modele : une ile.
     * 		DeroulementPartie dp : une partie.
     * 		Construction d'une View attachee a un modele. 
     **/
    public Cview(Island modele, DeroulementPartie dp) throws IOException {
        //Definition de la fenetre principale.
        frame = new JFrame();
        this.frame.setTitle("Ile Interdite");
        this.frame.setIconImage(ImageIO.read(new File("res/Icone.png")));
        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);


        //Definition des Views et ajout a la fenetre.
        grille = new ViewGrid(modele,dp);
        this.frame.add(grille);
        commandes = new ViewCommands(modele,dp);
        commandes.setView(this);
        this.frame.add(commandes,BorderLayout.SOUTH);
        commandes.setGrille(grille);


        Viewj = new ViewPlayer(dp);
        commandes.setViewPlayer(Viewj);
        this.frame.add(Viewj,BorderLayout.PAGE_START);

        Viewi = new ViewInventory(modele);
        this.frame.add(Viewi,BorderLayout.EAST);

        //Fin de la plomberie :
        // - Ajustement de la taille de la fenetre en fonction du contenu.
        // - Indiquer qu'on quitte l'application si la fenetre est fermee.
        // - Preciser que la fenetre doit bien apparaitre a l'ecran.
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    //Ecran de victoire
    public void ecranVictoire() {
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
        //this.frame.add(new EcranDeFin("Victoire"));
        ImageIcon icone = new ImageIcon("res/feuxArtifices.gif");
        JLabel image = new JLabel(icone);
        this.frame.add(image);
        this.frame.setVisible(true);
    }

    //Ecran de defaite
    public void ecranDefaite() throws IOException {
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
        //this.frame.add(new EcranDeFin("Defaite"));
        ImageIcon icone = new ImageIcon("res/defaite.gif");
        JLabel image = new JLabel(icone);
        this.frame.add(image);
        this.frame.setVisible(true);

    }
}


