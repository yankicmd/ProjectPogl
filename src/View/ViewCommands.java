package View;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import Others.Observer;
import Modeles.DeroulementPartie;
import Modeles.Island;
import Modeles.Player;
import Controller.Playeractions;
import Controller.Controleur;
import Controller.Playermovement;
import Controller.EchangeKeys;
import Controller.CollectArtifact;

class ViewCommands extends JPanel implements Observer {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une reference au
     * Modeles.
     */
    private Island Modeles;
    private DeroulementPartie liaison;
    private ViewGrid vg;
    private ViewPlayer vj;
    private CollectArtifact ra;
    private EchangeKeys ea;

    public ViewCommands(Island Modeles, DeroulementPartie dp) {
        this.Modeles = Modeles;
        this.liaison = dp;
        this.ra = new CollectArtifact(liaison.getPlayer());
        this.ea = new EchangeKeys(liaison.getPlayer());
        liaison.addObserver(this);

        JButton boutonFinDuTour = new JButton("Fin du tour");

        boutonFinDuTour.addActionListener(e -> {
            vg.removeMouseListener(liaison.getAJActuel().getPlayermovement());
            vg.removeMouseListener(liaison.getAJActuel().getaZ());
            liaison.prochainPlayer();
            vg.addMouseListener(liaison.getAJActuel().getPlayermovement());
            vg.addMouseListener(liaison.getAJActuel().getaZ());
            Modeles.avance();
            liaison.giveAleaClefs();
            vj.update();

            if (this.liaison.gagne()) {
                System.out.println(" La partie est gagne !");
            }
        });

        JButton recupArte = new JButton("Recuperer un artefact");
        recupArte.addActionListener(e -> {
            if (liaison.getAJActuel().estLibre()) {
                ra.donnerArtefact(liaison.getPlayer());
            }
        });

        JButton echangeArte = new JButton("Donner une cle");
        echangeArte.addActionListener(e -> ea.echange(liaison.getPlayer(), Modeles.getPlayers()));

        this.add(echangeArte);
        this.add(recupArte);
        this.add(boutonFinDuTour);
    }

    // CONTROLEUR --> Island --> Player

    public void setGrille(ViewGrid vg) {
        this.vg = vg;
    }

    public void setViewPlayer(ViewPlayer vj) {
        this.vj = vj;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}
