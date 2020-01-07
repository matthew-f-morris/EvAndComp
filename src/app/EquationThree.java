package app;

import java.util.Arrays;

public class EquationThree extends Equation {

    private double dissimilarity = 0.0;
    private double shape = 1.0;

    public EquationThree(double dissimilarity) {
        super(dissimilarity);
    }

    public int getFitness(Member a, Member[] S) {

        int sum = 0;
        for (Member si : S) {
            sum += score(a, si);
        }

        return sum;
    }

    public int score(Member Ma, Member Mb) {

        int[] sumA = new int[Ma.getDs()];
        int[] sumB = new int[Mb.getDs()];

        int[][] bitsA = Ma.getBits();
        int[][] bitsB = Mb.getBits();

        for (int i = 0; i < bitsA.length; i++) {
            sumA[i] = Arrays.stream(bitsA[i]).sum();
            sumB[i] = Arrays.stream(bitsB[i]).sum();
        }

        int best = -1;
        int diff = 100000;

        for (int i = 0; i < sumA.length; i++) {
            int score = Math.abs(sumA[i] - sumB[i]);
            if (score < diff) {
                best = i;
                diff = score;
            }
        }

        return sumA[best] > sumB[best] ? 1 : 0;
    }
}
