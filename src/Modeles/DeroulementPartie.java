package Modeless;


import java.util.ArrayList;
import java.util.Random;

import others.Observable;
import others.Observer;
import Player.Playeractions;

public class DeroulementPartie extends Observable implements Observer {

    Random rand = new Random();

    private Island Island;
    private Player j;
    private Playeractions ajActuel;
    private int indicePlayer;
    private int actionsRestantes;

    public DeroulementPartie(Island Island) {
        this.Island = Island;
        indicePlayer = 0;
        this.j = Island.getPlayers().get(indicePlayer);
        ajActuel = new Playeractions(j);
        actionsRestantes = ajActuel.getnbActionsMax();
        ajActuel.addObserver(this);
    }

    public void prochainPlayer() {
        indicePlayer++;
        j = Island.getPlayers().get(indicePlayer % Island.getPlayers().size());
        ajActuel = new Playeractions(j);
        actionsRestantes = ajActuel.getnbActionsMax();
        ajActuel.addObserver(this);
        this.notifyObservers();
    }

    public Playeractions getAJActuel() {
        return ajActuel;
    }

    public int getindicePlayerModulo() {
        return indicePlayer % Island.getPlayers().size();
    }

    public int getActionsRestantes() {
        // System.out.println(ajActuel.getnbActionsMax() -
        // ajActuel.nbActionsUtilisees());
        return ajActuel.getnbActionsMax() - ajActuel.nbActionsUtilisees();
    }

    public Player getPlayer() {
        return this.j;
    }

    public void giveAleaKeyss() {
        int chance = rand.nextInt(100);
        if (0 <= chance && chance < 20) {
            Player PlayerA = getPlayer();
            Keys cAlea = Keys.aleaKeyss();
            PlayerA.recupereArtifact(cAlea);
            System.out.println("Ce Player recoit une Keys " + cAlea.e.toString());
            this.notifyObservers();
        }
    }

    // Fonction verifiant si la partie est gagne
    public boolean gagne() {
        ArrayList<Player> listJ = Island.getPlayers();
        int nbArte = 0;
        for (Player j : listJ) {
            if (!j.getZones().equals(Island.getHeli())) {
                // System.out.println("Pas tous a l'helico");
                return false;
            }
            nbArte += j.getNbArtifacts();
        }
        if (!(nbArte == Island.nbArtifact)) {
            // System.out.println("Pas tous les Artifacts");
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        actionsRestantes = getActionsRestantes();
        this.notifyObservers();

    }
}
