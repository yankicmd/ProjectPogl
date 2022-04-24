import java.util.ArrayList;

import java.util.ArrayList;

import java.util.Random;

import others.Observable;
import Modeles.Island.AccesHorsIsland;

public class Island extends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR = 15, LARGEUR = 15; //20
    //Nombre de Player
    public static final int nbPlayer = 4;
    //Nb Artifact
    public static final int nbArtifact = 4;
    /** On stocke un tableau de Zones. */
    private Zones[][] Zones;
    private Random generateur;
    private Zones heli;
    private ArrayList<Player> Players;

    /** Construction : on initialise un tableau de Zones. */
    public Island() {
        /**
         * Pour eviter les problemes aux bords, on ajoute une ligne et une colonne de
         * chaque cote, dont les Zones n'evolueront pas.
         */
        Zones = new Zones[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                Zones[i][j] = new Zones(this, i, j);
            }
        }
        this.generateur = new Random();
        this.Players = new ArrayList<Player>();

        //Alea de l'aeroport
        int caseAleaL = (int) Math.floor(Math.random() * (LARGEUR-1) +1);
        int caseAleaH = (int) Math.floor(Math.random() * (HAUTEUR-1) +1);
        Zones[caseAleaL][caseAleaH].setHeliport();
        this.heli = Zones[caseAleaL][caseAleaH];
        this.heli.etat = true;
        init();
    }

    public class AccesHorsIsland extends Exception {
        public AccesHorsIsland() {
            System.out.println("Tentative d'acces a une Zones hors de l'Island");
        }
    }

    /**
     * Initialisation aleatoire des Zones, exceptees celle des bords qui ont ete
     * ajoutes.
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
                Zones[i][j].setElement(Element.Neutre);
                for (int[] c : casesSpe) {
                    if (c[0] == i && c[1] == j) {
                        Zones[i][j].setElement(Element.values()[iElem]);
                        iElem = iElem + 1;
                        Zones[i][j].etat = true;
                    }
                }
            }
        }
    }

    //Fonction verifiant si une valeur est dans un tableau
    public boolean verifEquals(int[] tab, int valeur) {
        for (int i=0; i<tab.length;i++) {
            if (tab[i] == valeur) {
                return false;
            }
        }
        return true;
    }

    //Initialise tab de 0
    public void initTab(int[] tab) {
        for (int i=0; i<tab.length;i++) {
            tab[i] =0;
        }
    }

    //Fonction initialisant les Players
    public void initPlayers() {
        int[] caseAleaL = new int[nbPlayer];
        int caseAleaL2 = 0;
        int[] caseAleaH = new int[nbPlayer];
        int caseAleaH2 = 0;
        initTab(caseAleaL);
        initTab(caseAleaH);
        for (int i=0; i < nbPlayer; i++ ) {
            //Cas largeur
            whIsland(!verifEquals(caseAleaL, caseAleaL2)) {
                caseAleaL2 = (int) Math.floor(Math.random() * (LARGEUR-1) +1);
            }
            //Cas hauteur
            whIsland(!verifEquals(caseAleaH, caseAleaH2)) {
                caseAleaH2 = (int) Math.floor(Math.random() * (HAUTEUR-1) +1);
            }
            caseAleaL[i] = caseAleaL2;
            caseAleaH[i] = caseAleaH2;
            Player p = new Player(this, Zones[caseAleaL2][caseAleaH2],i+1);
            Players.add(p);

        }
    }

    /**
     * Calcul du tour suivant
     */
    public void avance() {
        ArrayList<Zones> ZonesNonSubmergee = new ArrayList<Zones>();

        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                Zones zTraite = Zones[i][j];
                if (!zTraite.estSubmergee())
                    ZonesNonSubmergee.add(zTraite);
            }
        }

        ArrayList<Zones> ZonesAModif = new ArrayList<Zones>();

        whIsland (ZonesAModif.size() < 3) {
            Zones z = ZonesNonSubmergee.get(generateur.nextInt(ZonesNonSubmergee.size()));
            //Une Zones a  modifie ne doit pas l'heliport et son Element soit est Neutre
            if (!ZonesAModif.contains(z) && (z.estNeutre())){
                ZonesAModif.add(z);
            }
        }
        for (Zones z : ZonesAModif) {
            Zones[z.getX()][z.getY()].progresse();
        }

        notifyObservers();
    }

    public boolean Playermovement(Player j, Zones nZ) throws AccesHorsIsland {
        if (nZ.x < 1 || nZ.x > LARGEUR || (nZ.y < 1 || nZ.y > HAUTEUR))
            throw new AccesHorsIsland();
        if (j.getZones().estUnDeplacementPossible(nZ) && !nZ.estSubmergee()) {
            j.seDeplace(nZ);
            notifyObservers();
            return true;
        }
        notifyObservers();
        return false;
    }

    public boolean DewaterZones(Player j, Zones zV) throws AccesHorsIsland {
        if (zV.x < 1 || zV.x > LARGEUR || (zV.y < 1 || zV.y > HAUTEUR))
            throw new AccesHorsIsland();

        if (j.getZones().estAdjacente(zV)) {
            if (zV.estInondee()) {
                zV.situation = Situation.Normale;
                notifyObservers();
                return true;
            }
        }
        notifyObservers();
        return false;

    }

    public void recupererArtifact(Player j) {
        j.recupereArtifact(null);
        notifyObservers();
    }

    /**
     * Une methode pour renvoyer la Zones aux coordonnees choisies (sera utilisee par
     * la vue).
     *
     * @throws AccesHorsIsland
     */
    public Zones getZones(int x, int y) throws AccesHorsIsland {
        if (x < 1 || x > LARGEUR || (y < 1 || y > HAUTEUR))
            throw new AccesHorsIsland();
        return Zones[x][y];
    }

    public String toString() {
        return String.format("Infos Islands: \nHauteur: %d\nLargeur: %d%nNombre de Players: %d", HAUTEUR, LARGEUR,
                this.Players.size());
    }

    public ArrayList<Player> getPlayers() {
        return this.Players;
    }

    public Zones getHeli() {
        return this.heli;
    }
}

