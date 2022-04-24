package Game;

import java.awt.EventQueue;
import java.io.IOException;
// JE DOIS AJOUTER CA ...
import Modeles.DeroulementPartie;
import Modeles.Island;
import View.Mview;

public class IslandInterdit {
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on consid�re la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous int�resse. */
            Island Modeles = new Island();
            DeroulementPartie dp = new DeroulementPartie(Modeles);

            try {
                Mview View = new Mview(Modeles,dp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}
