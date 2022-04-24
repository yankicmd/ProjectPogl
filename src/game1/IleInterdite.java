package game1;
//test commit

import java.awt.EventQueue;
import java.io.IOException;

import modele1.DeroulementPartie;
import modele1.Island;
import view1.Cview;

public class IleInterdite {
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considere la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous interesse. */
            Island modele = new Island();
            DeroulementPartie dp = new DeroulementPartie(modele);

            try {
                Cview View = new Cview(modele,dp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}


