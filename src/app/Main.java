package app;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int popSize = 25;
        int sampleSize = 15;
        float probability = 0.005f;

        Equation eq = new EquationOne(sampleSize);
        Selector select = new Selector(eq);
        Population p = new Population(eq, select, probability, 25, 100, 1);

        System.out.println(p.toString());

        // Member a = new Member(new int[][] { { 1 } });
        // Member b = new Member(new int[][] { { 2 } });
        // Member c = new Member(new int[][] { { 3 } });
        // Member d = new Member(new int[][] { { 4 } });
        // Member e = new Member(new int[][] { { 5 } });
        // Member f = new Member(new int[][] { { 6 } });
        // Member g = new Member(new int[][] { { 7 } });
        // Member h = new Member(new int[][] { { 8 } });
        // Member i = new Member(new int[][] { { 9 } });
        // Member j = new Member(new int[][] { { 10 } });
        // Member k = new Member(new int[][] { { 11 } });
        // Member l = new Member(new int[][] { { 12 } });
        // Member m = new Member(new int[][] { { 13 } });
        // Member n = new Member(new int[][] { { 14 } });
        // Member o = new Member(new int[][] { { 15 } });
        // Member p = new Member(new int[][] { { 16 } });
        // Member q = new Member(new int[][] { { 17 } });
        // Member r = new Member(new int[][] { { 18 } });
        // Member s = new Member(new int[][] { { 19 } });
        // Member t = new Member(new int[][] { { 20 } });
        // Member u = new Member(new int[][] { { 21 } });
        // Member v = new Member(new int[][] { { 22 } });
        // Member w = new Member(new int[][] { { 23 } });
        // Member x = new Member(new int[][] { { 24 } });
        // Member y = new Member(new int[][] { { 25 } });

        // Member[] s2 = new Member[] { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,
        // q, r, s, t, u, v, w, x, y };

        // Equation eq = new EquationOne(sampleSize);
        // Selector select = new Selector(eq);

        // // for (int i = 0; i < 100; i++) {
        // // Member selected = s2[select.select(s2)];
        // // System.out.println(selected);
        // // }

        // // Population p = new Population(5, false, null, 100, 2);

        // // Member m = new Member(100, 1);

        // // for (int i = 0; i < 100; i++) {
        // // System.out.print(m);
        // // System.out.println(eq.objectiveFitness(m));

        // // for (int j = 0; j < 600; j++)
        // // m.mutate();

        // // System.out.print(m);
        // // System.out.println(eq.objectiveFitness(m));

        // System.out.println(Arrays.toString(s2));
        // for (int jj = 0; jj < 10; jj++)
        // for (Member aa : s2)
        // aa.mutate();

        // Member[] newPop = select.selectPop(s2);
        // System.out.println();
        // System.out.println(Arrays.toString(newPop));
    }
}