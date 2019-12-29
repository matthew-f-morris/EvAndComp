package app;

import java.util.Arrays;

public class EquationOne extends Equation {

    public int getFitness(Member a, Member[] S) {

        long start = System.nanoTime();

        int sum = 0;
        for (int i = 0; i < S.length; i++) {
            sum += score(a, S[i]);
        }

        long end = System.nanoTime();
        System.out.println("D: " + (end - start));
        return sum;
    }

    public int score(Member a, Member b) {

        int sumA = Arrays.stream(a.getBits()[0]).sum();
        int sumB = Arrays.stream(b.getBits()[0]).sum();

        return sumA > sumB ? 1 : 0;
    }
}
