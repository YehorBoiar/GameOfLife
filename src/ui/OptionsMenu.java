package ui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//cd ../src; javac -d ../bin main/*.java input/*.java renderer/*.java logic/*.java ui/*.java fileio/*.java brushes/*.java ; cd ../bin; java -cp . main.Main

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.*;

public class OptionsMenu extends JFrame implements ActionListener {

    private static OptionsMenu theOptions;
    private JPanel optionsPanel;
    private JPanel textPanel;

    private JTextField XField;
    private JTextField YField;
    private JTextField ZField;
    private JTextField gridSizeField;

    private JLabel XLabel;
    private JLabel YLabel;
    private JLabel ZLabel;
    private JLabel gridSizeLabel;

    private JButton backToGameMenu;
    private JButton hotKeyExplain;
    
    private GridBagConstraints gbc = new GridBagConstraints();

    // default values
    private int xValue = 2;
    private int yValue = 3;
    private int zValue = 3;
    private int gridSize = 50;

    // getters
    public int getX() {
        return this.xValue;
    }

    public int getY() {
        return this.yValue;
    }

    public int getZ() {
        return this.zValue;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    // return singleton instance
    public static synchronized OptionsMenu getInstance() {
        if (theOptions == null) {
            theOptions = new OptionsMenu();
        }
        return theOptions;
    }

    private OptionsMenu() {

        configFrame();

        XLabel = makeLabel("X");
        YLabel = makeLabel("Y");
        ZLabel = makeLabel("Z");
        gridSizeLabel = makeLabel("Grid Size");

        XField = makeTextField("2");
        YField = makeTextField("3");
        ZField = makeTextField("3");
        gridSizeField = makeTextField("50");

        configPanels();
        configBackToMenuButton();
        explainButton();

    }

    private void configFrame() {

        setTitle("Options Menu");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

    }

    private JLabel makeLabel(String labelName) {

        JLabel label = new JLabel(labelName, SwingConstants.CENTER);

        Font labelFont = new Font(Font.MONOSPACED, Font.BOLD, 19);

        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(110, 30));
        label.setFont(labelFont);

        return label;
    }

    private JTextField makeTextField(String defaultValue) {

        JTextField field = new JTextField(defaultValue);

        field.addActionListener(this);
        field.addFocusListener(null);
        field.setPreferredSize(new Dimension(100, 30));

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(defaultValue)) {
                    field.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (field.getText().isEmpty()) {
                    field.setText(defaultValue);
                }
            }
        });

        return field;

    }

    private int validateInput(JTextField field, int defaultValue) {

        int value;

        try {

            int input = Integer.parseInt(field.getText());

            if (!(input < 0 || input > 8)) {

                value = input;

                return value;
            } else {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Enter an integer between 0-8");

        }

        return defaultValue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == XField) {

            xValue = validateInput(XField, xValue);

        }

        if (e.getSource() == YField) {

            yValue = validateInput(YField, yValue);

        }

        if (e.getSource() == ZField) {

            zValue = validateInput(ZField, zValue);

        }

        if (e.getSource() == gridSizeField) {

            try {

                int input = Integer.parseInt(gridSizeField.getText());
    
                if (!(input < 10 || input > 1000)) {
    
                    gridSize = input;
    
                    
                } else {
                    throw new NumberFormatException();
                }
    
            } catch (NumberFormatException ex) {
    
                JOptionPane.showMessageDialog(null, "Enter an integer between 10-1000");
    
            }


        }

    }

    private void configPanels() {

        optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(Color.BLACK);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 10, 0);
       


        optionsPanel.add(XLabel);
        optionsPanel.add(XField, gbc);
        optionsPanel.add(YLabel);
        optionsPanel.add(YField, gbc);
        optionsPanel.add(ZLabel);
        optionsPanel.add(ZField, gbc);
        
        optionsPanel.add(gridSizeLabel);
        optionsPanel.add(gridSizeField,gbc);

        textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.BLACK);

        String paragraph = "<html>A live cell with fewer than X live neighbours dies <br>"
                + "A live cell with X to Y live neighbours remains live <br>"
                + "A live cell with more than Y live neighbours dies <br>"
                + "A dead cell with exactly Z live neighbours becomes live</html>";

        JLabel rules = new JLabel(paragraph);

        Font customFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
        rules.setFont(customFont);
        rules.setForeground(Color.WHITE);

        textPanel.add(rules);

        add(optionsPanel);
        add(textPanel);

    }

    private void configBackToMenuButton() {

        backToGameMenu = new JButton("MAIN MENU");
        backToGameMenu.setBackground(Color.BLACK);
        backToGameMenu.setForeground(Color.WHITE);

        backToGameMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                setVisible(false);

                GameMenu mainMenu = GameMenu.getInstance();

                mainMenu.setVisible(true);

                //same action listener perhaps?
            }
        });

gbc.insets = new Insets(80, 0, 0, 0);
        optionsPanel.add(backToGameMenu, gbc);
    }

    private void explainButton(){
        hotKeyExplain = new JButton("SHORTCUT KEYS");
        hotKeyExplain.setBackground(Color.BLACK);
        hotKeyExplain.setForeground(Color.WHITE);

        hotKeyExplain.addActionListener(new ActionListener() {

String keyexpl = "<html>--SHORTCUT KEYS-- <br>"
                +"SPACE = Start/Stop Game<br>"
                +"Ctrl+M = Hide Button Panel<br>"
                +"Ctrl+S = Save Grid<br>"
                +"Ctrl+O = Load Grid<br>"
                +"Ctrl+Q = Exit Game<br>"
                +"PRESS 1 = DOT<br>"
                +"PRESS 2 = BIG DOT<br>"
                +"PRESS 3 = ERASE DOT<br>"
                +"PRESS 4 = BIG ERASE<br>"
                +"PRESS 5 = GLIDER<br>"
                +"PRESS 6 = TWICKER<br></html>";

            
            
            @Override
            public void actionPerformed(ActionEvent a) {

                
                JOptionPane.showMessageDialog(null, keyexpl);
                
                
            }
        });

        gbc.insets = new Insets(20, 0, 0, 0);
                optionsPanel.add(hotKeyExplain, gbc);



    }

}