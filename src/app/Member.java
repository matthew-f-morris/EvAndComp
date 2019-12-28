package app;

public class Member {

    private int[][] bits;
    private int dimentions;

    public Member(int size, int dimention) {
        this.bits = new int[dimention][size / dimention];
        this.dimentions = dimention;
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
}
