package app;

import java.util.Arrays;

public class EquationOne extends Equation {

    private double dissimilarity = 0.0;
    private double shape = 1.0;

    public EquationOne(double dissimilarity) {
        this.dissimilarity = dissimilarity;
    }

    public int getFitness(Member a, Member[] S) { // [PASS]

        // a = member from pop
        // S = sample from other pop

        int sum = 0;
        for (int i = 0; i < S.length; i++) {
            sum += score(a, S[i]);
        }
        return sum;
    }

    public int score(Member a, Member b) { // [PASS]

        int sumA = Arrays.stream(a.getBits()[0]).sum();
        int sumB = Arrays.stream(b.getBits()[0]).sum();

        return sumA > sumB ? 1 : 0;
    }

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
