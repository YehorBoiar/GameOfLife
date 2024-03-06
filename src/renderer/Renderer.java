package renderer;

import ui.ButtonPanel;
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
import ui.StandrardStructuresPanel;

/**
 * The Renderer class handles the visual representation of the Game of Life
 * grid.
 * It manages the JFrame, drawing squares on the panel, and updating the grid
 * based on user input.
 */
public class Renderer {
    private JPanel structuresPanel;
    private JPanel mainPanel;
    private double zoomFactor = 1.0;
    private boolean gameState = false;
    private boolean eraseElements = false;
    private final Color BLACK = Color.BLACK;
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
        frame.addMouseMotionListener(new PanningHandler(this)); 
        frame.addMouseWheelListener(new ZoomHandler(this));

        mainPanel = new MyPanel();
        mainPanel.add(new ButtonPanel(this));
        // structuresPanel = new StandrardStructuresPanel();
        // mainPanel.add(structuresPanel);
        frame.add(mainPanel);


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
            if (!gameState) {
                drawGrid(g);
            }
        }

    }

    /**
     * Draws a white grid on a panel
     * @param g
     */
    private void drawGrid(Graphics g) {
        int squareSize = (int) (10 * zoomFactor);
    
        // Draw vertical grid lines (columns)
        for (int i = 0; i <= width; i++) {
            int x = (int) (i * squareSize - panOffsetX);
            g.setColor(Color.GRAY);
            g.drawLine(x, 0, x, getHeight()*squareSize);
        }
    
        // Draw horizontal grid lines (rows)
        for (int j = 0; j <= height; j++) {
            int y = (int) (j * squareSize - panOffsetY);
            g.setColor(Color.GRAY);
            g.drawLine(0, y, getWidth()*squareSize, y);
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
                    // Set color of squares in the rainbow sequence
                    Color rainbowColor = getRainbowColor(i, j);
                    g.setColor(rainbowColor);
                    g.fillRect(x, y, squareSize, squareSize);
                } else {
                    g.setColor(BLACK);
                    g.fillRect(x, y, squareSize, squareSize);
                }
            }
        }
    }

        /**
     * Helper method to get the rainbow color based on the position in the grid.
     *
     * @param row    The row index of the grid element.
     * @param column The column index of the grid element.
     * @return Color representing the rainbow color.
     */
    private Color getRainbowColor(int row, int column) {
        float hue = (float) ((row * width + column) % 360) / 360.0f;
        return Color.getHSBColor(hue, 1.0f, 1.0f);
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
     * @param erase  If true, sets the element to false (erasing mode); if false, sets the element to true (drawing mode).
     * @return The updated grid after toggling the state of the specified element.
     */
    public boolean[][] reverseElement(int row, int column, boolean erase) {
        if (erase) {
            this.grid[row][column] = false;
        } else {
            this.grid[row][column] = true;
        }
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

    public boolean isEraseElements() {
        return eraseElements;
    }

    public void setEraseElements(boolean eraseElements) {
        this.eraseElements = eraseElements;
    }

    
}
