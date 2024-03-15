package ui;

import main.Main;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;
import java.awt.*;

/**
 * The GameMenu class generates the main menu JFrame and allows user to start game, exit game and go to options menu.
 */
public class GameMenu extends JFrame {

    private static GameMenu gameMenu;
    private JPanel menuPanel;

    /**
     * Private constructor which creates the singleton instance of the Game Menu class
     * Configures the JFrame
     */
    private GameMenu() {

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 1000);
        configPanel();
        add(menuPanel);

    }

    /**
     * Configures all the components of the main menu panel; including button, title and label
     */
    private void configPanel() {

        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.BLACK);

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);

        JButton optionsButton = new JButton("OPTIONS");
        optionsButton.setBackground(Color.BLACK);
        optionsButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.ipadx = 0;
        gbc.ipady = 200;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel Title = new JLabel("G A M E  O F  L I F E");

        Font customFont = new Font(Font.MONOSPACED, Font.BOLD, 70);
        Title.setFont(customFont);
        Title.setForeground(Color.WHITE);

        menuPanel.add(Title, gbc);
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.ipadx = 30;
        gbc.ipady = 30;
        menuPanel.add(startButton, gbc);
        menuPanel.add(exitButton, gbc);
        menuPanel.add(optionsButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

                setVisible(false);

                Main.startGame();

            }
        });

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                dispose();
                System.exit(0);

            }
        });

        optionsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                setVisible(false);
                OptionsMenu options = OptionsMenu.getInstance();

                options.setVisible(true);
            }
        });

    }

    /**
     * Retrieves the singleton instance of the Game Menu class
     * @return Game Menu instance
     */
    public static synchronized GameMenu getInstance() {
        if (gameMenu == null) {
            gameMenu = new GameMenu();
        }
        return gameMenu;
    }
}