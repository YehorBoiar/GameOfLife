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
        // Check if the left mouse button is pressed (LMB)
        if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
            handleMouseDrag(e);
        }
    }
    
    /**
     * Handles the mouse drag event by updating the grid according to the drag
     * movements.
     *
     * @param e The MouseEvent representing the mouse drag event.
     */
    private void handleMouseDrag(MouseEvent e) {
        int squareSize = (int) (10 * renderer.getZoomFactor());
        int x = (int) ((e.getX() + renderer.getPanOffsetX()) / squareSize);
        int y = (int) ((e.getY() + renderer.getPanOffsetY() - 40) / squareSize);
    
        // Ensure the adjusted coordinates are within valid grid bounds
        x = Math.min(Math.max(0, x), renderer.getWidth() - 1);
        y = Math.min(Math.max(0, y), renderer.getHeight() - 1);
        // Check if we try to draw on the side.
        if (x >= 0 && x < renderer.getWidth() && y >= 0 && y < renderer.getHeight()) { 
            // Update the grid position directly without calculating steps
            renderer.reverseElement(y, x);
            renderer.setLastMouseX(x);
            renderer.setLastMouseY(y);
            renderer.getFrame().repaint();
        }
    }
    

}
