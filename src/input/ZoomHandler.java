package input;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import renderer.Renderer;

/**
 * Custom MouseAdapter for handling zooming events in the Renderer.
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

        // Limit the zoom factor to the minimum value
        newZoomFactor = Math.max(1.0, newZoomFactor);

        // Update the zoom factor and repaint
        renderer.setZoomFactor(newZoomFactor);
        updatePanOffsets();
        renderer.getFrame().repaint();
    }
    
    private void updatePanOffsets() {
        int maxPanOffsetX = (int) ((renderer.getWidth() * 10 * renderer.getZoomFactor()) - renderer.getFrame().getWidth());
        if (renderer.getPanOffsetX() > maxPanOffsetX) {
            renderer.setPanOffsetX(maxPanOffsetX);
        }
    
        int maxPanOffsetY = (int) ((renderer.getHeight() * 10 * renderer.getZoomFactor()) - renderer.getFrame().getHeight());
        if (renderer.getPanOffsetY() > maxPanOffsetY) {
            renderer.setPanOffsetY(maxPanOffsetY);
        }
    }

}
