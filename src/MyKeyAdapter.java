import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toggleGameState();
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            Save save = new Save(renderer.getGrid());
            save.SaveToFile();
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
            Load load = new Load();
            load.loadFile();
        }
    }

    private void toggleGameState() {
        renderer.setGameState(!renderer.getGameState());
        System.out.println("Game State toggled to: " + renderer.getGameState());
    }

    // TODO - Write CTRL-Q to exit the program.
}