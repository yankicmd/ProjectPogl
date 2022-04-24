package view1;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import others1.Observer;
import modele1.Keys;
import modele1.Artifact;
import modele1.Island;
import modele1.Player;
import modele1.Objet;

public class ViewInventory extends JPanel implements Observer{

    //Attributs

    //'modele' une ile.
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
     * modele.
     */
    Island modele;
    //'labelInventaire' un label.
    private JLabel labelInventaire;
    //'PlayersList' une liste de Players.
    private ArrayList<JLabel> PlayersList;
    //'WIDTH' un entier correspondant a une largeur.
    private final int WIDTH = 30 * modele.HAUTEUR; //50
    //'HEIGHT' un entier correspondant a une hauteur.
    private final int HEIGHT = 10 * modele.LARGEUR; //40
    //'cote' un entier correspondant au cote d'un morceau d'interface.
    private final int cote = modele.LARGEUR*4;
    //'espacement' un entier correspondant a l'espacement entre deux inventaires.
    private final int espacement = cote/2;
    //'marge' un entier correspondant a une marge.
    private static int marge;


    //Constructeur

    /**ViewInventaire() :
     * 		Ile modele : une ile.
     * 		 Construction d'une View de l'inventaire attachee a un modele.
     **/
    public ViewInventory(Island mod) throws IOException {
        this.modele = mod;
        this.setLayout(null);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);
        this.modele.addObserver(this);

        //Definition du titre de la View des inventaires des Players.
        this.labelInventaire = new JLabel("Inventaires des Players");
        this.add(labelInventaire);
        Dimension size = this.labelInventaire.getPreferredSize();
        this.labelInventaire.setBounds(WIDTH/2, HEIGHT/20, size.width, size.height);
        this.labelInventaire.setVisible(true);
        marge = this.labelInventaire.getPreferredSize().width + WIDTH / 20;

        //Definition des titres pour chaque inventaire des Players.
        this.PlayersList = new ArrayList<JLabel>();
        for(int i=0; i < this.modele.getPlayers().size(); i++) {
            this.PlayersList.add(new JLabel("Inventaire du Player "+Integer.toString(i+1)));
            this.add(PlayersList.get(i));
            this.PlayersList.get(i).setBounds(cote, 2*espacement*(i+1), size.width, size.height);
        }
    }

    //Methodes	
    public void update() {
        this.getParent().repaint();
        repaint();
    }

    /**void paintInventairePlayer() :
     * 		Graphics g : un graphique.
     * 		Player j : un Player.
     * 		Paint l'inventaire d'un Player.
     **/
    public void paintInventairePlayer(Graphics g, Player j) {
        int nbJ = modele.getPlayers().indexOf(j);
        if (!j.getInventaire().isEmpty()) {
            for (int i = 0; i < j.getInventaire().size();i++) {
                Objet o = j.getInventaire().get(i);
                String chemin = "res/"+o.getClass().getSimpleName()+"/"+o.getElement().name()+".png";
                try {
                    Image img = ImageIO.read(new File(chemin));
                    g.drawImage(img,cote+32*i,2*espacement*(nbJ+1)+espacement/2,this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**void paintComponent() :
     *
     * 		Graphics g : un graphique.
     *
     * 		Paint les inventaires de tout les Players.
     **/
    public void paintComponent(Graphics g) {
        for (Player j:this.modele.getPlayers()) {
            paintInventairePlayer(g,j);
        }
    }
}

