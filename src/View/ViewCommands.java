package View;

import javax.swing.JButton;
import javax.swing.JPanel;

import Modeless.Island;
import Controller.Controller;

class ViewCommands extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une référence au
     * modèle.
     */
    private Island Modeless;

    /** Constructeur. */
    public ViewCommands(Island Modeless) {
        this.Modeless = Modeless;
        /**
         * On crée un nouveau bouton, de classe [JButton], en précisant le texte qui
         * doit l'étiqueter. Puis on ajoute ce bouton au panneau [this].
         */
        /**
         * Le bouton, lorsqu'il est cliqué par l'utilisateur, produit un événement, de
         * classe [ActionEvent].
         *
         * On a ici une variante du schéma observateur/observé : un objet implémentant
         * une interface [ActionListener] va s'inscrire pour "écouter" les événements
         * produits par le bouton, et recevoir automatiquements des notifications.
         * D'autres variantes d'auditeurs pour des événements particuliers :
         * [MouseListener], [KeyboardListener], [WindowListener].
         *
         * Cet observateur va enrichir notre schéma Modèle-Vue d'une couche
         * intermédiaire Contrôleur, dont l'objectif est de récupérer les événements
         * produits par la vue et de les traduire en instructions pour le modèle. Cette
         * strate intermédiaire est potentiellement riche, et peut notamment traduire
         * les mêmes événements de différentes façons en fonction d'un état de
         * l'application. Ici nous avons un seul bouton réalisant une seule action,
         * notre contrôleur sera donc particulièrement simple. Cela nécessite néanmoins
         * la création d'une classe dédiée.
         */
        /**
         * Variante : une lambda-expression qui évite de créer une classe spécifique
         * pour un contrôleur simplissime.
         */
        JButton boutonFinDuTour = new JButton("Fin du tour");
        this.add(boutonFinDuTour);
        boutonFinDuTour.addActionListener(e -> {
            Modeless.avance();
        });
        JButton test = new JButton("Testage");
        this.add(test);
        test.addActionListener(e-> { Modeless.Playermovement(Modeless.getPlayers().get(0), Modeless.getZones(2, 2)); });
    }
}

