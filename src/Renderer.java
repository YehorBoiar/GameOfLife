import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Renderer {
    private boolean gameState = false;
    private final Color BLACK = Color.BLACK;
    private final Color WHITE = Color.WHITE;
    private JFrame frame;
    private static Renderer instance;
    private int height = 100;
    private int width = 100;
    private boolean[][] grid = new boolean[height][width];
    private Logic logic = new Logic(); // instantiate Logic class
    private int lastMouseX = -1;
    private int lastMouseY = -1;



    private Renderer() {
        frame = new JFrame("Game of Life");
        configFrame();
    }

    private void configFrame() {
        frame.setSize(width * 10, height * 10);
        frame.getContentPane().setBackground(BLACK);

        frame.addMouseListener(new MyMouseListener(this));
        frame.addKeyListener(new MyKeyAdapter(this));
        frame.addMouseMotionListener(new MyMouseMotionAdapter(this));

        frame.add(new MyPanel());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

    public boolean[][] reverseElement(int row, int column){
        this.grid[row][column] = !grid[row][column];
        return this.grid;
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

    public JFrame getFrame(){
        return this.frame;
    }

    public void setFrame(JFrame frame){
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
