package app;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Member {

    private int[][] bits;
    private int dimentions;
    private static SplittableRandom rand = new SplittableRandom();
    private final double prob;

    public Member(int size, int dimention, double prob) {

        if (size % dimention != 0) {
            System.err.println("Dimention was not a multiple of size!");
            this.bits = new int[1][1];
            this.dimentions = 1;
            this.prob = prob;
        } else {
            this.bits = new int[dimention][size / dimention];
            this.dimentions = dimention;
            this.prob = prob;
        }
    }

    public Member(int[][] bits) {
        this.bits = bits;
        this.dimentions = bits.length;
        this.prob = 0.005;
    }

    public int[][] getBits() {
        return this.bits;
    }

    public void setBits(int[][] newBits) {
        this.bits = newBits;
    }

    public int getDs() {
        return dimentions;
    }

    public void mutate() {

        for (int i = 0; i < this.bits.length; i++)
            for (int j = 0; j < this.bits[i].length; j++)
                if (rand.nextDouble() <= prob)
                    this.bits[i][j] = (rand.nextDouble() < 0.5) ? 0 : 1;
    }

    public int objectiveFitness() {

        int sum = 0;
        for (int i = 0; i < bits.length; i++) {
            sum += Arrays.stream(bits[i]).sum();
        }

        return sum;
    }

    @Override
    public Member clone() {

        int[][] newBits = new int[bits.length][bits[0].length];

        for (int i = 0; i < this.bits.length; i++)
            for (int j = 0; j < this.bits[i].length; j++)
                newBits[i][j] = this.bits[i][j];

        Member newMember = new Member(newBits);
        return newMember;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < this.bits.length; i++) {
            builder.append("[");
            for (int j = 0; j < this.bits[i].length; j++) {
                builder.append(bits[i][j]);
            }
            builder.append("], ");
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }
}
