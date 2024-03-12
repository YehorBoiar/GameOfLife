package input;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import renderer.Renderer;

/**
 * Custom MouseWheelListener for handling zooming events in the Renderer.
 * This listener allows zooming in and out based on the mouse wheel rotation.
 */
public class ZoomHandler implements MouseWheelListener {
    private Renderer renderer;

    /**
     * Constructs a new ZoomHandler with the specified Renderer.
     *
     * @param renderer The Renderer associated with this handler.
     */
    public ZoomHandler(Renderer renderer) {
        this.renderer = renderer;
    }


    /**
     * Invoked when the mouse wheel is moved.
     * Adjusts the zoom factor based on the mouse wheel rotation to zoom in or out.
     * @param e The MouseWheelEvent representing the mouse wheel movement.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();

        double newZoomFactor;
        
        if (notches < 0) {
            // Zoom in
            newZoomFactor = renderer.getZoomFactor() * 1.1;
        } else {
            // Zoom out
            newZoomFactor = renderer.getZoomFactor() / 1.1;
        }

       updateZoom(newZoomFactor,renderer); 

    }
    
    private void updatePanOffsets() {
        int maxPanOffsetX = (int) ((renderer.getCols() * renderer.calcSquareSize()) - renderer.getFrame().getWidth());
        if (renderer.getPanOffsetX() > maxPanOffsetX) {
            renderer.setPanOffsetX(maxPanOffsetX);
        }
    
        int maxPanOffsetY = (int) ((renderer.getRows() * renderer.calcSquareSize()) - renderer.getFrame().getHeight());
        if (renderer.getPanOffsetY() > maxPanOffsetY) {
            renderer.setPanOffsetY(maxPanOffsetY);
        }
    }

    /** 
     * Updates the zoom factor as well as the grid size and square size. 
     * 
     * @param newZoomFactor The desired zoom factor to change to.
     * @param renderer The Renderer associated with this handler.
     */
    public void updateZoom(double newZoomFactor, Renderer renderer) {
        // Limit the zoom factor to the minimum value
        newZoomFactor = Math.max(1.0, newZoomFactor);

        // Update the zoom factor and repaint
        renderer.setZoomFactor(newZoomFactor);
        renderer.setSquareSize(renderer.calcSquareSize());
        System.out.println("Square size: " + renderer.getSquareSize());
        updatePanOffsets();
        renderer.getFrame().repaint();
    }

}
