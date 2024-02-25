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
        
        if (notches < 0) {
            System.out.println("Mouse wheel moved up");
            renderer.setZoomFactor(renderer.getZoomFactor()*1.1);
            renderer.getFrame().repaint();
        } else {
            System.out.println("Mouse wheel moved down");
            renderer.setZoomFactor(renderer.getZoomFactor()/1.1);
            renderer.getFrame().repaint();
        }
    }
}