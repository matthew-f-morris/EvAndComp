package app;

import java.util.SplittableRandom;

public abstract class Population {

    public Equation eq;
    public Member[] pop1;
    public Member[] pop2;
    public boolean print = false;
    public SplittableRandom rand = new SplittableRandom();

    public Population(int size, boolean print, Equation eq, int a, int dimention) {

        this.eq = eq;
        this.print = print;

        pop1 = new Member[size];
        pop2 = new Member[size];
        for (int i = 0; i < size; i++) {
            pop1[i] = new Member(a, dimention);
            pop2[i] = new Member(a, dimention);
        }
    }

    protected int random() {
        return rand.nextInt(pop1.length);
    }
}