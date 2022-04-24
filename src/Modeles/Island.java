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
        init();
    }

    /**
     * Initialisation aléatoire des Zonesss, exceptées celle des bords qui ont été
     * ajoutés.
     */
    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                if (Math.random() < .2) {
                    Zonesss[i][j].etat = true;
                }
            }
        }
    }

    /**
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

}
