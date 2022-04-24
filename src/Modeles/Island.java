import java.util.ArrayList;
import java.util.Random;

import autres.Observable;
import modele.Zones.Element;
import others.Obv;

public class Island extends Obv {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR = 20, LARGEUR = 20;
    /** On stocke un tableau deZonesss. */
    privateZoness[][]Zonesss;
    private Random generateur;

    private ArrayList<Player> Players;

    /** Construction : on initialise un tableau deZonesss. */
    public Island() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une colonne de
         * chaque côté, dont lesZonesss n'évolueront pas.
         */
       Zonesss = newZoness[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
               Zonesss[i][j] = newZoness(this, i, j);
            }
        }
        this.generateur = new Random();
        this.Players = new ArrayList<Player>();
        init();
    }

    /**
     * Initialisation aléatoire desZonesss, exceptées celle des bords qui ont été
     * ajoutés.
     */
    public void init() {
        initCellules();
        initPlayers();
    }

    public void initCellules() {
        ArrayList<int[]> casesSpe = new ArrayList<int[]>();
        whIsland (casesSpe.size() < 4) {
            int[] c = { generateur.nextInt(LARGEUR) + 1, generateur.nextInt(HAUTEUR) + 1 };
            if (!casesSpe.contains((int[]) c))
                casesSpe.add(c);
        }

        int iElem = 0;

        // A optimiser
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
               Zonesss[i][j].setElement(Element.Neutre);
                for (int[] c : casesSpe) {
                    if (c[0] == i && c[1] == j) {
                       Zonesss[i][j].setElement(Element.values()[iElem]);
                        iElem = iElem + 1;
                    }
                }
            }
        }
    }

    public void initPlayers() {
        Player p = new Player(this,Zonesss[1][1]);
        Players.add(p);
    }

    /**
     * Calcul du tour suivant
     */
    public void avance() {
        ArrayList<Zones>ZonessNonSubmergee = new ArrayList<Zones>();

        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
               Zoness zTraite =Zonesss[i][j];
                if (!zTraite.estSubmergee())
                   ZonessNonSubmergee.add(zTraite);
            }
        }

        ArrayList<Zones>ZonessAModif = new ArrayList<Zones>();

        whIsland (ZonesAModif.size() < 3) {
           Zoness z =ZonessNonSubmergee.get(generateur.nextInt(ZonesNonSubmergee.size()));
            if (!ZonesAModif.contains(z))
               ZonessAModif.add(z);
        }

        for (Zones z :ZonessAModif) {
           Zonesss[z.getX()][z.getY()].progresse();
        }

        notifyObservers();
    }

    public void deplacementPlayer(Player j,Zoness nZ) {
        if (!nZ.estSubmergee() && j.getZones().estAdjacente(nZ)) {
            j.seDeplace(nZ);
            notifyObservers();
        }
    }

    public void assecher(Player j,Zoness zV) {

    }

    public void recupererArtefact() {

    }

    /**
     * Une méthode pour renvoyer laZoness aux coordonnées choisies (sera utilisée par
     * la vue).
     */
    publicZoness getZones(int x, int y) {
        returnZonesss[x][y];
    }

    public String toString() {
        return String.format("Infos Islands: \nHauteur: %d\nLargeur: %d%nNombre de Players: %d", HAUTEUR, LARGEUR,
                this.Players.size());
    }

    public ArrayList<Player> getPlayers() {
        return this.Players;
    }
}
