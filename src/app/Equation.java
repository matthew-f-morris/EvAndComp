package app;

public abstract class Equation {

    public abstract int getFitness(Member a, Member[] S);

    public abstract int score(Member a, Member b);
}