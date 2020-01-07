package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SplittableRandom;

public class Selector {

    private SplittableRandom rand = new SplittableRandom();
    private Equation eq = null;
    public int sampleSize = 15;
    private int popSize;
    private boolean hamming;

    public Selector(Equation eq, int sampleSize, int popSize, boolean hamming, boolean hof, int target, int dimension) {
        this.eq = eq;
        this.sampleSize = sampleSize;
        this.popSize = popSize;
        this.hamming = hamming;
    }

    public Member[] selectPop(Member[] pop, Member[] otherPop) { // [PASS]

        Member[] newPop = new Member[pop.length];

        int sum = 1;
        int[] wheel = new int[newPop.length];

        for (int i = 0; i < pop.length; i++) {
            sum += eq.getFitness(pop[i], this.getSubSample(otherPop, hamming));
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

    public Member[] selectPopHoF(Member[] pop, Hall otherPop) { // [PASS]

        Member[] newPop = new Member[pop.length];

        int sum = 1;
        int[] wheel = new int[newPop.length];

        for (int i = 0; i < pop.length; i++) {

            int fit = eq.getFitness(pop[i], this.getSubSample(otherPop.hof, hamming));
            sum += fit;
            otherPop.check(pop[i], fit);
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

    public Member[] getSubSample(Member[] S, boolean hamming) { // [PASS]

        int[] indexes;
        if (hamming)
            indexes = this.getHammingScores(S);
        else
            indexes = this.getSubsampleIndexes(S.length);

        Member[] subSample = new Member[indexes.length];

        for (int i = 0; i < subSample.length; i++) {
            subSample[i] = S[indexes[i]].clone();
        }

        return subSample;
    }

    private int[] getSubsampleIndexes(int arraySize) { // [PASS]

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

    public int[] getHammingScores(Member[] pop) {

        int[][] scores = new int[pop.length][2];

        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores.length; j++) {
                if (i != j) {
                    int score = hammingDistance(pop[i], pop[j]);
                    if (score < 0) {
                        scores[i][1] -= 1;
                    } else if (score > 0) {
                        scores[i][1] += 1;
                    }
                }
            }

            scores[i][1] = Math.abs(scores[i][1]);
            scores[i][0] = i;
        }

        Arrays.sort(scores, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        int[] sorted = new int[scores.length];

        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = scores[sorted.length - 1 - i][0];
        }

        return sorted;
    }

    public int hammingDistance(Member a, Member b) {

        // if positive, A is better, else B is better

        int overall = 0;
        int[][] bitsA = a.getBits();
        int[][] bitsB = b.getBits();

        for (int i = 0; i < bitsA.length; i++) {
            int partial = 0;
            for (int j = 0; j < bitsA[i].length; j++) {
                int x = bitsA[i][j];
                int y = bitsB[i][j];
                if (x > y)
                    partial++;
                else if (x < y)
                    partial--;
            }

            overall += partial;
        }

        return overall;
    }

    public double getSub(Member[] pop, Member[] other) {

        int sum = 0;
        for (int i = 0; i < pop.length; i++)
            sum += eq.getFitness(pop[i], this.getSubSample(other, false));
        return (double) sum / pop.length;
    }
}