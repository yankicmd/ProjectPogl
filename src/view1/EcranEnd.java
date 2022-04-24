package view1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EcranEnd extends JPanel {
    public EcranEnd(String message) {
        this.setLayout(new BorderLayout());

        JLabel msg = new JLabel(message);

        msg.setFont(new Font("Serif", Font.PLAIN, 50));

        this.add(msg,BorderLayout.PAGE_START);
    }
}
