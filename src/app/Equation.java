package app;

import java.util.SplittableRandom;

public abstract class Equation {

    private double dissimilarity = 0.0;
    private double shape = 1.0;

    public Equation(double dissimilarity) {
        this.dissimilarity = dissimilarity;
    }

    public SplittableRandom rand = new SplittableRandom();

    public abstract int getFitness(Member a, Member[] S); // S is array of membersd of other population [WORKS]

    public abstract int score(Member a, Member b); // calculate score against specific member of S

    public double getFitnessSharing(Member a, Member[] S) {

        double sum = 0.0;
        for (int i = 0; i < S.length; i++) {
            sum += sh(hammingDistance(a, S[i]));
        }

        return sum;
    }

    public double sh(int dist) {
        if (dist > dissimilarity)
            return 0.0;
        else
            return 1.0 - (double) Math.pow((double) dist / dissimilarity, shape);
    }

    public int hammingDistance(Member a, Member b) {

        // if positive, A is better, else B is better

        int overall = 0;
        int[][] bitsA = a.getBits();
        int[][] bitsB = b.getBits();

        for (int i = 0; i < bitsA.length; i++) {
            int partial = 0;
            for (int j = 0; j < bitsA[i].length; j++) {
                int x = bitsA[i][j];
                int y = bitsB[i][j];
                if (x > y)
                    partial++;
                else if (x < y)
                    partial--;
            }

            overall += partial;
        }

        return overall;
    }

}