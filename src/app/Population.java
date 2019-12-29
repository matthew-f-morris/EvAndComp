package app;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Population {

    private SplittableRandom rand = new SplittableRandom();
    private Equation eq;
    private Selector select;
    private double probability;
    public Member[] pop1;
    public Member[] pop2;

    public Population(Equation eq, Selector select, double probability, int popSize, int target, int dimention) {

        this.eq = eq;
        this.select = select;
        this.probability = probability;

        pop1 = new Member[popSize];
        pop2 = new Member[popSize];
        for (int i = 0; i < popSize; i++) {
            pop1[i] = new Member(target, dimention, probability);
            pop2[i] = new Member(target, dimention, probability);
        }

        for (int i = 0; i < popSize; i++) {
            pop1[i].objectiveFitness();
        }

        for (int i = 0; i < popSize; i++) {
            pop2[i].objectiveFitness();
        }
    }

    public Population(Member[] pop1, Member[] pop2) {
        this.pop1 = pop1;
        this.pop2 = pop2;
        this.eq = null;
        this.probability = 0.005;
    }

    public void run() {

        int generation = 0;

        while (generation < 10) {

            Member[] newPop1 = select.selectPop(pop1);
            Member[] newPop2 = select.selectPop(pop2);

            for (Member m : newPop1) {
                m.mutate();
            }

            for (Member m : newPop2) {
                m.mutate();
            }

            generation++;
        }
    }

    protected int random() {
        return rand.nextInt(pop1.length);
    }

    public double getASF1() {
        return 0.0;
    }

    public double getASF2() {
        return 0.0;
    }

    public void getOBF() {

    }

    @Override
    public String toString() {
        return Arrays.toString(pop1) + "\n\n\n" + Arrays.toString(pop2);
    }
}