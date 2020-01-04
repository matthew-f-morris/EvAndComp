package app;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 50;
        int dimentions = 5;

        Equation eq = new EquationThree();
        Selector select = new Selector(eq, sampleSize);
        Population p = new Population(select, popSize, target, dimentions);

        p.run(600, true, 1);
    }
}