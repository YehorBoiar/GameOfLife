package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.Renderer;

/**
 * Custom MouseMotionAdapter for handling mouse dragging events in the Renderer.
 * This adapter is responsible for updating the grid based on mouse drag
 * movements.
 */
public class MyMouseMotionAdapter extends MouseAdapter {
    // TODO - If you don't drag your mouse fast enough, it doesn't drag it.

    private final Renderer renderer;

    /**
     * Constructs a new MyMouseMotionAdapter with the specified Renderer.
     *
     * @param renderer The Renderer associated with this adapter.
     */
    public MyMouseMotionAdapter(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Invoked when the mouse is dragged.
     * Calls the handleMouseDrag method to update the grid based on the mouse drag.
     *
     * @param e The MouseEvent representing the mouse drag event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        handleMouseDrag(e);
    }

    /**
     * Handles the mouse drag event by updating the grid according to the drag
     * movements.
     *
     * @param e The MouseEvent representing the mouse drag event.
     */
    private void handleMouseDrag(MouseEvent e) {
        int x = (int) (e.getX() / 10 / renderer.getZoomFactor());
        int y = (int) ((e.getY() - 40) / 10 / renderer.getZoomFactor());
    
        if (x >= 0 && x < renderer.getWidth() && y >= 0 && y < renderer.getHeight()) {
            if (renderer.getLastMouseX() != -1 && renderer.getLastMouseY() != -1) {
                int deltaX = x - renderer.getLastMouseX();
                int deltaY = y - renderer.getLastMouseY();
                int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
    
                if (steps == 0) {
                    return;
                }
    
                for (int step = 0; step <= steps; step++) {
                    int stepX = renderer.getLastMouseX() + step * deltaX / steps;
                    int stepY = renderer.getLastMouseY() + step * deltaY / steps;
    
                    if (stepX >= 0 && stepX < renderer.getWidth() && stepY >= 0 && stepY < renderer.getHeight()) {
                        renderer.reverseElement(stepY, stepX);
                    }
                }
            }
    
            renderer.setLastMouseX(x);
            renderer.setLastMouseY(y);
            renderer.getFrame().repaint();
        }
    }
    

}
