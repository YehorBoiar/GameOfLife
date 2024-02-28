package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.Renderer;

public class PanningHandler extends MouseAdapter {
    private Renderer renderer;
    

    public PanningHandler(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Check if the right mouse button is pressed (RMB)
        if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
            handleRMBDrag(e);
        }
    }

    private void handleRMBDrag(MouseEvent e) {
        int deltaX = e.getX() - renderer.getLastMouseX();
        int deltaY = e.getY() - renderer.getLastMouseY();
    
        // Adjust the pan offset based on the dragging movement
        int newPanOffsetX = renderer.getPanOffsetX() - deltaX;
        int newPanOffsetY = renderer.getPanOffsetY() - deltaY;
    
        // Update the pan offset within bounds
        newPanOffsetX = Math.max(0, Math.min(newPanOffsetX, getMaxPanOffsetX()));
        newPanOffsetY = Math.max(0, Math.min(newPanOffsetY, getMaxPanOffsetY()));
    
        // Set the new pan offset and repaint
        renderer.setPanOffsetX(newPanOffsetX);
        renderer.setPanOffsetY(newPanOffsetY);
        renderer.getFrame().repaint();
    
        // Update the last mouse coordinates for the next drag event
        renderer.setLastMouseX(e.getX());
        renderer.setLastMouseY(e.getY());
    }

    // Rest of the code remains unchanged

    private int getMaxPanOffsetX() {
        return (int) (renderer.getWidth() * 10 * renderer.getZoomFactor() - renderer.getFrame().getWidth());
    }

    private int getMaxPanOffsetY() {
        return (int) (renderer.getHeight() * 10 * renderer.getZoomFactor() - renderer.getFrame().getHeight());
    }
}
