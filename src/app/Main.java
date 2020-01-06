package app;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        int target = 100;
        int dimentions = 2;

        Equation eq = new EquationThree();
        Selector select = new Selector(eq, sampleSize, popSize);
        Population p = new Population(select, popSize, target, dimentions);

        long startTime = System.nanoTime();
        p.run(600, true, 1);
        long endTime = System.nanoTime();

        System.out.println("Time to Run: " + (endTime - startTime));

        Member a = new Member(new int[][]{{0,0,0,0},{0,0,0,0}});
        Member b = new Member(new int[][]{{0,0,1,1},{1,1,0,0}});
        Member c = new Member(new int[][]{{1,1,1,1},{1,1,1,1}});
        Member d = new Member(new int[][]{{1,0,1,0},{0,1,0,1}});

        Member[] pop = new Member[]{a,b,c,d};
        int[][] scores = new int[pop.length][pop.length];
        
        for(int i = 0; i < scores.length; i++){
            for(int j = 0; j < scores[i].length; j++){
                scores[i][j] = select.hammingDistance(pop[i], pop[j]);
            }     
        }

        System.out.println(Arrays.deepToString(scores));
    }
}