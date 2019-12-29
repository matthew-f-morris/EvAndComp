package app;

import java.util.SplittableRandom;

public class Selector {

    private SplittableRandom rand = new SplittableRandom();
    private Equation eq = null;

    public Selector(Equation eq) {
        this.eq = eq;
    }

    public Member[] selectPop(Member[] pop, Member[] otherPop) {

        Member[] newPop = new Member[pop.length];
        Member[] S = eq.getSubSample(otherPop, eq.getSubsampleIndexes(otherPop.length));

        int sum = 0;
        int[] wheel = new int[newPop.length];

        for (int i = 0; i < pop.length; i++) {
            sum += eq.getFitness(pop[i], S);
            wheel[i] = sum;
        }

        for (int i = 0; i < pop.length; i++) {
            double pick = rand.nextDouble(sum);
            int j = 0;
            while (wheel[j] <= pick) {
                j++;
            }

            newPop[i] = pop[j].clone();
        }

        return newPop;
    }
}

// for (int index : indexes) {
// boolean exists = false;
// int r = rand.nextInt(arraySize);
// for (int i : indexes) {
// if (i == r)
// exists = true;
// }
// }

// Random rnd = ThreadLocalRandom.current();
// for (int i = array.length - 1; i > 0; i--) {
// int index = rnd.nextInt(i + 1);
// Member a = array[index];
// array[index] = array[i];
// array[i] = a;
// }
// }