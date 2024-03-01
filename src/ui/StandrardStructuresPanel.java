package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StandrardStructuresPanel extends JPanel {
    private final int BUTTON_SIZE = 60;

    public StandrardStructuresPanel() {
        setLayout(new FlowLayout());
        JButton button = createButton("<html>Glider<br>Gun</html>", null);
        add(button);
    }

    private JButton createButton(String text, ActionListener listener){
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE); 
        return button;
    }
}
