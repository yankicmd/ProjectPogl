package Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modeles.Artifact;
import Modeles.Keys;
import Modeles.Player;
import Modeles.Objet;
import Modeles.Zones;

public class CollectArtifact extends Playeraction implements ActionListener {

    private final static int KeyssMinimum = 2; // A modifier selon l'envie

    public CollectArtifact(Player j) {
        super(j);
    }

    public void donnerArtifact(Player j) {
        Zones z = j.getZones();
        if (z.getElement() == Modeles.Element.Neutre) {
            System.out.println("Cette Zones ne contient pas d'Artifact !");
        }
        System.out.println("Test");
        if (this.KeyssMinimum <= j.combienCles(z.getElement())) {
            j.recupereArtifact(new Artifact(z.getElement()));
            j.enleveCles(new Keys(z.getElement()), KeyssMinimum);
            this.notifyObservers();
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}
