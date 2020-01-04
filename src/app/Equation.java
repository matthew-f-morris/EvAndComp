package app;

import java.util.SplittableRandom;

public abstract class Equation {

    public SplittableRandom rand = new SplittableRandom();

    public abstract int getFitness(Member a, Member[] S); // S is array of membersd of other population [WORKS]

    public abstract int score(Member a, Member b); // calculate score against specific member of S

}