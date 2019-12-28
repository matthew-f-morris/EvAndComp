package app;

public class EquationOne extends Equation {

    public EquationOne(int a) {
        super(a);
    }

    public int getFitness(Member[] S) {

        int sum = -1;
        for (Member si : S) {
            sum += score(a, si);
        }

        return sum;
    }

    private int score(int a, Member m) {
        int b = 0;
        for (int[] ds : m.getBits()) {
            for (int i : ds) {
                b += i;
            }
        }

        return a > b ? 1 : 0;
    }
}
