import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter{
    private final Renderer renderer;

    public MyKeyAdapter(Renderer renderer) {
        this.renderer = renderer;
    }
    
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
}