package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 2;

        Population p = new Population(new Selector(new EquationThree(), sampleSize, popSize), popSize, target,
                dimentions);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        long endTime = System.nanoTime();

        System.out.println("Time to Run: " + (endTime - startTime));
    }
}