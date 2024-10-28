import java.util.ArrayList;

/**
 * Tests the functions of StatsLibrary.
 */
public class StatsLTester
{
    public static void main(String[] args){
        StatsLibrary tester = new StatsLibrary();
        
        ArrayList<Integer> numList = new ArrayList<>();

        numList.add(50);
        numList.add(40);
        numList.add(12);
        numList.add(100);
        numList.add(50);
        numList.add(50);
        numList.add(12);
        numList.add(-10);
        numList.add(0);
        numList.add(80);
        
        double mean = tester.computeMean(numList);
        double median = tester.computeMedian(numList);
        double mode = tester.computeMode(numList);
        double sd = tester.computeStandardDeviation(numList);
        
        System.out.println(numList);
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
        System.out.println("Standard Deviation: " + sd);
        System.out.println();
        System.out.println("6!: " + tester.factorial(6));
        System.out.println("10 C 3: " + tester.combination(10, 3));
        System.out.println("10 P 3: " + tester.permutation(10, 3));
        System.out.println();

        double pa = 1/4.0;
        double pb = 1/8.0;
        double panb = 1/32.0;
        System.out.println("P(A): 1/4");
        System.out.println("P(B): 1/8");
        System.out.println("P(A n B): 1/32");
        System.out.println("(Conditional Probility) P(A|B): " + tester.conditionalProbability(panb, pb));
        double bga = tester.conditionalProbability(panb, pa);
        System.out.println("(Bayes Theorem) P(A|B): " + tester.bayesTheorem(bga, pa, pb));
        System.out.println("A and B Independent?: " + tester.independenceCheck(pa, pb, panb));
        System.out.println();
        
        System.out.println("Binomial Dist. (n=10, p=0.8, x=4): " + tester.binomdist(10, 0.8, 4));
        System.out.println("Geomet Dist. (p=0.5, x=20): " + tester.geometdist(0.5, 20));
        
    }
}
