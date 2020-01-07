package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 10;
        boolean hammingMutate = false;
        double mutation = 0.005;
        double dissimilarity = 100.0;

        Selector select = new Selector(new EquationThree(dissimilarity), sampleSize, popSize, false, target,
                dimentions);
        Population p = new Population(select, mutation, popSize, target, dimentions, hammingMutate, false);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        long endTime = System.nanoTime();

        System.out.println("\nTime to Run: " + (endTime - startTime));

        // Member a = new Member(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 0,
        // 1, 0 } });
        // Member b = new Member(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 0,
        // 1, 0 } });
        // Member c = new Member(new int[][] { { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 1, 0,
        // 1, 0 } });
        // Member d = new Member(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, { 1, 0,
        // 1, 0 } });
        // Member e = new Member(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1,
        // 1, 1 } });

        // Member[] pop = new Member[] { b, a, c, e, d };

        // EquationThree eq3 = new EquationThree();
        // System.out.println("Fitness: " + eq3.getFitnessSharing(d, pop));

    }
}
