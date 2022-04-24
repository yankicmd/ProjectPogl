package others;

public class Obv {
    public abstract class Obv { {
        /**
         *Nous avons une liste [d'observateurs] d'observateurs, initialement vide, auxquels
         * viennent enregistrer les observateurs via la méthode [addObv].
         */
        private ArrayList<Obv> Obvs;
        public Observable() {
            this.Obvs = new ArrayList<Obv>();
        }
        public void addObv(Obv o) {
            Obvs.add(o);
        }

        /**
         * Lorsque l'état de l'objet observé change, il est convenu d'appeler le
         * Méthode [notifyObvs] pour notifier tous les observateurs
         * enregistré.
         * Nous le faisons ici concrètement en appelant la méthode [update] de chaque
         * observateur.
         */
        public void notifyObvs() {
            for(Obv o : Obvs) {
                o.update();
            }
        }
    }

}
