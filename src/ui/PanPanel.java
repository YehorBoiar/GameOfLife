package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import input.ZoomHandler;
import fileio.Load;
import fileio.Save;
import renderer.Renderer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;


public class PanPanel extends JPanel {

    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private Renderer renderer;

    public PanPanel(Renderer renderer) {
        setBackground(Color.WHITE);
        this.renderer = renderer;

        upButton = new JButton("Pan Up");
        leftButton = new JButton("Pan Left");
        rightButton = new JButton("Pan Right");
        downButton = new JButton("Pan Down");

        JButton[] buttons = {upButton, leftButton, rightButton, downButton};

        for (int i = 0; i < buttons.length; i++ ) {
            buttons[i].setBackground(Color.RED);
            //buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            add(buttons[i]);
        }
        
        setBounds(500, 500,200,200);
    
    } 
    
}

