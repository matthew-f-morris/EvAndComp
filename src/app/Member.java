package app;

public class Member {

    int[] bits;

    public Member(int size){
        bits = new int[size];
    }

    public int[] getBits(){
        return this.bits;
    }

    public void setBits(int[] newBits){
        this.bits = newBits;
    }
}