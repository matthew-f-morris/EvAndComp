package app;

import java.util.Arrays;

public class Member {

    private int[][] bits;
    private int dimentions;

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

    public Member(int[][] bits) {
        this.bits = bits;
        this.dimentions = bits.length;
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

    @Override
    public String toString() {
        return Arrays.deepToString(bits) + " D = " + dimentions + "\n";
    }
}
