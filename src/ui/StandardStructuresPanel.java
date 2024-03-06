package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StandardStructuresPanel extends JPanel {
    private final int BUTTON_SIZE = 60;
    private boolean display = false;

    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public StandardStructuresPanel() {
        if(!display){
            return;
        }
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
