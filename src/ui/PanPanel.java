package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import input.PanningHandler;
import renderer.Renderer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;


public class PanPanel extends JPanel implements ActionListener{

    private JButton panUpButton;
    private JButton panLeftButton;
    private JButton panRightButton;
    private JButton panDownButton;
    PanningHandler panningHandler;


    public PanPanel(Renderer renderer) {
        setBackground(Color.BLACK);
        panningHandler = new PanningHandler(renderer);

        panUpButton = new JButton("Pan Up");
        panLeftButton = new JButton("Pan Left");
        panRightButton = new JButton("Pan Right");
        panDownButton = new JButton("Pan Down");

        JButton[] buttons = {panUpButton, panLeftButton, panRightButton, panDownButton};

        for (int i = 0; i < buttons.length; i++ ) {
            buttons[i].setBackground(Color.RED);
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            add(buttons[i]);
        }
    
    } 
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == panUpButton) {
            panningHandler.upPan();
        }

        if (e.getSource() == panLeftButton) {
            panningHandler.leftPan();
        }

        if (e.getSource() == panRightButton) {
            panningHandler.rightPan();
        }

        if (e.getSource() == panDownButton) {
            panningHandler.downPan();
        }
    }
}

