package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import brushes.Brush;
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
        // Checks if the left mouse button is pressed (LMB)
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
        if (SwingUtilities.isLeftMouseButton(e)) {
            double squareSize = renderer.calcSquareSize();
            renderer.setLastMouseX(-1);
            renderer.setLastMouseY(-1);
            int x = (int) ((e.getX() + renderer.getPanOffsetX()) / squareSize);
            int y = (int) ((e.getY() + renderer.getPanOffsetY() - 40) / squareSize);
            
            // Ensure the adjusted coordinates are within valid grid bounds
            x = Math.min(Math.max(0, x), renderer.getCols() - 1);
            y = Math.min(Math.max(0, y), renderer.getRows() - 1);
            // Check if we try to draw on the side.
            if (x >= 0 && x < renderer.getCols() && y >= 0 && y < renderer.getRows()) { 

                Brush.values()[renderer.getMouseListener().getBrushID()].execute(renderer, y, x);
                renderer.getFrame().repaint();
            }

        }
    }

}
