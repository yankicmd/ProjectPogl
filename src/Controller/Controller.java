package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modeless.Island;
import Modeless.Player;

public class Controller implements ActionListener {
    Island Modeless;

    public Controller(Island Modeless) {
        this.Modeless = Modeless;
    }

    /**
     * Action effectue  rception d'un vnement : appeler la mthode [avance] du
     * modle.
     */

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.Modeless.avance();
        System.out.println("bipboup");
        this.Modeless.getPlayers();
        System.out.println("Changement de tour !");
        for(int i=0; i<this.Modeless.getPlayers().size(); i++) {
            this.Modeless.getPlayers().get(i).resetEnergy();
            System.out.println(this.Modeless.getPlayers().get(i).getRemainingA());
        }
    }
}
