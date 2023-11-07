/* *****************************************************************************
 *  Name:              Alan Enriquez
 *  Last modified:     10/25/2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] percolationThreshold;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid input.");
        }
        this.trials = trials;
        percolationThreshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                test.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            }
            percolationThreshold[i] = (test.numberOfOpenSites() * 1.0) / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(percolationThreshold);
    }

    public double stddev() {
        return StdStats.stddev(percolationThreshold);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int userN = Integer.parseInt(args[0]);
        int userTrials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(userN, userTrials);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }
}
