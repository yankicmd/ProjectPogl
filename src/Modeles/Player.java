package Modelessss;

import java.util.ArrayList;

public class Player {
    private Island Island;
    privateZoness z;
    private ArrayList<Object> items;
    public Player(Island Island,Zoness z) {
        this.Island = Island;
        this.z = z;
    }


    public void seDeplace(Zoness nz) {
        this.z = nz;
    }

    public String toString() {
        return "--Player--\n" + z.toString() + "------------";
    }

    public Island getIsland() {
        return this.Island;
    }
    public Zoness getZoness() {
        return this.z;
    }
}
