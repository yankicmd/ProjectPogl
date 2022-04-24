package View;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FIsland;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import others.Observer;
import Modeles.Keys;
import Modeles.Artifact;
import Modeles.Island;
import Modeles.Player;
import Modeles.Objet;

public class ViewInverntory extends JPanel implements Observer {

    Island Modeles;
    private JLabel labelInventaire;
    private ArrayList<JLabel> PlayersList;
    private final int WIDTH = 50 * Modeles.HAUTEUR;
    private final int HEIGHT = 40 * Modeles.LARGEUR;
    private final int cote = Modeles.LARGEUR*4;
    private final int espacement = cote/2;
    private static int marge;

    /*
     * private BufferedImage cleEau = ImageIO.read(new FIsland("res/Waterkey.png"));
     * private BufferedImage Keyseu = ImageIO.read(new FIsland("res/Firekey.png"));
     * private BufferedImage cleAir = ImageIO.read(new FIsland("res/Windkey.png"));
     * private BufferedImage cleTerre = ImageIO.read(new FIsland("res/Earthkey.png"));
     *
     * private BufferedImage artEau = ImageIO.read(new FIsland("res/Waterart.png"));
     * private BufferedImage artFeu = ImageIO.read(new FIsland("res/Fireart.png"));
     * private BufferedImage artWind = ImageIO.read(new FIsland("res/Windart.png"));
     * private BufferedImage artTerre = ImageIO.read(new FIsland("res/Earthart.png"));
     */


    public ViewInverntory(Island mod) throws IOException {
        this.Modeles = mod;
        this.setLayout(null);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);
        this.Modeles.addObserver(this);

        this.labelInventaire = new JLabel("Inventaires des Players");
        this.add(labelInventaire);
        Dimension size = this.labelInventaire.getPreferredSize();
        this.labelInventaire.setBounds(WIDTH/2, HEIGHT/20, size.width, size.height);
        this.labelInventaire.setVisible(true);
        marge = this.labelInventaire.getPreferredSize().width + WIDTH / 20;

        this.PlayersList = new ArrayList<JLabel>();
        for(int i=0; i < this.Modeles.getPlayers().size(); i++) {
            this.PlayersList.add(new JLabel("Inventaire du Player "+Integer.toString(i+1)));
            this.add(PlayersList.get(i));
            this.PlayersList.get(i).setBounds(cote, espacement*(i+1), size.width, size.height);
        }

    }

    public void update() {

    }

    public void paintInventairePlayer(Graphics g, Player j) {
        if (!j.getInventaire().isEmpty()) {
            for (Objet o:j.getInventaire()) {
                String chemin = "res/"+o.getClass().getSimpleName()+"/"+o.getElement().toString()+".png";
                try {
                    Image img = ImageIO.read(new FIsland(chemin));
                    g.drawImage(img,32,32,this);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for (Player j:this.Modeles.getPlayers()) {
            paintInventairePlayer(g,j);
        }
    }


}


