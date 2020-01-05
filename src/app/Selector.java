package app;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Selector {

    private SplittableRandom rand = new SplittableRandom();
    private Equation eq = null;
    public int sampleSize = 15;
    private int popSize;

    public Selector(Equation eq, int sampleSize, int popSize) {
        this.eq = eq;
        this.sampleSize = sampleSize;
        this.popSize = popSize;
    }

    public Member[] selectPop(Member[] pop, Member[] otherPop) { // [PASS]

        Member[] newPop = new Member[pop.length];

        int sum = 1;
        int[] wheel = new int[newPop.length];

        for (int i = 0; i < pop.length; i++) {
            sum += eq.getFitness(pop[i], this.getSubSample(otherPop));
            wheel[i] = sum;
        }

        for (int i = 0; i < pop.length; i++) {

            double pick;
            if (sum == 0)
                pick = rand.nextDouble(sum + 1);
            else
                pick = rand.nextDouble(sum);
            int j = 0;
            while (wheel[j] < pick) {
                j++;
            }

            newPop[i] = pop[j].clone();
        }

        return newPop;
    }

    public double getSub(Member[] pop) {

        int sum = 0;
        for (int i = 0; i < pop.length; i++)
            sum += eq.getFitness(pop[i], this.getSubSample(pop));
        return (double) sum / pop.length;
    }

    public double getSub(Member[] pop, Member[] other) {

        int sum = 0;
        for (int i = 0; i < pop.length; i++)
            sum += eq.getFitness(pop[i], this.getSubSample(other));
        return (double) sum / pop.length;
    }

    public Member[] getSubSample(Member[] S) { // [PASS]

        int[] indexes = this.getSubsampleIndexes(S.length);
        Member[] subSample = new Member[indexes.length];

        for (int i = 0; i < subSample.length; i++) {
            subSample[i] = S[indexes[i]].clone();
        }

        return subSample;
    }

    public int[] getSubsampleIndexes(int arraySize) { // [PASS]

        // return array of 15 random indexes for members with no duplicates
        int[] newIndexes = new int[sampleSize];
        List<Integer> indexes = new ArrayList<Integer>();

        for (int i = 0; i < arraySize; i++)
            indexes.add(i);

        for (int i = 0; i < sampleSize; i++) {
            int r = rand.nextInt(indexes.size());
            newIndexes[i] = indexes.get(r);
            indexes.remove(r);
        }

        return newIndexes;
    }

    public void incSample(int inc) {
        if (sampleSize + inc < popSize) {
            this.sampleSize += inc;
            System.out.println("SS: " + this.sampleSize);
        }
    }

    public void decSample(int dec) {
        if (sampleSize - dec > 0) {
            this.sampleSize -= dec;
            System.out.println("SS: " + this.sampleSize);
        }
    }
}