package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import fileio.Load;
import fileio.Save;
import renderer.Renderer;


/**
 * MyKeyAdapter is responsible for adding hotkeys to our program.
 */
public class MyKeyAdapter extends KeyAdapter {
    private Renderer renderer;
    private final int MOVE = 50; 

    public MyKeyAdapter(Renderer renderer) {
        this.renderer = renderer;
    }
    //TODO add zoom in and zoom out hotkeys
    //TODO add accelerate and decelarate hotkeys
    /**
     * In this method we add hotkeys we want to have,
     * and assign functionality to them.
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
            rightPan();
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPan();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPan();
        }
        if (keyCode == KeyEvent.VK_UP) {
            upPan();
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
    }

    private void displayButtons(){
        boolean showButtons = renderer.isShowButtons();
        renderer.setShowButtons(!showButtons);
        renderer.getButtonPanel().setVisible(!showButtons);
        System.out.println("Display button panel: " + !showButtons);
    }

    private void leftPan() {
        

        // Calculate the new pan offset
        int newPanOffsetX = renderer.getPanOffsetX() - MOVE;

        // Check if the new pan offset is within bounds
        if (newPanOffsetX >= 0) {
            renderer.setPanOffsetX(newPanOffsetX);
            renderer.getFrame().repaint();
        } else {
            // If moving out of bounds, set the pan offset to the minimum allowed
            renderer.setPanOffsetX(0);
            renderer.getFrame().repaint();
        }
    }

    private void rightPan() {
        

        // Calculate the maximum pan offset to prevent panning beyond the rightmost edge
        // of the grid
        int maxPanOffsetX = (int) ((renderer.getCols() * renderer.calcSquareSize())
                - renderer.getFrame().getWidth());

        // Calculate the new pan offset
        int newPanOffsetX = renderer.getPanOffsetX() + MOVE;

        // Check if the new pan offset is within bounds
        if (newPanOffsetX <= maxPanOffsetX) {
            renderer.setPanOffsetX(newPanOffsetX);
            renderer.getFrame().repaint();
        } else {
            // If moving out of bounds, set the pan offset to the maximum allowed
            renderer.setPanOffsetX(maxPanOffsetX);
            renderer.getFrame().repaint();
        }
    }

    private void downPan() {
        // Calculate the maximum pan offset to prevent panning beyond the bottom edge of
        // the grid
        int maxPanOffsetY = (int) ((renderer.getRows() * renderer.calcSquareSize())
                - renderer.getFrame().getHeight());

        // Calculate the new pan offset
        int newPanOffsetY = renderer.getPanOffsetY() + MOVE;

        // Check if the new pan offset is within bounds
        if (newPanOffsetY <= maxPanOffsetY) {
            renderer.setPanOffsetY(newPanOffsetY);
            renderer.getFrame().repaint();
        } else {
            // If moving out of bounds, set the pan offset to the maximum allowed
            renderer.setPanOffsetY(maxPanOffsetY);
            renderer.getFrame().repaint();
        }
    }

    private void upPan() {
        
    
        // Calculate the new pan offset
        int newPanOffsetY = renderer.getPanOffsetY() - MOVE;
    
        // Check if the new pan offset is within bounds
        if (newPanOffsetY >= 0) {
            renderer.setPanOffsetY(newPanOffsetY);
            renderer.getFrame().repaint();
        } else {
            // If moving out of bounds, set the pan offset to the minimum allowed
            renderer.setPanOffsetY(0);
            renderer.getFrame().repaint();
        }
    }

    private void toggleGameState() {
        renderer.setGameState(!renderer.getGameState());
        System.out.println("Game State toggled to: " + renderer.getGameState());
    }

}