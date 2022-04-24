package Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import Modeles.Artifact;
import Modeles.Keys;
import Modeles.Island;
import Modeles.Player;
import Modeles.Zones;
import Modeles.Island.AccesHorsIsland;
import Modeles.Zones.Element;


public class CollectArtifact extends Playeractions implements ActionListener{

    public CollectArtifact(Player j) {
        super(j);
        // TODO Auto-generated constructor stub
    }

    //Pour pouvoir ramasser un Artifact il faut etre sur la bonne case et avoir la bonne cle
    public void donnerArtifact(Player j) {
        Zones z = j.getZones();
        if (z.getElement() == Zones.Element.Neutre) {
            System.out.println("Cette Zones ne contient pas d'Artifact !");
        }
        if ((z.getElement() == Zones.Element.Air) && (j.verifCle(Zones.Element.Air))) {
            j.recupereArtifact(new Artifact(Element.Air));
            System.out.println("Un Artifact de type Air a ete ramasser !");
        }
        if ((z.getElement() == Zones.Element.Eau) && (j.verifCle(Zones.Element.Eau))) {
            j.recupereArtifact(new Artifact(Element.Eau));
            System.out.println("Un Artifact de type Eau a ete ramasser !");
        }
        if ((z.getElement() == Zones.Element.Terre) && (j.verifCle(Zones.Element.Terre))) {
            j.recupereArtifact(new Artifact(Element.Terre));
            System.out.println("Un Artifact de type Terre a ete ramasser !");
        }
        if ((z.getElement() == Zones.Element.Feu) && (j.verifCle(Zones.Element.Feu))){
            j.recupereArtifact(new Artifact(Element.Feu));
            System.out.println("Un Artifact de type Feu a ete ramasser !");
        }
    }



    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}



