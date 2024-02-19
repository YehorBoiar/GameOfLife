import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Renderer {
    private boolean gameState = false;
    private final Color BLACK = Color.BLACK;
    private final Color WHITE = Color.WHITE;
    private JFrame frame;
    private static Renderer instance;
    private static int height = 100;
    private static int width = 100;
    private static boolean[][] grid = new boolean[height][width];
    private Logic logic = new Logic(); // instantiate Logic class

    public boolean getGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public boolean[][] getGrid() {
        return Renderer.grid;
    }

    public void setGrid(boolean[][] grid) {
        Renderer.grid = grid;
        frame.repaint();
    }

    private Renderer() {
        frame = new JFrame("Game of Life");
        configFrame();
    }

    private void configFrame() {
        frame.setSize(width * 10, height * 10);
        frame.getContentPane().setBackground(BLACK);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    toggleGameState();
                }
            }
        });
        frame.add(new MyPanel());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void toggleGameState() {
        gameState = !gameState;
        System.out.println("Game State toggled to: " + gameState);
    }

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

    private void handleMouseClick(MouseEvent e) {
        int x = e.getX() / 10;
        int y = (e.getY() - 40) / 10;
        grid[y][x] = !grid[y][x];
        System.out.println("Mouse Clicked: " + x + "," + y);
        frame.repaint(); // Repaint the frame to update the drawing
    }

    public void drawSquares(Graphics g) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j]) {
                    g.setColor(WHITE);
                    g.fillRect(j * 10, i * 10, 10, 10);
                } else {
                    g.setColor(BLACK);
                    g.fillRect(j * 10, i * 10, 10, 10);
                }
            }
        }
    }

    public void updateGrid() {
        if (gameState) {
            grid = logic.gridUpdate(grid);
            frame.repaint();
        }
    }

    public static synchronized Renderer getInstance() {
        if (instance == null) {
            instance = new Renderer();
        }
        return instance;
    }
}
