package modele1;

import java.util.ArrayList;

import java.util.Random;

import others1.Observab;
import modele1.Island.AccesHorsIsland;

public class Island extends Observab {
//Attributs

    //'HAUTEUR' un entier correspondant a la hauteur de la grille et 'LARGEUR' un entier correspondant a la largeur de la grille.
    public static final int HAUTEUR = 15, LARGEUR = 15;
    //'nbPlayer' un entier representant le nombre de Players de la partie.
    public static final int nbPlayer = 3;
    //'nbArtifact' un entier representant le nombre d'Artifact de la partie.
    public static final int nbArtifact = 4;
    //'zones' un tableau stockant des zones correspondant aux zones de l'Island.
    private Zone[][] zones;
    //'generateur' un generateur d'entiers aleatoires.
    private Random generateur;
    //'heli' une zone speciale correspondant a l'heliport.
    private Zone heli;
    //'Players' une liste de Players contenant les Players de la partie.
    private ArrayList<Player> players;

    //Constructeur
    /**Island() :
     * 		Creer tout d'abord un tableau de zone vide correspondant a l'attribut zones de l'Island puis creer une liste vide de Players.
     * 		On place ensuite l'heliport et on initialise la liste des Players et des zones.
     **/
    public Island() {
        /**
         * Pour eviter les problemes aux bords, on ajoute une ligne et une colonne de
         * chaque cote, dont les zones n'evolueront pas.
         */
        zones = new Zone[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                zones[i][j] = new Zone(this, i, j);
            }
        }
        this.generateur = new Random();
        this.players = new ArrayList<Player>();

        // Alea de l'aeroport
        int caseAleaL = (int) Math.floor(Math.random() * (LARGEUR - 1) + 1);
        int caseAleaH = (int) Math.floor(Math.random() * (HAUTEUR - 1) + 1);
        zones[caseAleaL][caseAleaH].setHeliport();
        this.heli = zones[caseAleaL][caseAleaH];
        this.heli.etat = true;
        init();
    }

    public class AccesHorsIsland extends Exception {

        //Constructeur
        /**AccesHorsIsland() :
         * 		Affiche un message si le Player essaye d'acceder a une zone hors de l'Island.
         **/
        public AccesHorsIsland() {
            System.out.println("Tentative d'acces a une zone hors de l'Island");
        }
    }
    //Methodes

    /**void init() :
     * Initialisation aleatoire des zones, exceptees celle des bords qui ont ete
     * ajoutes.
     */
    public void init() {
        initCellules();
        initPlayers();
    }

    /**void initCellules() :
     * 		Initialise aleatoirement les zones exceptees les zones des bords qui sont des surplus.
     **/
    public void initCellules() {
        ArrayList<int[]> casesSpe = new ArrayList<int[]>();
       while (casesSpe.size() < 4) {
            int[] c = { generateur.nextInt(LARGEUR) + 1, generateur.nextInt(HAUTEUR) + 1 };
            //on verifie que la case n'est pas deja dans l'arrayList et que l'abscisse est differente de celle de l'heli aisni que l'heliport
            if ((!casesSpe.contains((int[]) c)) && (c[0] != this.heli.x) && (c[1] != this.heli.y))
                casesSpe.add(c);
        }

        int iElem = 0;

        // A optimiser
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                zones[i][j].setElement(Element.Neutre);
                for (int[] c : casesSpe) {
                    if (c[0] == i && c[1] == j) {
                        zones[i][j].setElement(Element.values()[iElem]);
                        iElem = iElem + 1;
                        zones[i][j].etat = true;
                    }
                }
            }
        }
    }

    /**boolean verifEquals() :
     * 		int[] tab : un tableau d'entiers.
     * 		int valeur : un entier.
     * 		Return : True si valeur se trouve dans tab[], false sinon.
     **/
    public boolean verifEquals(int[] tab, int valeur) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == valeur) {
                return false;
            }
        }
        return true;
    }

    /**void initTab() :
     * 		int[] tab : un tableau d'entiers.
     * 		Initialise ou mets toutes les valeurs du tableau tab a 0.
     **/
    public void initTab(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }
    }

    /**void initPlayers() :
     *Initialise les Players de la partie,
     * les places de maniere aleatoire sur la carte
     * et les ajoute a la liste de Players de la partie
     **/
    public void initPlayers() {
        int[] caseAleaL = new int[nbPlayer];
        int caseAleaL2 = 0;
        int[] caseAleaH = new int[nbPlayer];
        int caseAleaH2 = 0;
        initTab(caseAleaL);
        initTab(caseAleaH);
        for (int i = 0; i < nbPlayer; i++) {
            // Cas largeur
           while (!verifEquals(caseAleaL, caseAleaL2)) {
                caseAleaL2 = (int) Math.floor(Math.random() * (LARGEUR - 1) + 1);
            }
            // Cas hauteur
           while (!verifEquals(caseAleaH, caseAleaH2)) {
                caseAleaH2 = (int) Math.floor(Math.random() * (HAUTEUR - 1) + 1);
            }
            caseAleaL[i] = caseAleaL2;
            caseAleaH[i] = caseAleaH2;
            Player p = new Player(this, zones[caseAleaL2][caseAleaH2]);
            players.add(p);

        }
    }

    /**void avance() :
     * Calcul du tour suivant
     */
    public void avance() {
        ArrayList<Zone> zoneNonSubmergee = new ArrayList<Zone>();

        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                Zone zTraite = zones[i][j];
                if (!zTraite.estSubmergee() && !zTraite.isHeliport())
                    zoneNonSubmergee.add(zTraite);
            }
        }

        ArrayList<Zone> zoneAModif = new ArrayList<Zone>();

       while (zoneAModif.size() < 3) {
            Zone z = zoneNonSubmergee.get(generateur.nextInt(zoneNonSubmergee.size()));
            // Une zone a modifie ne doit pas l'heliport et son element soit est Neutre
            if (!zoneAModif.contains(z) && (z.estNeutre())) {
                zoneAModif.add(z);
            }
        }
        for (Zone z : zoneAModif) {
            zones[z.getX()][z.getY()].progresse();
        }

        notifyObservers();
    }

    /**boolean MovementPlayer() :
     * 		Player j : un Player.
     * 		Zone nZ : une zone.
     * 		Deplace un Player j dans les limites des dimensions de l'Island dans une zone nZ si elle est proche
     * 		Return : True si le Player a pu etre deplace, false sinon.
     **/
    public boolean MovementPlayer(Player j, Zone nZ) throws AccesHorsIsland {
        if (nZ.x < 1 || nZ.x > LARGEUR || (nZ.y < 1 || nZ.y > HAUTEUR))
            throw new AccesHorsIsland();
        if (j.getZone().estUnDeplacementPossible(nZ) && !nZ.estSubmergee()) {
            j.seDeplace(nZ);
            notifyObservers();
            return true;
        }
        notifyObservers();
        return false;
    }

    /**boolean DewaterZone() :
     * 		Player j : un Player.
     * 		Zone zV : une zone.
     * 		Asseche une zone zV dans les limites des dimensions de l'Island si elle est proche du Player j effectuant l'action
     * 		Return : True si la zone a pu etre assechee, false sinon.
     **/
    public boolean DewaterZone(Player j, Zone zV) throws AccesHorsIsland {
        if (zV.x < 1 || zV.x > LARGEUR || (zV.y < 1 || zV.y > HAUTEUR))
            throw new AccesHorsIsland();

        if (j.getZone().estAdjacente(zV)) {
            if (zV.estInondee()) {
                zV.situation = Situation.Normale;
                notifyObservers();
                return true;
            }
        }
        notifyObservers();
        return false;

    }

    /**void recupererArtifact() :
     * 		Player j : un Player.
     * 		Permet au Player j de recuperer un Artifact.
     **/
    public void recupererArtifact(Player j) {
        j.recupereObjet(null);
        notifyObservers();
    }

    /**Zone getZone() :
     * 		int x : un entier formant une coordonnee avec un autre entier.
     * 		int y : un entier formant une coordonnee avec un autre entier.
     * 		Return : la zone presente aux coordonnees choisies si elle n'est pas hors de l'Island.
     * @throws AccesHorsIsland
     */
    public Zone getZone(int x, int y) throws AccesHorsIsland {
        if (x < 1 || x > LARGEUR || (y < 1 || y > HAUTEUR))
            throw new AccesHorsIsland();
        return zones[x][y];
    }

    /**String toString() :
     * 		Return : Une chaine de caracteres correspondant aux informations de l'Island fournies par certains attributs.
     **/
    public String toString() {
        return String.format("Infos Islands: \nHauteur: %d\nLargeur: %d%nNombre de Players: %d", HAUTEUR, LARGEUR,
                this.players.size());
    }

    /**ArrayList getPlayers() :
     * 		Return : la liste des Players de la partie.
     **/
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**Zone getHeli() :
     * 		Return : la zone presentant l'heliport.
     **/
    public Zone getHeli() {
        return this.heli;
    }

    /**int getNbPlayer() :
     * 		Player j : un Player.
     *
     * 		Return : l'index du Player j dans la lisre des Players de la partie.
     *
     * 		Notes: Nous avons ici choisi de ne pas créer d'exception et la renvoyer ici lié aux Players contrairement aux zones
     **/
    public int getNbPlayer(Player j) {
        int index = players.indexOf(j);
        if (index==-1) {
            System.out.println("Player non existant");
            System.exit(1);
        }
        return index;
    }
}
