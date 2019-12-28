package app;

import java.util.SplittableRandom;

public class Selector {

    private SplittableRandom rand = new SplittableRandom();

    public Member select(Member[] pop){
        float sum = 0;
        float[] wheel = new float[pop.length];

        for(int i = 0; i < pop.length - 1; i++){
            sum += fitness(pop[i]);
            wheel[i] = sum;
        }

        double pick = rand.nextDouble(sum);

        int i = 0;
        while (wheel[i] >= pick){
            i++
        }

        return pop[i];

    }
}