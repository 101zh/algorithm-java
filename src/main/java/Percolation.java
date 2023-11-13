
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.util.Arrays;

public class Percolation {

    private WeightedQuickUnionUF quickUF;
    private WeightedQuickUnionUF noBackWashQuickUF;
    private boolean[][] grid;
    private int[][] numberedGrid;
    private int len;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Invalid size of n or invalid trial #");
        grid = new boolean[n][n];
        numberedGrid = new int[n][n];
        int size = (n * n) + 2;
        quickUF = new WeightedQuickUnionUF(size);
        noBackWashQuickUF = new WeightedQuickUnionUF(size);
        virtualTop = quickUF.count() - 2;
        virtualBottom = quickUF.count() - 1;
        len = n;

        int curNumber = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                numberedGrid[i][j] = curNumber;
                curNumber++;
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row -= 1;
        col -= 1;
        throwException(isValid(row, col));
        grid[row][col] = true;

        if (row == 0) {
            quickUF.union(numberedGrid[row][col], virtualTop);
            noBackWashQuickUF.union(numberedGrid[row][col], virtualTop);
        }
        if (row == len - 1) {
            quickUF.union(numberedGrid[row][col], virtualBottom);
        }

        if (isValid(row - 1, col) && grid[row - 1][col]) {
            quickUF.union(numberedGrid[row][col], numberedGrid[row - 1][col]);
            noBackWashQuickUF.union(numberedGrid[row][col], numberedGrid[row - 1][col]);
        }
        if (isValid(row + 1, col) && grid[row + 1][col]) {
            quickUF.union(numberedGrid[row][col], numberedGrid[row + 1][col]);
            noBackWashQuickUF.union(numberedGrid[row][col], numberedGrid[row + 1][col]);
        }
        if (isValid(row, col - 1) && grid[row][col - 1]) {
            quickUF.union(numberedGrid[row][col], numberedGrid[row][col - 1]);
            noBackWashQuickUF.union(numberedGrid[row][col], numberedGrid[row][col - 1]);
        }
        if (isValid(row, col + 1) && grid[row][col + 1]) {
            quickUF.union(numberedGrid[row][col], numberedGrid[row][col + 1]);
            noBackWashQuickUF.union(numberedGrid[row][col], numberedGrid[row][col + 1]);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        throwException(isValid(row, col));

        return grid[row][col];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row -= 1;
        col -= 1;
        throwException(isValid(row, col));
        return noBackWashQuickUF.connected(numberedGrid[row][col], virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int openSites = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j]) {
                    openSites++;
                }
            }
        }
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUF.connected(virtualTop, virtualBottom);
    }

    private boolean isValid(int row, int col) {
        boolean isvalidRows = row >= 0 && row <= len - 1;
        boolean isvalidCols = col >= 0 && col <= len - 1;
        return isvalidCols && isvalidRows;
    }

    private static void throwException(boolean valid) {
        if (!valid) {
            throw new IllegalArgumentException("Row or Column is invalid");
        }
    }

    // test client (optional)
    // public static void main(String[] args) {
    // Percolation percolation = new Percolation(6);
    // System.out.println(percolation.quickUnionFind.count());
    // percolation.open(1, 6);
    // percolation.open(2, 6);
    // percolation.open(3, 6);
    // percolation.open(4, 6);
    // percolation.open(5, 6);
    // percolation.open(5, 5);
    // percolation.open(4, 4);
    // percolation.open(3, 4);
    // percolation.open(2, 4);
    // percolation.open(2, 3);
    // percolation.open(2, 2);
    // percolation.open(2, 1);
    // percolation.open(3, 1);
    // percolation.open(4, 1);
    // percolation.open(5, 1);
    // percolation.open(5, 2);
    // percolation.open(6, 2);
    // percolation.open(5, 4);

    // System.out.println(Arrays.deepToString(percolation.grid).replace("], ",
    // "]\n").replace("[[", "[").replace("true", "0").replace("false", "1"));
    // System.out.println(percolation.numberOfOpenSites());
    // System.out.println(percolation.percolates());

    // }

}
