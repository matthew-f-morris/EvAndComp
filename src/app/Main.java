package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 10;
        boolean hamming = true;
        double dissimilarity = 50.0;

        Selector select = new Selector(new EquationThree(dissimilarity), sampleSize, popSize, hamming);
        Population p = new Population(select, popSize, target, dimentions);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        long endTime = System.nanoTime();

        System.out.println("\nTime to Run: " + (endTime - startTime));
    }
}
