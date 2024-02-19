public class Main {

    public static void main(String[] args) {
        Renderer renderer = Renderer.getInstance();

        while (true) {
            renderer.updateGrid();
            try {
                Thread.sleep(200); // Add a delay to observe the changes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
