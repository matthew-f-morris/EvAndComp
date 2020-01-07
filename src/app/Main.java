package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 10;
        boolean hamming = true;
        boolean hammingMutate = false;
        boolean hof = false;
        double mutation = 0.005;

        Selector select = new Selector(new EquationTwo(), sampleSize, popSize, hamming, hof, target, dimentions);
        Population p = new Population(select, mutation, popSize, target, dimentions, hammingMutate, hof);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        p.toString();
        long endTime = System.nanoTime();

        System.out.println("\nTime to Run: " + (endTime - startTime));

        // Member a = new Member(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
        // Member b = new Member(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 } });
        // Member c = new Member(new int[][] { { 1, 1, 1, 1 }, { 0, 0, 0, 0 } });
        // Member d = new Member(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 0, 0 } });
        // Member e = new Member(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } });

        // Member[] pop = new Member[] { b, a, c, e, d };

        // System.out.println(Arrays.toString(select.getHammingScores(pop)));

    }
}
