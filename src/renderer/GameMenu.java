package renderer;

import main.Main;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GameMenu extends JFrame {

    private static GameMenu gameMenu;
    private JPanel menuPanel;

    private GameMenu() {

        setTitle("Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        configPanel();
        add(menuPanel);

    }

    private void configPanel() {

        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.BLACK);

        JButton startButton = new JButton("Start Game");
        startButton.setBackground(Color.WHITE);

        JButton exitButton = new JButton("Exit Game");
        exitButton.setBackground(Color.WHITE);

        JButton optionsButton = new JButton("Options");
        optionsButton.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.ipadx = 30;
        gbc.ipady = 30;
        gbc.insets = new Insets(0, 0, 10, 0);

        menuPanel.add(startButton, gbc);
        menuPanel.add(exitButton, gbc);
        menuPanel.add(optionsButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

                dispose();

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

    public static synchronized GameMenu getInstance() {
        if (gameMenu == null) {
            gameMenu = new GameMenu();
        }
        return gameMenu;
    }
}