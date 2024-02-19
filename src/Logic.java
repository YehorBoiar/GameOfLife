public class Logic {
    private int x = 2;
    private int y = 3;
    private int z = 3;
    

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean[][] gridUpdate(boolean[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] newGrid = new boolean[rows][cols];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int aliveNeighbors = countAliveNeighbors(grid, i, j);
    
                if (grid[i][j]) { // Check if alive cell would live, or it would die.
                    if (aliveNeighbors < x || aliveNeighbors > y) {
                        newGrid[i][j] = false;
                    } else {
                        newGrid[i][j] = true;
                    }
                } else {
                    if (aliveNeighbors == z) {
                        newGrid[i][j] = true;
                    }
                }
            }
        }
    
        return newGrid;
    }
    
    private int countAliveNeighbors(boolean[][] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
    
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && !(i == row && j == col)) {
                    count += grid[i][j] ? 1 : 0;
                }
            }
        }
    
        return count;
    }
    
}
