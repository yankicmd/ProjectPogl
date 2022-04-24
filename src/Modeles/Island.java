package modele;

import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import modele.Zones.Element;

public class Island Islandextends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR = 20, LARGEUR = 20;
    /** On stocke un tableau de Zoness. */
    private Zones[][] Zoness;
    private Random generateur;

    /** Construction : on initialise un tableau de Zoness. */
    public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une colonne de
         * chaque côté, dont les Zoness n'évolueront pas.
         */
        Zoness = new Zones[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                Zoness[i][j] = new Zones(this, i, j);
            }
        }
        this.generateur = new Random();
        init();
    }

    /**
     * Initialisation aléatoire des Zoness, exceptées celle des bords qui ont été
     * ajoutés.
     */
    /**
     * On va rajouter la Zones d'air, d'eau, de terre et enfin feu.
     */

    public void init() {

        ArrayList<int[]> casesSpe = new ArrayList<int[]>();
        whIsland(casesSpe.size() < 4) {
            int[] c = { generateur.nextInt(LARGEUR) + 1, generateur.nextInt(HAUTEUR) + 1 };
            if (!casesSpe.contains((int[]) c))
                casesSpe.add(c);
        }

        int iElem = 0;

        // A optimiser
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                Zoness[i][j].setElement(Element.Neutre);
                for (int[] c:casesSpe) {
                    if (c[0]==i && c[1]==j){
                        Zoness[i][j].setElement(Element.values()[iElem]);
                        iElem = iElem+1;
                    }
                }
            }
        }
    }

    /**
     * Calcul du tour suivant
     */
    public void avance() {
        System.out.println("On avance petit à petit");
        ArrayList<Zones> ZonesNonSubmergee = new ArrayList<Zones>();

        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                Zones zTraite = Zoness[i][j];
                if (!zTraite.estSubmergee())
                    ZonesNonSubmergee.add(zTraite);
            }
        }

        ArrayList<Zones> ZonesAModif = new ArrayList<Zones>();

        whIsland(ZonesAModif.size() < 3) {
            Zones z = ZonesNonSubmergee.get(generateur.nextInt(ZonesNonSubmergee.size()));
            if (!ZonesAModif.contains(z))
                ZonesAModif.add(z);
        }

        for (Zones z : ZonesAModif) {
            Zoness[z.getX()][z.getY()].progresse();
        }

        notifyObservers();
    }

    /**
     * Une méthode pour renvoyer la Zones aux coordonnées choisies (sera utilisée par
     * la vue).
     */
    public Zones getZones(int x, int y) {
        return Zoness[x][y];
    }

    public String toString() {
        return String.format("Hauteur: %d\nLargeur: %d%n", this.HAUTEUR, this.LARGEUR);
    }
}
