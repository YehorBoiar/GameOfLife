package main;

import javax.swing.Timer;
import renderer.Renderer;
import ui.GameMenu;
import ui.OptionsMenu;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
    private static int speed = 100;

    public static void main(String[] args) {

        OptionsMenu.getInstance();

        GameMenu menu = GameMenu.getInstance();
        menu.setVisible(true);

    }

    public static void startGame() {

        Renderer renderer = Renderer.getInstance();
        JFrame frame = renderer.getFrame();

        frame.setVisible(true);


        Timer timer = new Timer(speed, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                renderer.updateGrid();
            }

        });

        timer.start();

    }

    public static void speedUp() {
        if (speed >= 20) {
            speed -= 10;
        } else {
            System.out.println("You've reached the limit!");
        }
    }

    public static void slowDown() {
        speed += 10;
    }
}
