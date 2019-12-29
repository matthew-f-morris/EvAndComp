package app;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public abstract class Equation {

    public int sampleSize = 15;
    public SplittableRandom rand = new SplittableRandom();

    public Equation(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public abstract int getFitness(Member a, Member[] S);

    public abstract int score(Member a, Member b);

    public Member[] getSubSample(Member[] S, int[] indexes) {

        Member[] subSample = new Member[indexes.length];

        for (int i = 0; i < subSample.length; i++) {
            subSample[i] = S[indexes[i]].clone();
        }

        return subSample;
    }

    public int[] getSubsampleIndexes(int arraySize) {

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
}