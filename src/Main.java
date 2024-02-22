public class Main {
    private int speed = 100;
    public static void main(String[] args) {
        Renderer renderer = Renderer.getInstance();

        while (true) {
            renderer.updateGrid();
            try {
                Thread.sleep(100); // Add a delay to observe the changes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
