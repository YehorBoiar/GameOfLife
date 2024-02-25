import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        handleMouseClick(e);
    }

    /**
     * Handles the mouse click event by updating the grid according to the click position.
     *
     * @param e The MouseEvent representing the mouse click event.
     */
    private void handleMouseClick(MouseEvent e) {
        int x = e.getX() / 10;
        int y = (e.getY() - 40) / 10;
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
     * Handles the mouse drag event by updating the grid according to the drag movements.
     *
     * @param e The MouseEvent representing the mouse drag event.
     */
    private void handleMouseDrag(MouseEvent e) {
        int x = e.getX() / 10;
        int y = (e.getY() - 40) / 10;

        if (x >= 0 && x < renderer.getWidth() && y >= 0 && y < renderer.getHeight()) {
            if (renderer.getLastMouseX() != -1 && renderer.getLastMouseY() != -1) {
                int deltaX = x - renderer.getLastMouseX();
                int deltaY = y - renderer.getLastMouseY();
                int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));

                for (int step = 0; step <= steps; step++) {
                    int stepX = renderer.getLastMouseX() + step * deltaX / steps;
                    int stepY = renderer.getLastMouseY() + step * deltaY / steps;

                    // Ensure the updated coordinates are within the valid range
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
