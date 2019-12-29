package app;

import java.util.Arrays;

public class EquationOne extends Equation {

    public EquationOne(int sampleSize) {
        super(sampleSize);
    }

    public int getFitness(Member a, Member[] S) {

        int[] subsamples = getSubsampleIndexes(S.length);
        Member[] sub = getSubSample(S, subsamples);
        int sum = 0;
        for (int i = 0; i < sub.length; i++) {
            sum += score(a, sub[i]);
        }
        return sum;
    }

    public int score(Member a, Member b) {

        int sumA = Arrays.stream(a.getBits()[0]).sum();
        int sumB = Arrays.stream(b.getBits()[0]).sum();

        return sumA > sumB ? 1 : 0;
    }
}
