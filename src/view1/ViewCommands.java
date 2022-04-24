package view1;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import others1.Observer;
import modele1.DeroulementPartie;
import modele1.Island;
import modele1.Player;
import controller1.ActionPlayers;
import controller1.Controleur;
import controller1.MovementPlayer;
import controller1.ExhangeKeys;
import controller1.CollectArtifact;

public class ViewCommands extends JPanel implements Observer {
    // Attributs

    // 'modele' une ile.
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
     * modele.
     */
    private Island modele;
    // 'liaison' une partie.
    private DeroulementPartie liaison;
    // 'vg' une ViewGrille.
    private ViewGrid vg;
    // 'vj' une ViewPlayer.
    private ViewPlayer vj;
    // 'ra' la capacite de ramasser un artefact.
    private CollectArtifact ra;
    // 'ea' la capacite d'echanger des clefs.
    private ExhangeKeys ea;

    //
    private Cview cv;
    // Constructeur

    /**
     * ViewCommandes() : Ile modele : une ile. DeroulementPartie dp : une partie.
     * Construction d'une View des commandes attachee a un modele.
     **/
    public ViewCommands(Island modele, DeroulementPartie dp) {
        this.modele = modele;
        this.liaison = dp;
        liaison.addObserver(this);
        JButton boutonFinDuTour = new JButton("Fin du tour");

        boutonFinDuTour.addActionListener(e -> {
            vg.removeMouseListener(liaison.getAJActuel().getMovementPlayer());
            vg.removeMouseListener(liaison.getAJActuel().getaZ());
            liaison.giveAleaKeys();
            liaison.prochainPlayer();
            vg.addMouseListener(liaison.getAJActuel().getMovementPlayer());
            vg.addMouseListener(liaison.getAJActuel().getaZ());
            modele.avance();

            vj.update();

            if (this.liaison.gagne()) {
                System.out.println(" La partie est gagne !");
                cv.ecranVictoire();
            }

            if (this.liaison.defaite()) {
                System.out.println(" La partie est perdue !");
                try {
                    cv.ecranDefaite();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton recupArte = new JButton("Recuperer un artefact");
        recupArte.addActionListener(e -> {
            if (liaison.getAJActuel().estLibre()) {
                dp.getAJActuel().getrA().donnerArtifact();
                ;
            }
        });

        JButton echangeArte = new JButton("Donner une cle");
        echangeArte.addActionListener(e -> {
            if (liaison.getAJActuel().estLibre()) {
                dp.getAJActuel().getExhangeKeys().echange();
            }
        });

        this.add(echangeArte);
        this.add(recupArte);
        this.add(boutonFinDuTour);
    }

    // Methodes
    // CONTROLEUR --> ILE --> Player

    /**
     * void setGrille() : ViewGrille vg : une ViewGrille. Donne la valeur de la
     * ViewGrille vg a l'attribut vg de la ViewCommandes.
     **/
    public void setGrille(ViewGrid vg) {
        this.vg = vg;
    }

    /**
     * void setViewPlayer() :
     *
     * ViewPlayer vj : une ViewPlayer.
     *
     * Donne la valeur de la ViewPlayer vj a l'attribut vj de la ViewCommandes.
     **/
    public void setViewPlayer(ViewPlayer vj) {
        this.vj = vj;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    public void setView(Cview cv) {
        this.cv = cv;
    }
}
