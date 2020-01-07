package app;

import java.util.Random;

public class Hall {

    public Member[] hof;
    private int[] fitnesses;

    public Hall(int maxSize, int target, int dimension) {

        hof = new Member[maxSize];
        fitnesses = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            Member m = new Member(target, dimension);
            hof[i] = m;
            fitnesses[i] = 0;
        }
    }

    public void check(Member m, int fitness) {
        for (int i = 0; i < fitnesses.length; i++) {
            if (fitnesses[i] < fitness) {
                shift(i);
                hof[i] = m.clone();
                fitnesses[i] = fitness;
                break;
            } else
                continue;
        }
    }

    public void shift(int i) {
        for (int j = fitnesses.length - 1; j > i; j--) {
            if (j != -1) {
                fitnesses[j] = fitnesses[j - 1];
                hof[j] = hof[j - 1];
            }
            continue;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < fitnesses.length; i++) {
            sb.append(fitnesses[i] + " " + hof[i].toString() + ", ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}