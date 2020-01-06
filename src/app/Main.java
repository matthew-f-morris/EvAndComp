package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 2;
        boolean hamming = true;

        Selector select = new Selector(new EquationThree(), sampleSize, popSize, hamming);
        Population p = new Population(select, popSize, target, dimentions);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
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
