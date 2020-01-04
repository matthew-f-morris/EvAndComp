package app;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Member {

    private int[][] bits;
    private int dimentions;
    private static SplittableRandom rand = new SplittableRandom();
    private static double p = 0.005;

    public Member(int size, int dimention) {

        if (size % dimention != 0) {
            System.err.println("Dimention was not a multiple of size!");
            this.bits = new int[1][1];
            this.dimentions = 1;
        } else {
            this.bits = new int[dimention][size / dimention];
            this.dimentions = dimention;
        }
    }

    public Member(int[][] bits) { // [PASS]
        this.bits = bits;
        this.dimentions = bits.length;
    }

    public int[][] getBits() {
        return this.bits;
    }

    public void setBits(int[][] newBits) { // [PASS]
        this.bits = newBits;
    }

    public int getDs() {
        return dimentions;
    }

    public void mutate() { // [PASS]

        for (int i = 0; i < this.bits.length; i++)
            for (int j = 0; j < this.bits[i].length; j++)
                if (rand.nextDouble() < p)
                    this.bits[i][j] = (rand.nextDouble() < 0.5) ? 0 : 1;
    }

    public void mutate(double prob) { // [PASS]

        for (int i = 0; i < this.bits.length; i++)
            for (int j = 0; j < this.bits[i].length; j++)
                if (rand.nextDouble() < prob)
                    this.bits[i][j] = (rand.nextDouble() < 0.5) ? 0 : 1;
    }

    public int objectiveFitness() {

        int sum = 0;
        for (int i = 0; i < bits.length; i++) {
            sum += Arrays.stream(bits[i]).sum();
        }

        return sum;
    }

    public int objectiveFitness(int i) {
        return Arrays.stream(bits[i]).sum();
    }

    @Override
    public Member clone() { // [PASS]

        int[][] newBits = new int[bits.length][bits[0].length];

        for (int i = 0; i < this.bits.length; i++)
            for (int j = 0; j < this.bits[i].length; j++)
                newBits[i][j] = this.bits[i][j];

        Member newMember = new Member(newBits);
        return newMember;
    }

    public String toStringa() { // [PASS]

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < this.bits.length; i++) {
            builder.append("[");
            for (int j = 0; j < this.bits[i].length; j++) {
                builder.append(bits[i][j]);
            }
            builder.append("], ");s
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(this.objectiveFitness());
        builder.append("]");
        return builder.toString();
    }
}
