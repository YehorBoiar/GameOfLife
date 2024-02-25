import java.awt.Color;
import java.awt.Graphics;
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
    private int lastMouseX = -1;
    private int lastMouseY = -1;

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

    public JFrame getFrame(){
        return this.frame;
    }

    public void setFrame(JFrame frame){
        this.frame = frame;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

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

        addMouseListeners();
        frame.addKeyListener(new MyKeyAdapter(this));

        frame.add(new MyPanel());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void addMouseListeners(){
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                lastMouseX = -1;
                lastMouseY = -1;
            }    
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }
        });
        
    }
    
    private void handleMouseDrag(MouseEvent e) {
        int x = e.getX() / 10;
        int y = (e.getY() - 40) / 10;

        if (x >= 0 && x < width && y >= 0 && y < height) {
            if (lastMouseX != -1 && lastMouseY != -1) {
                int deltaX = x - lastMouseX;
                int deltaY = y - lastMouseY;
                int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));

                for (int step = 0; step < steps; step++) {
                    int stepX = lastMouseX + step * deltaX / steps;
                    int stepY = lastMouseY + step * deltaY / steps;

                    grid[stepY][stepX] = !grid[stepY][stepX];
                }
            }

            lastMouseX = x;
            lastMouseY = y;
            frame.repaint(); 
        }
    }
    
    private void handleMouseClick(MouseEvent e) {
        int x = e.getX() / 10;
        int y = (e.getY() - 40) / 10;
        grid[y][x] = !grid[y][x];
        System.out.println("Mouse Clicked: " + x + "," + y);
        frame.repaint(); // Repaint the frame to update the drawing
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
