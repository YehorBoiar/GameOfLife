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

/**
 * Represents a panel containing buttons for user interactions in the GUI.
 */
public class ButtonPanel extends JPanel implements ActionListener{
    private StructuresMenu menu;
    private JButton presetButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton stepButton;
    private JButton speedUpButton;
    private JButton slowDownButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton mainMenuButton;
    private Renderer renderer;
    private ZoomHandler zoomHandler;


    /**
     * Constructs a ButtonPanel with specified buttons and associates it with a renderer.
     * @param renderer The renderer associated with this button panel.
     */
    public ButtonPanel(Renderer renderer) {
        setBackground(Color.BLACK);
        this.renderer = renderer;

        presetButton = new JButton("Presets");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        speedUpButton = new JButton("Speed");
        slowDownButton = new JButton("Slow");
        stepButton = new JButton("Step");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        exitButton = new JButton("Exit");
        mainMenuButton = new JButton("Main Menu");
        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");

        zoomHandler = new ZoomHandler(renderer);     
        menu = new StructuresMenu(presetButton, renderer);

        JButton[] buttons = {presetButton,startButton,stopButton,speedUpButton,slowDownButton,stepButton,saveButton,loadButton,exitButton, mainMenuButton, zoomInButton, zoomOutButton};

        for (int i = 0; i < buttons.length; i++ ) {
            buttons[i].setBackground(Color.RED);
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            add(buttons[i]);
        }
    
    } 

    /**
     * Invoked when a button is clicked, performs actions based on the clicked button.
     * @param e The ActionEvent associated with the button click.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == presetButton) {
            menu.show(presetButton, 0, presetButton.getHeight());
        }

        if (e.getSource() == startButton) {
            renderer.setGameState(true);
        }

        if (e.getSource() == stopButton) {
            renderer.setGameState(false);
            renderer.getFrame().repaint();
        }

        if (e.getSource() == speedUpButton) {
            Main.speedUp();
        }

        if (e.getSource() ==slowDownButton) {
            Main.slowDown();
        }

        if (e.getSource() == stepButton) {
            renderer.nextStep();
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

        if (e.getSource() == mainMenuButton) {

            renderer.getFrame().setVisible(false);
            
            GameMenu mainMenu = GameMenu.getInstance();

            mainMenu.setVisible(true);


        }

        if (e.getSource() == zoomInButton) {
            zoomHandler.updateZoom(renderer.getZoomFactor() * 1.1, renderer);
        }

        if ((e.getSource() == zoomOutButton)) {
            ZoomHandler zoomHandler = new ZoomHandler(renderer);
            zoomHandler.updateZoom(renderer.getZoomFactor() * 0.9, renderer);
        }
         
    }

}
