package Modeles;

import java.util.ArrayList;

public class Player {
    private Island Island;
    privateZoness z;
    private ArrayList<Object> items;
    public Joueur(Island Island,Zoness z) {
        this.Island = Island;
        this.z = z;
    }


    public void seDeplace(Zones nz) {
        this.z = nz;
    }

    public String toString() {
        return "--Joueur--\n" + z.toString() + "------------";
    }

    public Island getIsland() {
        return this.Island;
    }
    public Zones getZones() {
        return this.z;
    }
}