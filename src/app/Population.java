package app;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Population {

    private SplittableRandom rand = new SplittableRandom();
    private Selector select1;
    private Selector select2;
    private Selector select3;
    private double p;
    public Member[] pop1;
    public Member[] pop2;
    private StringBuilder sb = new StringBuilder();

    public Population(Selector select1, Selector select2, Selector select3, int popSize, int target, int dimention,
            double p) {

        this.select1 = select1;
        this.select2 = select2;
        this.select3 = select3;
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

    public void run(int gen, boolean print, int printOnGen) {

        int generation = 0;
        Member[] tempA;
        Member[] tempB;

        while (generation < gen) {

            tempA = select3.selectPop(this.pop1, this.pop2);
            tempB = select3.selectPop(this.pop2, this.pop1);
            double aveA = select1.getSub(this.pop1, pop2);
            double aveB = select1.getSub(this.pop2, pop1);

            pop1 = tempA;
            pop2 = tempB;

            for (Member m : pop1)
                m.mutate(this.p);
            for (Member m : pop2)
                m.mutate(this.p);

            generation++;

            if (print && (generation % printOnGen == 0)) {
                sb.append(this.getOBF1() + ", " + this.getOBF2() + ", " + aveA / select1.sampleSize + ", "
                        + aveB / select1.sampleSize + "\n");
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