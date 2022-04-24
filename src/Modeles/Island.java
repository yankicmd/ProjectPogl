<<<<<<< HEAD
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
=======
package Modeless;

import others.Obv;

public class Island { extends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR = 40, LARGEUR = 60;
    /** On stocke un tableau de Zonessss. */
    private Zoness[][] Zonesss;

    /** Construction : on initialise un tableau de Zonesss. */
        public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une colonne de
         * chaque côté, dont les Zonesss n'évolueront pas.
         */
        Zonesss = new Zoness[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                Zonesss[i][j] = new Zoness(this, i, j);
            }
        }
>>>>>>> github/Project
        init();
    }

    /**
<<<<<<< HEAD
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
=======
     * Initialisation aléatoire des Zonesss, exceptées celle des bords qui ont été
     * ajoutés.
     */
    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                if (Math.random() < .2) {
                    Zonesss[i][j].etat = true;
>>>>>>> github/Project
                }
            }
        }
    }

    /**
<<<<<<< HEAD
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
=======
     * Calcul de la génération suivante.
     */
    public void avance() {
        /**
         * On procède en deux étapes. - D'abord, pour chaque Zoness on évalue ce que
         * sera son état à la prochaine génération. - Ensuite, on applique les
         * évolutions qui ont été calculées.
         */
        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                Zonesss[i][j].evalue();
            }
        }
        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                Zonesss[i][j].evolue();
            }
        }
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs qu'ils
         * doivent se mettre à jour.
         */
        notifyObvs();
    }

    /**
     * Méthode auxiliaire : compte le nombre de voisines vivantes d'une Zoness
     * désignée par ses coordonnées.
     */
    protected int compteVoisines(int x, int y) {
        int res = 0;
        /**
         * Stratégie simple à écrire : on compte les Zonesss vivantes dans le carré 3x3
         * centré autour des coordonnées (x, y), puis on retire 1 si la Zoness centrale
         * est elle-même vivante. On n'a pas besoin de traiter à part les bords du
         * tableau de Zonesss grâce aux lignes et colonnes supplémentaires qui ont été
         * ajoutées de chaque côté (dont les Zonesss sont mortes et n'évolueront pas).
         */
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (Zonesss[i][j].etat) {
                    res++;
                }
            }
        }
        return (res - ((Zonesss[x][y].etat) ? 1 : 0));
        /**
         * L'expression [(c)?e1:e2] prend la valeur de [e1] si [c] vaut [true] et celle
         * de [e2] si [c] vaut [false]. Cette dernière ligne est donc équivalente à int
         * v; if (Zonesss[x][y].etat) { v = res - 1; } else { v = res - 0; } return v;
         */
    }

    /**
     * Une méthode pour renvoyer la Zoness aux coordonnées choisies (sera utilisée
     * par la vue).
     */
    public Zoness getZoness(int x, int y) {
        return Zonesss[x][y];
    }
}

>>>>>>> github/Project
}
