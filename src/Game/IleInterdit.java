package Game;

public class IslandInterdit {
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considère la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Island Modeles = new Island();
            CVue vue = new CVue(Modeles);
        });
    }
}
