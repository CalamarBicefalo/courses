import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;

/**
 * @author José Carlos Valero
 * @since 8/09/15
 */
public class PercolationStats {

    private final double[] results;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        results = new double[T];
        Percolation cPercolation;
        for (int k = 0; k < T; k++) {
            cPercolation = new Percolation(N);
            int percolationIndex = 0;
            while (!cPercolation.percolates()){

                int i = getRandomIntegerBetweenOneAnd(N);
                int j = getRandomIntegerBetweenOneAnd(N);
                if(cPercolation.isOpen(i,j)){
                   continue;
                }
                cPercolation.open(i, j);
                percolationIndex++;
            }
            results[k] = (new Double(percolationIndex) / N*N);
        }
    }

    private int getRandomIntegerBetweenOneAnd(int N) {
        return StdRandom.uniform(1,N + 1);
    }

    /**
     *
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /**
     *
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
//        return mean() - 1.96*;
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return 0d;
    }

    public static void main(String[] args) {
    }


}
