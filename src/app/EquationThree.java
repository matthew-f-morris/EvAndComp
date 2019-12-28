package app;

public class EquationThree extends Equation {

    public EquationThree(int a) {
        super(a);
    }

    public int getFitness(Member a, Member[] S) {

        int sum = -1;
        for (Member si : S) {
            sum += score(a, si);
        }

        return sum;
    }

    private int score(Member Ma, Member Mb) {

        int[] sumA = new int[Ma.getDs()];
        int[] sumB = new int[Mb.getDs()];

        int[][] bitsA = Ma.getBits();
        int[][] bitsB = Mb.getBits();

        for (int i = 0; i < bitsA.length; i++) {
            int localA = 0;
            int localB = 0;
            for (int j = 0; j < bitsA[i].length; j++) {
                localA += bitsA[i][j];
                localB += bitsB[i][j];
            }

            sumA[i] = localA;
            sumB[i] = localB;
        }

        int bestA = -1;
        int bestB = -1;
        int diff = 100000;

        for (int i = 0; i < sumA.length; i++) {
            for (int j = 0; j < sumB.length; j++) {
                int score = Math.abs(sumA[i] - sumB[j]);
                if (score < diff) {
                    bestA = i;
                    bestB = j;
                    diff = score;
                }
            }
        }

        int a = 0;
        int b = 0;

        for (int i = 0; i < bitsA[bestA].length; i++) {
            a += i;
            b += bitsB[bestB][i];
        }

        return a > b ? 1 : 0;
    }
}
