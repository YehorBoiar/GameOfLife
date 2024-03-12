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

//import renderer.GameMenu;
import javax.swing.*;
import java.awt.*;



public class OptionsMenu extends JFrame implements ActionListener {

    private static OptionsMenu theOptions;
    private JPanel optionsPanel;
    private JPanel textPanel;

    private JTextField XField;
    private JTextField YField;
    private JTextField ZField;
   
    private JLabel XLabel;
    private JLabel YLabel;
    private JLabel ZLabel;

    private JButton backToGameMenu;
    private GridBagConstraints gbc = new GridBagConstraints();
    

    // default values
    private int xValue = 2;
    private int yValue = 3;
    private int zValue = 3;

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

    // return singleton instance
    public static synchronized OptionsMenu getInstance() {
        if (theOptions == null) {
            theOptions = new OptionsMenu();
        }
        return theOptions;
    }

    private OptionsMenu() {

        configFrame();
        configTextFields();
        configPanel();
        configBackToMenuButton();

        add(optionsPanel);
        add(textPanel);

    }

    private void configFrame() {

        setTitle("Options Menu");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

    }

    private void configTextFields() {

        XLabel = new JLabel("X:");
        YLabel = new JLabel("Y:");
        ZLabel = new JLabel("Z:");

        Font labelFont = new Font("Arial", Font.BOLD, 19);

        JLabel[] labels = { XLabel, YLabel, ZLabel };

        for (JLabel label : labels) {

            label.setForeground(Color.WHITE);
            label.setPreferredSize(new Dimension(50, 30));
            label.setFont(labelFont);

        }

        XField = new JTextField("2");
        YField = new JTextField("3");
        ZField = new JTextField("3");

        JTextField[] fields = { XField, YField, ZField };

        for (JTextField field : fields) {

            field.addActionListener(this);
            field.addFocusListener(null);
            field.setPreferredSize(new Dimension(100, 30));

        }

        XField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (XField.getText().equals("2")) {
                    XField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
                if (XField.getText().isEmpty()) {
                    XField.setText("2");
                }
            }
        });

        YField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (YField.getText().equals("3")) {
                    YField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (YField.getText().isEmpty()) {
                    YField.setText("3");
                }
            }
        });

        ZField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ZField.getText().equals("3")) {
                    ZField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (ZField.getText().isEmpty()) {
                    ZField.setText("3");
                }
            }
        });

    }

    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == XField) {

            try {

                
                int input = Integer.parseInt(XField.getText());

                if (!(input < 0 || input > 8)) {

                   xValue = input ;
                }
                else {
                    throw new NumberFormatException();
                }

                

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(null, "Enter an integer between 0-8");

            }
           

        }

        if (e.getSource() == YField) {

            try {
                int input = Integer.parseInt(YField.getText());
               

                if (!(input < 0 || input > 8)) {

                   yValue = input ;
                }
                else {
                    throw new NumberFormatException();
                }

                

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(null, "Enter an integer"); // add range

            }

        }

        if (e.getSource() == ZField) {

            try {
                

                int input = Integer.parseInt(ZField.getText());

                if (!(input < 0 || input > 8)) {

                   zValue = input ;
                }
                else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(null, "Enter an integer");

            }

        }

    }

    

    private void configPanel() {

        optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(Color.BLACK);

        textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.BLACK);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 10, 0);

        optionsPanel.add(XLabel);
        optionsPanel.add(XField, gbc);
        optionsPanel.add(YLabel);
        optionsPanel.add(YField, gbc);
        optionsPanel.add(ZLabel);
        optionsPanel.add(ZField, gbc);

        String paragraph = "<html>A live cell with fewer than X live neighbours dies <br>"
                + "A live cell with X to Y live neighbours remains live <br>"
                + "A live cell with more than Y live neighbours dies <br>"
                + "A dead cell with exactly Z live neighbours becomes live</html>";

        JLabel text = new JLabel(paragraph);
        Font customFont = new Font("Arial", Font.PLAIN, 17);
        text.setFont(customFont);
        text.setForeground(Color.WHITE);

        textPanel.add(text);

    }

    private void configBackToMenuButton() {

        backToGameMenu = new JButton("Back to Game Menu");

        backToGameMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                setVisible(false);

                GameMenu gameMenu = GameMenu.getInstance();

                gameMenu.setVisible(true);
            }
        });

        optionsPanel.add(backToGameMenu, gbc);
    }

}