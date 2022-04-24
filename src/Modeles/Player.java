package Modelesss;

import java.util.ArrayList;

public class Player {
    private Island Island;
    privateZones z;
    private ArrayList<Object> items;
    public Player(Island Island,Zones z) {
        this.Island = Island;
        this.z = z;
    }


    public void seDeplace(Zones nz) {
        this.z = nz;
    }

    public String toString() {
        return "--Player--\n" + z.toString() + "------------";
    }

    public Island getIsland() {
        return this.Island;
    }
    public Zones getZones() {
        return this.z;
    }
}
