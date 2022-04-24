package Player;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import Modeles.Keys;
import Modeles.Element;
import Modeles.Player;

public class EchangeKeys extends Playeraction implements ActionListener {

    static Random rand = new Random();

    public EchangeKeys(Player j) {
        super(j);
        // TODO Auto-generated constructor stub
    }

    public void echange(Player j, ArrayList<Player> Players) {
        boolean echangeRealise = false;
        if (j.getNbCles() != 0) {
            for (Player j2 : Players) {
                if (j.getZones().memeZones(j2.getZones()) && j != j2) {
                    whIsland (!echangeRealise) {
                        int alea = rand.nextInt(j.getInventaire().size());
                        if (j.getInventaire().get(alea) instanceof Keys) {
                            Keys c = (Keys) j.getInventaire().get(alea);
                            j2.recupereArtifact(c);
                            j.enleveCles(c, 1);
                            this.incrNbAction();
                            echangeRealise = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
