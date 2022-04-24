package Modelesss;


import java.util.ArrayList;

import Player.CollectArtifact;

public class Player {
    private Island Island;
    private Zones z;
    private ArrayList<Objet> items;
    private int nbPlayer;

    public Player(Island Island, Zones z, int n) {
        this.Island = Island;
        this.z = z;
        //this.nbPlayer = n;
        this.items = new ArrayList<Objet>();
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
    public void recupereArtifact(Objet truc) {
        items.add(truc);
        if (truc instanceof Keys) {
            //System.out.println("Le Player " + this.nbPlayer + " a une cle en plus" + truc.e.toString());
        } else {
            //System.out.println("Le Player " + this.nbPlayer + " a un Artifact en plus" + truc.e.toString());
        }
    }

    /**
     //Fonction verifiant si dans la liste le joeueur possede une cle de type l'Artifact qu'il veut recuperer
     public boolean verifCle(Modeles.Zones.Element e) {
     for (int i = 0; i < this.items.size(); i++) {
     Object obj = this.items.get(i);	
     if (obj instanceof Keyss) {
     if (e.equals(((Keyss) obj).getElement())) {
     System.out.println("Le Player " + this.nbPlayer + " a bien une cle !");
     this.enleveCles((Keyss) obj);
     System.out.println("La cle pour ramasser cet Artifact a etait utilise ");
     return true;
     }
     }
     }
     System.out.println("Le Player " + this.nbPlayer + " n'a pas de cle !");
     return false;
     }**/
    //Fonction parcourant la liste des items et indiquant combien il y a de cles  de type e
    public int combienCles(Element e) {
        //System.out.println("Element" + e.toString());
        int nb = 0;
        for (int i = 0; i < this.items.size(); i++) {
            Object obj = this.items.get(i);
            //System.out.println("Element ds la liste " + e.toString());
            if (obj instanceof Keys) {
                //System.out.println("type cles");
                if(((Keys) obj).e.equals(e)) {
                    //System.out.println("egaux");
                    nb ++;
                }
            }
        }
        //System.out.println("Le Player " + this.nbPlayer + " a " + nb + " cles" );
        return nb;
    }


    public int getNbCles() {
        int res = 0;
        for (Objet o:items) {
            if (o instanceof Keys)
                res++;
        }
        return res;
    }
    public int getNbArtifacts() {
        int res = 0;
        for (Objet o:items) {
            if (o instanceof Artifact)
                res++;
        }
        return res;
    }

    public int getNbObjet() {
        return items.size();
    }

    public ArrayList<Objet> getInventaire(){
        return this.items;
    }

    public void enleveCles(Keys c, int combien) {
        int nb = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i) instanceof Keys) {
                if (c.cleEgales((Keys) this.items.get(i))) {
                    this.items.remove(i);
                    nb ++;
                }
            }
            if (nb == combien) {
                return;
            }
        }
    }

    public void enleveArtifact(Artifact a) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i) instanceof Artifact) {
                if (a.ArtifactEgal((Artifact) this.items.get(i))) {
                    this.items.remove(i);
                }
            }
        }
    }
}


