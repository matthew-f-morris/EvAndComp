package app;

import java.util.Arrays;

public class EquationOne extends Equation {

    public EquationOne(int a) {
        super(a);
    }

    public int getFitness(Member a, Member[] S) {

        int sum = -1;
        for (Member si : S) {
            sum += score(a, si);
        }

        return sum;
    }

    private int score(Member a, Member b) {

        int[] bitsA = a.getBits()[0];
        int[] bitsB = b.getBits()[0];

        int sumA = Arrays.stream(bitsA).sum();
        int sumB = Arrays.stream(bitsB).sum();

        return sumA > sumB ? 1 : 0;
    }
}
