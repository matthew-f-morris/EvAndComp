package app;

import java.util.Arrays;

public class EquationTwo extends Equation {

    private double dissimilarity = 0.0;
    private double shape = 1.0;

    public EquationTwo(double dissimilarity) {
        this.dissimilarity = dissimilarity;
    }

    public int getFitness(Member a, Member[] S) { // [WORKS]

        int sum = 0;
        for (Member si : S) {
            sum += score(a, si);
        }

        return sum;
    }

    public int score(Member Ma, Member Mb) { // [WORKS]

        int[] sumA = new int[Ma.getDs()];
        int[] sumB = new int[Mb.getDs()];

        int[][] bitsA = Ma.getBits();
        int[][] bitsB = Mb.getBits();

        for (int i = 0; i < bitsA.length; i++) {
            sumA[i] = Arrays.stream(bitsA[i]).sum();
            sumB[i] = Arrays.stream(bitsB[i]).sum();
        }

        int best = -1;
        int diff = 0;

        for (int i = 0; i < sumA.length; i++) {
            int score = Math.abs(sumA[i] - sumB[i]);
            if (score > diff) {
                best = i;
                diff = score;
            }
        }

        if (best == -1)
            return 0;
        return sumA[best] > sumB[best] ? 1 : 0;
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
