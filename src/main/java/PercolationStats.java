
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private int trials;
    private final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Invalid size of n or invalid trial #");

        this.trials = trials;
        thresholds = new double[trials];

        int oneMoreThanN = n + 1;
        double numOfSites = n * n;
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            int sitesOpened = 0;

            while (!percolation.percolates()) {
                boolean opened = false;
                while (!opened) {
                    int row = StdRandom.uniform(1, oneMoreThanN);
                    int col = StdRandom.uniform(1, oneMoreThanN);

                    if (!percolation.isOpen(row, col)) {
                        percolation.open(row, col);
                        opened = true;
                    }

                }
                sitesOpened++;
            }

            thresholds[trial] = sitesOpened / numOfSites;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        // PercolationStats percolationStats = new PercolationStats(2, 11);
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");
    }

}
