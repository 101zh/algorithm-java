package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.util.Arrays;

public class Percolation {

    private WeightedQuickUnionUF quickUF;
    private WeightedQuickUnionUF noBackWashQuickUF;
    private boolean[][] grid;
    private int len;
    private int openSites;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Invalid size of n or invalid trial #");
        grid = new boolean[n][n];
        int lenSquared = (n * n) + 2;
        quickUF = new WeightedQuickUnionUF(lenSquared);
        noBackWashQuickUF = new WeightedQuickUnionUF(lenSquared);
        virtualTop = quickUF.count() - 2;
        virtualBottom = quickUF.count() - 1;
        len = n;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row -= 1;
        col -= 1;
        throwException(isValid(row, col));

        if (grid[row][col]) {
            return;
        }
        openSites++;
        grid[row][col] = true;

        int flatIndex = giveFlatIndex(row, col);

        if (row == 0) {
            quickUF.union(flatIndex, virtualTop);
            noBackWashQuickUF.union(flatIndex, virtualTop);
        }
        if (row == len - 1) {
            quickUF.union(flatIndex, virtualBottom);
        }

        if (isValid(row - 1, col) && grid[row - 1][col]) {
            int up = giveFlatIndex(row - 1, col);
            quickUF.union(flatIndex, up);
            noBackWashQuickUF.union(flatIndex, up);
        }
        if (isValid(row + 1, col) && grid[row + 1][col]) {
            int down = giveFlatIndex(row + 1, col);
            quickUF.union(flatIndex, down);
            noBackWashQuickUF.union(flatIndex, down);
        }
        if (isValid(row, col - 1) && grid[row][col - 1]) {
            int left = giveFlatIndex(row, col - 1);
            quickUF.union(flatIndex, left);
            noBackWashQuickUF.union(flatIndex, left);
        }
        if (isValid(row, col + 1) && grid[row][col + 1]) {
            int right = giveFlatIndex(row, col + 1);
            quickUF.union(flatIndex, right);
            noBackWashQuickUF.union(flatIndex, right);
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
        int flatIndex = giveFlatIndex(row, col);
        return noBackWashQuickUF.find(flatIndex) == noBackWashQuickUF.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUF.find(virtualTop) == quickUF.find(virtualBottom);
    }

    private int giveFlatIndex(int row, int col) {
        return (len * row) + col;
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

    // percolation.open(1, 1);
    // percolation.open(2, 2);
    // percolation.open(3, 3);
    // percolation.open(4, 4);

    // System.out.println(Arrays.deepToString(percolation.grid).replace("], ",
    // "]\n").replace("[[", "[").replace("true", "0").replace("false", "1"));
    // System.out.println(percolation.numberOfOpenSites());
    // System.out.println(percolation.percolates());

    // }

}
