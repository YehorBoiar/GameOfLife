package ui;

import javax.swing.JPopupMenu;
import renderer.Renderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StructuresMenu extends JPopupMenu {
    private Renderer renderer;

    public StructuresMenu(JButton component, Renderer renderer) {
        this.renderer = renderer;

        createOptions();
        
    }

    private void createOptions(){
        menuOption(createIcon("icons/Dot.png"), 0);
        menuOption(createIcon("icons/Big_Dot.png"), 1);
        menuOption(createIcon("icons/Erase.png"), 2);
        menuOption(createIcon("icons/Big_Erase.png"), 3);
        menuOption(createIcon("icons/Glider.png"), 4);
        menuOption(createIcon("icons/Twicker.png"), 5);
    }


    private void menuOption(Icon icon, int brushID) {
        JMenuItem menuItem = new JMenuItem(icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.getMouseListener().setBrushID(brushID);
            }
        });
        add(menuItem);
    }

    private Icon createIcon(String iconPath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        return icon;
    }
    
}
