/* *****************************************************************************
 *  Name:              Alan Enriquez
 *  Last modified:     10/24/2023
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int sideLength;
    private int topIndex = 0;
    private int bottomIndex;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        sideLength = n;
        bottomIndex = (n * n) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > sideLength) {
            throw new IllegalArgumentException("Row value is outside of range");
        }
        if (col < 1 || col > sideLength) {
            throw new IllegalArgumentException("Column value is outside of range");
        }

        if (!isOpen(row, col)) {
            openSites++;
            grid[row - 1][col - 1] = true;

            // Connect to top virtual point if on first row
            if (row == 1) {
                uf.union(getUFIndex(row, col), topIndex);
            }
            // Connect to bottom virtual point if on last row
            if (row == sideLength) {
                uf.union(getUFIndex(row, col), bottomIndex);
            }

            // Check the top neighbor
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(getUFIndex(row, col), getUFIndex(row - 1, col));
            }
            // Check the bottom neighbor
            if (row < sideLength && isOpen(row + 1, col)) {
                uf.union(getUFIndex(row, col), getUFIndex(row + 1, col));
            }

            // Check the left neighbor
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(getUFIndex(row, col), getUFIndex(row, col - 1));
            }
            // Check the right neighbor
            if (col < sideLength && isOpen(row, col + 1)) {
                uf.union(getUFIndex(row, col), getUFIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > sideLength) {
            throw new IllegalArgumentException("Row value is outside of range");
        }
        if (col < 1 || col > sideLength) {
            throw new IllegalArgumentException("Column value is outside of range");
        }

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > sideLength) {
            throw new IllegalArgumentException("Row value is outside of range");
        }
        if (col < 1 || col > sideLength) {
            throw new IllegalArgumentException("Column value is outside of range");
        }

        return (uf.find(getUFIndex(row, col)) == uf.find(topIndex));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topIndex) == uf.find(bottomIndex);
    }

    // (1, 1) should equal to 1 after converting
    // (2, 2) should equal to 7 after converting
    private int getUFIndex(int row, int col) {
        return (sideLength * (row - 1)) + col;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
