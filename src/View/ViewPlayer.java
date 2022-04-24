package View;


import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import others.Observer;
import Modeles.DeroulementPartie;

public class ViewPlayer extends JPanel implements Observer  {
    DeroulementPartie dp;
    JLabel labelPlayer;
    JLabel labelTourRestant;
    JLabel nbCles;

    public ViewPlayer(DeroulementPartie dp) {
        this.dp = dp;
        dp.addObserver(this);
        labelPlayer = new JLabel("Player " + Integer.toString(dp.getindicePlayerModulo()+1));
        //labelTourRestant = new JLabel("Nombre de tours restants: " + Integer.toString(dp.getAJActuel().getnbActionsMax() - dp.getAJActuel().nbActionsUtilisees()));
        System.out.println(dp.getActionsRestantes());
        labelTourRestant = new JLabel("Nombre de tours restants: " + dp.getActionsRestantes());

        //Liste Artifact

        //nb Cls:

        this.add(labelPlayer,BorderLayout.NORTH);
        this.add(labelTourRestant,BorderLayout.SOUTH);
    }


    @Override
    public void update() {
        paint();
    }

    public void paint() {
        labelPlayer.setText("Player " + Integer.toString(dp.getindicePlayerModulo()+1));
        labelTourRestant.setText("Nombre de tours restants: " + dp.getActionsRestantes());

    }

}
