package main;
import renderer.Renderer;

public class Main {
    private static int speed = 100;
    public static void main(String[] args) {
        Renderer renderer = Renderer.getInstance();

        while (true) {
            renderer.updateGrid();
            try {
                Thread.sleep(speed); // Add a delay to observe the changes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void speedUp() {
        speed -= 10;
    }

    public static void slowDown() {
        speed += 10;
    }
}
