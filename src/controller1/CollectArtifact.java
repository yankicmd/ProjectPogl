package controller1;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import modele1.Artifact;
import modele1.Keys;
import modele1.Player;
import modele1.Objet;
import modele1.Zone;

public class CollectArtifact extends ActionPlayer implements ActionListener  {

    //Attributs
    //'KeyssMinimum' un entier correpondant au nombre de Keyss d'un element minimum que doit avoir un Player pour recuperer un Artifact de cet element.
    private final static int KeyssMinimum = 2; // A modifier selon l'envie

    //Constructeur
    /**CollectArtifact() :
     * Player j : un Player.
     * 		Definit j comme etant le Player concerne par l'action de ramasser un Artifact.
     **/
    public CollectArtifact(Player j) {
        super(j);
    }

    //Methodes
    /**void donnerArtifact() :
     * 		Getter pour acceder au nombre de cle (pour afficher ds l'interface)
     **/
    public static int getNbKeys() {
        return KeyssMinimum;
    }

    /**void donnerArtifact() :
     * 		Player j : un Player.
     * 		Donne un Artifact de l'element de la zone sur laquelle se trouve j si il a le nombre de Keyss de cet element minimum requis.
     **/
    public void donnerArtifact() {
        Zone z = j.getZone();
        if (z.getElement() == modele1.Element.Neutre) {
            System.out.println("Cette zone ne contient pas d'Artifact !");
        }
        if (this.KeyssMinimum <= j.combienCles(z.getElement())) {
            j.recupereObjet(new Artifact(z.getElement()));
            j.enleveCles(new Keys(z.getElement()), KeyssMinimum);

            this.incrNbAction();
            this.notifyObservers();
            j.getIsland().notifyObservers(); // Pour l'affichage d'inventaire.
        }
    }

    //Methode propre a l'interface ActionListener mais non utilisee ici.
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}

