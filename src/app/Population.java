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

    public Population(Selector select, int popSize, int target, int dimention) {

        this.select = select;

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

    public void run(int gen, boolean print, int printOnGen) {
        // double prob = 0.2;
        int generation = 0;
        Member[] tempA;
        Member[] tempB;

        while (generation < gen) {

            tempA = select.selectPop(this.pop1, this.pop2);
            tempB = select.selectPop(this.pop2, this.pop1);
            double aveA = select.getSub(this.pop1, pop2);
            double aveB = select.getSub(this.pop2, pop1);

            pop1 = tempA;
            pop2 = tempB;

            // if (generation % 50 == 0) {
            // if (prob - 0.01 > 0.005) {
            // prob -= 0.01;
            // System.out.println("Gen: " + generation + " , Prob: " + prob);
            // }
            // }

            // for (Member m : pop1)
            // m.mutate(prob);
            // for (Member m : pop2)
            // m.mutate(prob);

            for (Member m : pop1)
                m.mutate();
            for (Member m : pop2)
                m.mutate();

            generation++;

            if (print && (generation % printOnGen == 0)) {
                sb.append(this.getOBF1() + ", " + this.getOBF2() + ", " + aveA / select.sampleSize + ", "
                        + aveB / select.sampleSize + "\n");
            }

            // if (generation % 50 == 0) {
            // select.incSample(1);
            // }
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

    @Override
    public String toString() {
        return Arrays.toString(pop1) + "\n" + Arrays.toString(pop2) + "\n";
    }
}