package input;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.Renderer;

/**
 * Custom MouseAdapter for handling mouse events in the Renderer.
 * This adapter is responsible for processing mouse clicks, releases, and drag events.
 */
public class MyMouseListener extends MouseAdapter {
    private final Renderer renderer;

    /**
     * Constructs a new MyMouseListener with the specified Renderer.
     *
     * @param renderer The Renderer associated with this adapter.
     */
    public MyMouseListener(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Invoked when the mouse is clicked.
     * Calls the handleMouseClick method to update the grid based on the mouse click.
     *
     * @param e The MouseEvent representing the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if the left mouse button is pressed (LMB). 
        // for some reason the never version BUTTON1_DOWN_MASK doesn't work, so I used the older version
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
            handleMouseClick(e);
            System.out.println("aboba");
        }
    }

    /**
     * Handles the mouse click event by updating the grid according to the click position.
     *
     * @param e The MouseEvent representing the mouse click event.
     */
    private void handleMouseClick(MouseEvent e) {
        int x = (int) (e.getX() / 10 / renderer.getZoomFactor());
        int y = (int) ((e.getY() - 40) / 10 / renderer.getZoomFactor());
        
        renderer.reverseElement(y, x);
        System.out.println("Mouse Clicked: " + x + "," + y);
        renderer.getFrame().repaint(); // Repaint the frame to update the drawing
    }

    /**
     * Invoked when the mouse is released.
     * Resets the last mouse coordinates to -1, indicating no ongoing drag.
     *
     * @param e The MouseEvent representing the mouse release event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        renderer.setLastMouseX(-1);
        renderer.setLastMouseY(-1);
    }
}
