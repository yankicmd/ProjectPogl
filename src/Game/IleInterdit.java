package Game;

public class IslandInterdit {
    public static void main(String[] args) {
        /**
         * Pour les besoins du jour on considère la ligne EvenQueue... comme une
         * incantation qu'on pourra expliquer plus tard.
         */
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Island Modeless = new Island();
            Mview vue = new Mview(Modeless);
            System.out.println(Modeless.toString());
            try {
                Mview vue = new Mview(Modeless);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}

