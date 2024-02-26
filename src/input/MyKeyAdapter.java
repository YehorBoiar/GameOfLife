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
    }

    private void rightPan(){
        int panOffsetX = renderer.getPanOffsetX();
        int move = 10;
        // if ((panOffsetX + move) > renderer.getWidth()*renderer.getZoomFactor()) {
        //     return;
        // }

        renderer.setPanOffsetX(panOffsetX+move);
        renderer.getFrame().repaint(); 
    }
    
    private void toggleGameState() {
        renderer.setGameState(!renderer.getGameState());
        System.out.println("Game State toggled to: " + renderer.getGameState());
    }

}