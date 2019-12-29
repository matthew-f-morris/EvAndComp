package app;

public class Main {

    boolean twoPops = false;
    int popSize = 25;
    int sampleSize = 15;
    float probability = 0.005f;

    public static void main(String[] args) {

        Member a = new Member(new int[][] { { 1 } });
        Member b = new Member(new int[][] { { 2 } });
        Member c = new Member(new int[][] { { 3 } });
        Member x = new Member(new int[][] { { 4 } });
        Member y = new Member(new int[][] { { 5 } });
        Member d = new Member(new int[][] { { 6 } });
        Member e = new Member(new int[][] { { 7 } });
        Member f = new Member(new int[][] { { 8 } });

        Member[] s1 = new Member[] { a, b, c };
        Member[] s2 = new Member[] { d, e, f };

        Equation eq = new EquationOne();

        eq.getFitness(y, s1);
        eq.getFitness(x, s2);
        eq.getFitness(x, s1);
        eq.getFitness(y, s2);

        Member m = new Member(11, 7);

        // Population p = new Population(5, false, null, 100, 2);
    }
}