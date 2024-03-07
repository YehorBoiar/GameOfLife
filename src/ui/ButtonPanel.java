package ui;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import fileio.Load;
import fileio.Save;
import renderer.Renderer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * Represents a panel containing buttons for user interactions in the GUI.
 */
public class ButtonPanel extends JPanel implements ActionListener{
    private JButton configButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton speedUpButton;
    private JButton slowDownButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private Renderer renderer;

    /**
     * Constructs a ButtonPanel with specified buttons and associates it with a renderer.
     * @param renderer The renderer associated with this button panel.
     */
    public ButtonPanel(Renderer renderer) {
        this.renderer = renderer;

        configButton = new JButton("Config");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        speedUpButton = new JButton("Speed");
        slowDownButton = new JButton("Slow");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        exitButton = new JButton("Exit");

        JButton[] buttons = {configButton,startButton,stopButton,speedUpButton,slowDownButton,saveButton,loadButton,exitButton};

        for (int i = 0; i < buttons.length; i++ ) {
            buttons[i].setBackground(Color.RED);
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
    
        }

        add(configButton);
        add(startButton);
        add(stopButton);
        add(speedUpButton);
        add(slowDownButton);
        add(saveButton);
        add(loadButton);
        add(exitButton);
    }

    /**
     * Invoked when a button is clicked, performs actions based on the clicked button.
     * @param e The ActionEvent associated with the button click.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == configButton) {
            System.out.println("Config");
        }

        if (e.getSource() == startButton) {
            renderer.setGameState(true);
        }

        if (e.getSource() == stopButton) {
            renderer.setGameState(false);
            renderer.getFrame().repaint();;
        }

        if (e.getSource() == speedUpButton) {
            Main.speedUp();
        }

        if (e.getSource() ==slowDownButton) {
            Main.slowDown();
        }

        if (e.getSource() == saveButton) {
            Save save = new Save(renderer.getGrid());
            save.SaveToFile();
        }

        if (e.getSource() == loadButton) {
            Load load = new Load();
            load.loadFile();
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }


         
    }

}
