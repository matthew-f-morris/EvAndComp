package app;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Population {

    private SplittableRandom rand = new SplittableRandom();
    private Selector select;
    public Member[] pop1;
    public Member[] pop2;
    private StringBuilder sb = new StringBuilder();
    private double min = 0.005;
    private double max = 0.05;
    private boolean hammingMutate = false;
    private boolean hof = false;
    private Hall hof1;
    private Hall hof2;
    private double p = 0.005;

    public Population(Selector select, double p, int popSize, int target, int dimention, boolean hammingMutate,
            boolean hof) {

        this.select = select;
        this.hammingMutate = hammingMutate;
        this.hof = hof;
        this.hof1 = new Hall(popSize, target, dimention);
        this.hof2 = new Hall(popSize, target, dimention);
        this.p = p;

        pop1 = new Member[popSize];
        pop2 = new Member[popSize];
        for (int i = 0; i < popSize; i++) {
            pop1[i] = new Member(target, dimention);
            pop2[i] = new Member(target, dimention);
        }
    }

    public Population(Member[] pop1, Member[] pop2) {
        this.pop1 = pop1;
        this.pop2 = pop2;
    }

    public void run(int maxGen, boolean print, int printOnGen) {

        int generation = 0;
        Member[] tempA;
        Member[] tempB;

        while (generation < maxGen) {

            if (!hof) {
                tempA = select.selectPop(this.pop1, this.pop2);
                tempB = select.selectPop(this.pop2, this.pop1);
            } else {
                tempA = select.selectPopHoF(this.pop1, this.hof2);
                tempB = select.selectPopHoF(this.pop2, this.hof1);
                if (generation % 10 == 0) {
                    System.out.println(hof1.toString());
                    System.out.println(hof2.toString());
                }
            }

            double aveA = select.getSub(this.pop1, pop2);
            double aveB = select.getSub(this.pop2, pop1);

            pop1 = tempA;
            pop2 = tempB;

            if (hammingMutate) {

                int[] pop1Score = select.getHammingScores(pop1);
                int[] pop2Score = select.getHammingScores(pop2);

                int sum1 = Arrays.stream(pop1Score).sum();
                int sum2 = Arrays.stream(pop2Score).sum();

                for (int i = 0; i < pop1.length; i++) {
                    pop1[i].mutate(calcMR(pop1Score[i], sum1));
                }

                for (int i = 0; i < pop1.length; i++) {
                    pop2[i].mutate(calcMR(pop2Score[i], sum2));
                }

            } else {
                for (Member m : pop1)
                    m.mutate(p);
                for (Member m : pop2)
                    m.mutate(p);
            }

            generation++;

            if (print && (generation % printOnGen == 0)) {
                sb.append(this.getOBF1() + ", " + this.getOBF2() + ", " + aveA / select.sampleSize + ", "
                        + aveB / select.sampleSize + "\n");
            }
        }

        if (print) {
            StringSelection selection = new StringSelection(sb.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            System.out.println("\nFinished...");
        }
    }

    protected int random() {
        return rand.nextInt(pop1.length);
    }

    public double getOBF1() {

        int sum = 0;
        for (Member m : pop1)
            sum += m.objectiveFitness();
        return sum / pop1.length;
    }

    public double getOBF2() {

        int sum = 0;
        for (Member m : pop2)
            sum += m.objectiveFitness();
        return sum / pop2.length;
    }

    public double calcMR(int score, int sum) {
        return this.max - (double) score / sum * (this.max - this.min);
    }

    @Override
    public String toString() {
        return Arrays.toString(pop1) + "\n" + Arrays.toString(pop2) + "\n";
    }
}