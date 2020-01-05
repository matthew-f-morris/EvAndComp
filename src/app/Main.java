package app;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 2;
        double probability = 0.05;

        Selector select1 = new Selector(new EquationOne(), sampleSize, popSize);
        Selector select2 = new Selector(new EquationTwo(), sampleSize, popSize);
        Selector select3 = new Selector(new EquationThree(), sampleSize, popSize);
        
        Population p = new Population(select1, select2, select3, popSize, target, dimentions, probability);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        long endTime = System.nanoTime();

        System.out.println("Time to Run: " + (endTime - startTime));
    }
}

// https://stackoverflow.com/questions/37836751/what-are-fitness-sharing-and-niche-count-in-evolutionary-computation
// use larger mutation rate + fitness sharing

// fitness sharing is when the fitness of an individual is scaled based on
// proximity to others
// good solns in densley populated regions given lower fitness (reduces high
// quality, high density solns)
// use hamming distance!