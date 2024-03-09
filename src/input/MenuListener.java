package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import renderer.Renderer;
import ui.StructuresMenu;

public class MenuListener implements ActionListener {
    private JButton component;
    private final Renderer renderer;
    public MenuListener(JButton component, Renderer renderer) {
        this.renderer = renderer;
        this.component = component;
    }

    public void actionPerformed(ActionEvent e) {
        StructuresMenu configMenu = new StructuresMenu(component, renderer);
        configMenu.show(component, 0, component.getHeight());
    }
}
