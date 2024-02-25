package input;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import renderer.Renderer;

public class ZoomHandler implements MouseWheelListener {
    private Renderer renderer;

    public ZoomHandler(Renderer renderer) {
        this.renderer = renderer;
    }

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
        renderer.getFrame().repaint();
    }
}
