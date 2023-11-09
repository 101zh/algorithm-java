
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF quickUnionFind;
    private boolean[][] grid;
    private int[][] numberedGrid;
    private int len;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new boolean[n][n];
        numberedGrid = new int[n][n];
        quickUnionFind = new WeightedQuickUnionUF((n * n) + 2);
        virtualTop = quickUnionFind.count() - 2;
        virtualBottom = quickUnionFind.count() - 1;
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
            quickUnionFind.union(numberedGrid[row][col], virtualTop);
        }
        if (row == len - 1) {
            quickUnionFind.union(numberedGrid[row][col], virtualBottom);
        }

        if (isValid(row - 1, col) && grid[row - 1][col]) {
            quickUnionFind.union(numberedGrid[row][col], numberedGrid[row - 1][col]);
        }
        if (isValid(row, col - 1) && grid[row][col - 1]) {
            quickUnionFind.union(numberedGrid[row][col], numberedGrid[row][col - 1]);
        }
        if (isValid(row, col + 1) && grid[row][col + 1]) {
            quickUnionFind.union(numberedGrid[row][col], numberedGrid[row][col + 1]);
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
        return quickUnionFind.connected(numberedGrid[row][col], virtualTop);
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
        return quickUnionFind.connected(virtualTop, virtualBottom);
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

    // // test client (optional)
    // public static void main(String[] args) {
    // Percolation percolation = new Percolation(4);
    // System.out.println(percolation.quickUnionFind.count());
    // percolation.open(1, 1);
    // percolation.open(2, 1);
    // percolation.open(2, 2);
    // percolation.open(2, 3);
    // percolation.open(3, 3);
    // percolation.open(3, 4);
    // percolation.open(4, 4);

    // System.out.println(Arrays.deepToString(percolation.grid).replace("], ",
    // "]\n").replace("[[", "["));
    // System.out.println(percolation.numberOfOpenSites());
    // System.out.println(percolation.percolates());

    // }

}
