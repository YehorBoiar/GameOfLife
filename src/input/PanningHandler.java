package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.Renderer;

/**
 * Custom MouseAdapter for handling panning events in the Renderer.
 * This listener allows panning across the frame using the mouse drags.
 */

public class PanningHandler extends MouseAdapter {
    private Renderer renderer;
    private final int PAN_DISTANCE = 50; 
    

    public PanningHandler(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Checks if the right mouse button is pressed (RMB)
        if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
            handleRMBDrag(e);
        }
    }

    private void handleRMBDrag(MouseEvent e) {
        int deltaX = e.getX() - renderer.getLastMouseX();
        int deltaY = e.getY() - renderer.getLastMouseY();
    
        // Updates the last mouse coordinates for the next drag event
        renderer.setLastMouseX(e.getX());
        renderer.setLastMouseY(e.getY());

        updatePanOffset(deltaX, deltaY);
    }

    /**
     * This method adjusts the pan offset based on the dragging movement, 
     * updates the pan offset within bounds and then sets the new pan offset and repaints.
     * 
     * @param deltaX The change in x coordinate.
     * @param deltaY The change in y coordinate.
     */

    public void updatePanOffset(int deltaX,int deltaY) {

         int newPanOffsetX = renderer.getPanOffsetX() - deltaX;
         int newPanOffsetY = renderer.getPanOffsetY() - deltaY;
     
         newPanOffsetX = Math.max(0, Math.min(newPanOffsetX, getMaxPanOffsetX()));
         newPanOffsetY = Math.max(0, Math.min(newPanOffsetY, getMaxPanOffsetY()));
    
         renderer.setPanOffsetX(newPanOffsetX);
         renderer.setPanOffsetY(newPanOffsetY);
         renderer.getFrame().repaint();

    }

    private int getMaxPanOffsetX() {
        return (int) (renderer.getCols() * renderer.calcSquareSize() - renderer.getFrame().getWidth());
    }

    private int getMaxPanOffsetY() {
        return (int) (renderer.getRows() * renderer.calcSquareSize() - renderer.getFrame().getHeight());
    }

    public void leftPan() {

        updatePanOffset(PAN_DISTANCE, 0);

    }

    public void rightPan() {

        updatePanOffset(-PAN_DISTANCE, 0);
    
    }

    public void downPan() {

        updatePanOffset(0,-PAN_DISTANCE);

    }

    public void upPan() {

        updatePanOffset(0, PAN_DISTANCE);
        
    }

}
