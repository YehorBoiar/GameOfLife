package logic;

/**
 * Represents the logic for updating the Game of Life grid based on 
 * specified rules.
 */
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

    /**
     * Updates the Game of Life grid based on the specified rules.
     *
     * @param grid The current grid state.
     * @return A new grid state after applying the rules.
     */
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
    
    /**
     * Counts the number of alive neighbors around a specified cell in the grid.
     *
     * @param grid The grid state.
     * @param row  The row index of the cell.
     * @param col  The column index of the cell.
     * @return The count of alive neighbors.
     */
    private static int countAliveNeighbors(boolean[][] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                int toroidalRow = toroidalIndex(i, rows);

                int toroidalCol = toroidalIndex(j, cols);

                if (!(toroidalRow == row && toroidalCol == col)) {
                    count += grid[toroidalRow][toroidalCol] ? 1 : 0;
                }

            }
        }

        return count;
    }

    /**
     * 
     * @param index row or column index of specified cell
     * @param max  number of rows/columns in grid
     * @return the 'wrapped around' index of cell if the specified cell is out of
     *         bounds
     */
    private static int toroidalIndex(int index, int max) {
        return (index % max + max) % max;
    }



    
}
