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

/**
 * The Renderer class handles the visual representation of the Game of Life
 * grid.
 * It manages the JFrame, drawing squares on the panel, and updating the grid
 * based on user input.
 */
public class Renderer {
    private JPanel buttonPanel;
    private MyMouseListener mouseListener;
    private JPanel mainPanel;
    private double zoomFactor = 1.0;
    private boolean gameState = false;
    private boolean showButtons = true;
    private final Color BLACK = Color.BLACK;
    private JFrame frame;
    private static Renderer instance;
    private int rows = 100; // TODO - Handle the case when our grid becomes very large (e.g 1000x1000)
    private int cols = 100;
    private boolean[][] grid = new boolean[rows][cols];
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
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(BLACK);

        mouseListener = new MyMouseListener(this);
        frame.addMouseListener(mouseListener);
        frame.addKeyListener(new MyKeyAdapter(this));
        frame.addMouseMotionListener(new MyMouseMotionAdapter(this));
        frame.addMouseMotionListener(new PanningHandler(this));
        frame.addMouseWheelListener(new ZoomHandler(this));

        mainPanel = new MyPanel();
        buttonPanel = new ButtonPanel(this);
        mainPanel.add(buttonPanel);
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
     * 
     * @param g
     */
    private void drawGrid(Graphics g) {
        int squareSize = (int) (10 * zoomFactor);

        // Draw vertical grid lines (columns)
        for (int i = 0; i <= cols; i++) {
            int x = (int) (i * squareSize - panOffsetX);
            g.setColor(Color.GRAY);
            g.drawLine(x, 0, x, cols * squareSize);
        }

        // Draw horizontal grid lines (rows)
        for (int j = 0; j <= rows; j++) {
            int y = (int) (j * squareSize - panOffsetY);
            g.setColor(Color.GRAY);
            g.drawLine(0, y, rows * squareSize, y);
        }
    }

    /**
     * Draws squares on the panel based on the current state of the grid.
     *
     * @param g The Graphics object used for drawing.
     */
    public void drawSquares(Graphics g) {
        int squareSize = (int) (10 * zoomFactor);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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
        float hue = (float) ((row * cols + column) % 360) / 360.0f;
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
 * Reverses the values of a specified array of elements in the grid.
 * Used to toggle the state of cells based on user input.
 *
 * @param startRow The starting row index of the grid elements.
 * @param startColumn The starting column index of the grid elements.
 * @param elements 2D array of elements to reverse.
 * @return The updated grid after toggling the state of the specified elements.
 */
public boolean[][] reverseElements(int startRow, int startColumn, boolean[][] elements) {
    for (int i = 0; i < elements.length; i++) {
        for (int j = 0; j < elements[i].length; j++) {
            int row = startRow + i;
            int column = startColumn + j;

            // Check if the indices are within the bounds of the grid
            if (row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[row].length) {
                this.grid[row][column] = elements[i][j];
            }
        }
    }
    return this.grid;
}
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public boolean isShowButtons() {
        return showButtons;
    }

    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
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

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return cols;
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

    public MyMouseListener getMouseListener() {
        return mouseListener;
    }

}
