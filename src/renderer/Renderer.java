package renderer;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import input.MyKeyAdapter;
import input.MyMouseListener;
import input.MyMouseMotionAdapter;
import input.PanningHandler;
import input.ZoomHandler;
import logic.Logic;

/**
 * The Renderer class handles the visual representation of the Game of Life
 * grid.
 * It manages the JFrame, drawing squares on the panel, and updating the grid
 * based on user input.
 */
public class Renderer {
    private double zoomFactor = 1.0;
    private boolean gameState = false;
    private final Color BLACK = Color.BLACK;
    private final Color WHITE = Color.WHITE;
    private JFrame frame;
    private static Renderer instance;
    private int height = 100; // TODO - Handle the case when our grid becomes very large (e.g 1000x1000)
    private int width = 100;
    private boolean[][] grid = new boolean[height][width];
    private Logic logic = new Logic(); // instantiate Logic class
    private int lastMouseX = -1;
    private int lastMouseY = -1;
    private int panOffsetX = 0;
    private int panOffsetY = 0;


    /**
     * Private constructor to create a new instance of the Renderer class.
     * Initializes the JFrame and configures its settings.
     */
    private Renderer() {
        frame = new JFrame("Game of Life");
        configFrame();
    }

    /**
     * Configures the settings of the JFrame, including size, background color, and
     * event listeners.
     */
    private void configFrame() {
        frame.pack();
        frame.setSize(width * 10, height * 10);
        frame.getContentPane().setBackground(BLACK);

        frame.addMouseListener(new MyMouseListener(this));
        frame.addKeyListener(new MyKeyAdapter(this));
        frame.addMouseMotionListener(new MyMouseMotionAdapter(this));
        frame.addMouseMotionListener(new PanningHandler(this)); // TODO - make it work
        frame.addMouseWheelListener(new ZoomHandler(this));

        frame.add(new MyPanel());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    /**
     * Inner class representing the drawing panel inside the JFrame.
     */
    class MyPanel extends JPanel {
        public MyPanel() {
            setBackground(BLACK); // Set the background color to black
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawSquares(g);
        }
    }

    /**
     * Draws squares on the panel based on the current state of the grid.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void drawSquares(Graphics g) {
        int squareSize = (int) (10 * zoomFactor);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x = (int) (j * squareSize - panOffsetX);
                int y = (int) (i * squareSize - panOffsetY);

                if (grid[i][j]) {
                    g.setColor(WHITE);
                    g.fillRect(x, y, squareSize, squareSize);
                } else {
                    g.setColor(BLACK);
                    g.fillRect(x, y, squareSize, squareSize);
                }
            }
        }
    }

    /**
     * Updates the grid based on the Game of Life rules.
     * Repaints the frame to reflect the changes.
     */
    public void updateGrid() {
        if (gameState) {
            grid = logic.gridUpdate(grid);
            frame.repaint();
        }
    }

    /**
     * Retrieves the singleton instance of the Renderer class.
     * 
     * @return The Renderer instance.
     */
    public static synchronized Renderer getInstance() {
        if (instance == null) {
            instance = new Renderer();
        }
        return instance;
    }

    /**
     * Reverses the value of a specific element in the grid.
     * Used to toggle the state of cells based on user input.
     *
     * @param row    The row index of the grid element.
     * @param column The column index of the grid element.
     * @return The updated grid.
     */
    public boolean[][] reverseElement(int row, int column) {
        this.grid[row][column] = !grid[row][column];
        return this.grid;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public double getZoomFactor() {
        return this.zoomFactor;
    }

    public int getPanOffsetX() {
        return panOffsetX;
    }

    public void setPanOffsetX(int panOffsetX) {
        this.panOffsetX = panOffsetX;
    }

    public int getPanOffsetY() {
        return panOffsetY;
    }

    public void setPanOffsetY(int panOffsetY) {
        this.panOffsetY = panOffsetY;
    }

    public int getLastMouseX() {
        return lastMouseX;
    }

    public void setLastMouseX(int lastMouseX) {
        this.lastMouseX = lastMouseX;
    }

    public int getLastMouseY() {
        return lastMouseY;
    }

    public void setLastMouseY(int lastMouseY) {
        this.lastMouseY = lastMouseY;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return width;
    }

    public boolean getGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public boolean[][] getGrid() {
        return this.grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
        frame.repaint();
    }
}
