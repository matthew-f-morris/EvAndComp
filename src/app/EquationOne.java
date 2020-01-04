package app;

import java.util.Arrays;

public class EquationOne extends Equation {

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
}
