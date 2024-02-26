package input;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import fileio.Load;
import fileio.Save;
import renderer.Renderer;

/**
 * MyKeyAdapter is responsible for adding hotkeys to our program.
 */
public class MyKeyAdapter extends KeyAdapter{
    private final Renderer renderer;

    public MyKeyAdapter(Renderer renderer) {
        this.renderer = renderer;
    }
    
    /**
     * In this method we add hotkeys we want to have,
     * and assign functionality to them.
     */
    @Override
    public void keyPressed(KeyEvent e) { 
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            toggleGameState();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_S) {
            Save save = new Save(renderer.getGrid());
            save.SaveToFile();
        }
        if (e.isControlDown() && keyCode == KeyEvent.VK_O) {
            Load load = new Load();
            load.loadFile();
        }
        if(e.isControlDown() && keyCode == KeyEvent.VK_Q){
            renderer.getFrame().dispose();
            System.exit(0);
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            rightPan();
        }
        if(keyCode == KeyEvent.VK_LEFT){
            leftPan();
        }
    }

    private void leftPan() {
        int move = 10;
    
        // Calculate the new pan offset
        int newPanOffsetX = renderer.getPanOffsetX() - move;
    
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
        int move = 10;
    
        // Calculate the maximum pan offset to prevent panning beyond the rightmost edge of the grid
        int maxPanOffsetX = (int) ((renderer.getWidth() * 10 * renderer.getZoomFactor()) - renderer.getFrame().getWidth());
    
        // Calculate the new pan offset
        int newPanOffsetX = renderer.getPanOffsetX() + move;
    
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

    
    
    
    private void toggleGameState() {
        renderer.setGameState(!renderer.getGameState());
        System.out.println("Game State toggled to: " + renderer.getGameState());
    }

}