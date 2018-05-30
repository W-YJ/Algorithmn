package assignment1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	private static final double CONFIDENCE_95 = 1.96;
	private final int trials;
	private final double[] means;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		Percolation percolation;
		this.trials = trials;
		means = new double[trials];

		for (int i = 0; i < means.length; i++) {
			percolation = new Percolation(n);
			while (!percolation.percolates()) {
				percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
			}
			int grids = percolation.numberOfOpenSites();
			double p = (double) grids / (n * n);
			means[i] = p;
		}
	}

	// sample mean of assignment1.percolation threshold
	public double mean() {
		return StdStats.mean(means);
	}

	// sample standard deviation of assignment1.percolation threshold
	public double stddev() {
		return StdStats.stddev(means);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - CONFIDENCE_95 / Math.sqrt(trials);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + CONFIDENCE_95 / Math.sqrt(trials);
	}

	// test client (described below)
	public static void main(String[] args) {
		if (Integer.parseInt(args[0]) != Integer.parseInt(args[2])) {
			throw new IllegalArgumentException();
		}

		int i = 2;
		while (i < 20) {
			Stopwatch stopWatch = new Stopwatch();
			PercolationStats percolationStats = new PercolationStats(50, 10 * i);
			System.out.println("mean = " + percolationStats.mean());
			System.out.println("stddev = " + percolationStats.stddev());
			System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
					+ percolationStats.confidenceHi() + "]");
			System.out.println(stopWatch.elapsedTime());
			i = i * 2;
		}

	}
}
