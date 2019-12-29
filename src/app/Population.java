package app;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Population {

    public Equation eq;
    public Member[] pop1;
    public Member[] pop2;
    public SplittableRandom rand = new SplittableRandom();

    public Population(Equation eq, int size, int target, int dimention) {

        this.eq = eq;

        pop1 = new Member[size];
        pop2 = new Member[size];
        for (int i = 0; i < size; i++) {
            pop1[i] = new Member(target, dimention);
            pop2[i] = new Member(target, dimention);
        }
    }

    protected int random() {
        return rand.nextInt(pop1.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(pop1);
    }
}