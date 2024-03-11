package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import renderer.Renderer;

public class PanningHandler extends MouseAdapter {
    private Renderer renderer;
    private final int PAN_DISTANCE = 50; 
    

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
    
        // Update the last mouse coordinates for the next drag event
        renderer.setLastMouseX(e.getX());
        renderer.setLastMouseY(e.getY());

        updatePanOffset(deltaX, deltaY);
    }

    // Rest of the code remains unchanged

    public void updatePanOffset(int deltaX,int deltaY) {

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
