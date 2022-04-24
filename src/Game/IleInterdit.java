package Game;

public class IslandInterdit {
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considère la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Island modele = new Island();
            Mview vue = new Mview(modele);
            System.out.println(modele.toString());
            try {
                Mview vue = new Mview(modele);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}

