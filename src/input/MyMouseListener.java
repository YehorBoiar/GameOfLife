package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import brushes.Brush;
import renderer.Renderer;

/**
 * Custom MouseAdapter for handling mouse events in the Renderer.
 * This adapter is responsible for processing mouse clicks, releases, and drag
 * events.
 */
public class MyMouseListener extends MouseAdapter {
    private final Renderer renderer;
    private int brushID;

    /**
     * Constructs a new MyMouseListener with the specified Renderer.
     *
     * @param renderer The Renderer associated with this adapter.
     */
    public MyMouseListener(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Handles the mouse pressed event, updating the grid based on the click
     * position.
     * If the right mouse button is pressed, it records the initial mouse
     * coordinates for panning.
     * If the left mouse button is pressed, it calculates the grid indices and
     * toggles the cell state.
     * 
     * @param e The MouseEvent representing the mouse click event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            renderer.setLastMouseX(e.getX());
            renderer.setLastMouseY(e.getY());
        } else {
            int squareSize = renderer.calcSquareSize();

            int x = (int) ((e.getX() + renderer.getPanOffsetX()) / squareSize);
            int y = (int) ((e.getY() + renderer.getPanOffsetY() - 40) / squareSize);

            // Clamp the indices to valid grid bounds
            x = Math.min(Math.max(0, x), renderer.getCols() - 1);
            y = Math.min(Math.max(0, y), renderer.getRows() - 1);
            
            if (brushID >= 0 && brushID < Brush.values().length) {
                Brush.values()[brushID].execute(renderer, y, x);
                System.out.println("BrushID: " + brushID);
                System.out.println("Mouse Clicked: " + x + "," + y);
                renderer.getFrame().repaint();
            }

            System.out.println("BrushID: " + brushID);
            System.out.println("Mouse Clicked: " + x + "," + y);
            renderer.getFrame().repaint(); // Repaint the frame to update the drawing
        }
    }

    public int getBrushID() {
        return brushID;
    }

    public void setBrushID(int brushID) {
        this.brushID = brushID;
    }
}
