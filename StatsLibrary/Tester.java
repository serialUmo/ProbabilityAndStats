import java.util.ArrayList;

public class Tester
{
    public static void main(String[] args){
        StatsLibrary tester = new StatsLibrary();
        
        ArrayList<Integer> numList = new ArrayList<>();

        numList.add(50);
        numList.add(40);
        numList.add(12);
        numList.add(200);
        numList.add(50);
        numList.add(50);
        numList.add(12);
        numList.add(-10);
        numList.add(0);
        numList.add(80);
        
        double mean = tester.computeMean(numList);
        double median = tester.computeMedian(numList);
        double mode = tester.computeMode(numList);
        
        System.out.println(numList);
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
        System.out.println(numList);
    }
}
