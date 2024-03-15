package ui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

/**
 * OptionsMenu class handles the generation of the options menu GUI
 * Takes and validates custom input from the player
 */
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

    // returns singleton instance
    public static synchronized OptionsMenu getInstance() {
        if (theOptions == null) {
            theOptions = new OptionsMenu();
        }
        return theOptions;
    }

    /**
     * Constructor in which all the components are invoked to be configured
     * 
     */
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

    /**
     * Configures JFrame settings and layout manager
     */
    private void configFrame() {

        setTitle("Options Menu");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

    }

    /**
     * This method creates new JLabels and customises them for a preferred look
     * 
     * @param labelName The text displayed in the JLabel
     * @return customised JLabel
     */
    private JLabel makeLabel(String labelName) {

        JLabel label = new JLabel(labelName, SwingConstants.CENTER);

        Font labelFont = new Font(Font.MONOSPACED, Font.BOLD, 19);

        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(110, 30));
        label.setFont(labelFont);

        return label;
    }

    /**
     * Creates new text fields, adds a focusListener and actionListener so that they
     * respond to 'Enter' button click
     * 
     * @param defaultValue The default x,y,z, values that are intially displayed in
     *                     the text field
     * @return Customised JTextField
     */
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

    /**
     * Validates the user input in the text fields so that they are integers within
     * set bounds; rejects invalid input so game can proceed
     * 
     * @param field        JTextField in which input is to be validated
     * @param defaultValue the value that is used if input is invalid
     * @return validated input
     */
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

    /**
     * Depending on text field into which input is entered, perform validation of
     * input and set the new input to pass into logic of the game
     */
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

    /**
     * Configures the settings of the two major JPanels in the frame
     * Adds all labels, text and buttons to suitable panel
     * Added to the JFrame
     */
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
        optionsPanel.add(gridSizeField, gbc);

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

    /**
     * Configures the Back to Main Menu button which returns the player to the Main
     * Menu
     */
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
            }
        });

        gbc.insets = new Insets(80, 0, 0, 0);
        optionsPanel.add(backToGameMenu, gbc);
    }

    /**
     * Configures the Short Key explanation button which when clicked displays
     * information regarding hot key functions
     */
    private void explainButton() {
        hotKeyExplain = new JButton("SHORTCUT KEYS");
        hotKeyExplain.setBackground(Color.BLACK);
        hotKeyExplain.setForeground(Color.WHITE);

        hotKeyExplain.addActionListener(new ActionListener() {

            String keyExplanation = "<html>--SHORTCUT KEYS-- <br>"
                    + "SPACE = Start/Stop Game<br>"
                    + "Ctrl+M = Hide Button Panel<br>"
                    + "Ctrl+S = Save Grid<br>"
                    + "Ctrl+O = Load Grid<br>"
                    + "Ctrl+Q = Exit Game<br>"
                    + "ENTER = Next step<br>"
                    + "ESCAPE = Main Menu<br>"
                    + "PRESS 1 = DOT<br>"
                    + "PRESS 2 = BIG DOT<br>"
                    + "PRESS 3 = ERASE DOT<br>"
                    + "PRESS 4 = BIG ERASE<br>"
                    + "PRESS 5 = GLIDER<br>"
                    + "PRESS 6 = TWICKER<br></html>";

            @Override
            public void actionPerformed(ActionEvent a) {

                JOptionPane.showMessageDialog(null, keyExplanation);

            }
        });

        gbc.insets = new Insets(20, 0, 0, 0);
        optionsPanel.add(hotKeyExplain, gbc);

    }

}