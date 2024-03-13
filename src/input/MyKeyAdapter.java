package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import fileio.Load;
import fileio.Save;
import renderer.Renderer;
import ui.GameMenu;
import input.PanningHandler;


/**
 * MyKeyAdapter is responsible for adding hotkeys to our program.
 */
public class MyKeyAdapter extends KeyAdapter {
    private Renderer renderer;
    private ZoomHandler zoomHandler;
    private PanningHandler panHandler;

    public MyKeyAdapter(Renderer renderer) {
        this.renderer = renderer;

        zoomHandler = new ZoomHandler(renderer);
        panHandler = new PanningHandler(renderer);
    }
    /**
     * In this method we add hotkeys and assign each a specific functionality.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            toggleGameState();
            renderer.getFrame().repaint();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_M) {
            displayButtons();
            renderer.getFrame().repaint();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_S) {
            Save save = new Save(renderer.getGrid());
            save.SaveToFile();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_O) {
            Load load = new Load();
            load.loadFile();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_Q) {
            renderer.getFrame().dispose();
            System.exit(0);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            panHandler.rightPan();
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            panHandler.leftPan();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            panHandler.downPan();
        }
        if (keyCode == KeyEvent.VK_UP) {
            panHandler.upPan();
        }
        if (keyCode == KeyEvent.VK_1) {
            renderer.getMouseListener().setBrushID(0);
        }
        if (keyCode == KeyEvent.VK_2) {
            renderer.getMouseListener().setBrushID(1);
        }
        if (keyCode == KeyEvent.VK_3) {
            renderer.getMouseListener().setBrushID(2);
        }
        if (keyCode == KeyEvent.VK_4) {
            renderer.getMouseListener().setBrushID(3);
        }
        if (keyCode == KeyEvent.VK_5) {
            renderer.getMouseListener().setBrushID(4);
        }
        if (keyCode == KeyEvent.VK_6) {
            renderer.getMouseListener().setBrushID(5);
        }

        if (keyCode == KeyEvent.VK_EQUALS) {
            zoomHandler.updateZoom(renderer.getZoomFactor() * 1.1, renderer);
        }

        if (keyCode == KeyEvent.VK_MINUS) {
            zoomHandler.updateZoom(renderer.getZoomFactor() * 0.9, renderer);
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {

            renderer.getFrame().setVisible(false);
            GameMenu mainMenu = GameMenu.getInstance();
            mainMenu.setVisible(true);
        }
    }

    /**
     * This method is responsible for displaying and hiding the button panels. 
     */
    private void displayButtons(){
        boolean showButtons = renderer.isShowButtons();
        renderer.setShowButtons(!showButtons);
        renderer.getButtonPanel().setVisible(!showButtons);
        renderer.getPanPanel().setVisible(!showButtons);;
        System.out.println("Display button panel: " + !showButtons);
    }

    
    /**
     * This method is responsible for stopping and starting the game.
     */
    private void toggleGameState() {
        renderer.setGameState(!renderer.getGameState());
        System.out.println("Game State toggled to: " + renderer.getGameState());
    }

}