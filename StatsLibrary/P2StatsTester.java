/**
 * Tests the Project 2 functions of StatsLibrary.
 */
public class P2StatsTester {
    public static void main(String[] args){
        StatsLibrary tester = new StatsLibrary();

        System.out.println("Probability of drawing 3 red marbles out of 5 draws from 10 marbles of even black and red: " + tester.hypergeodist(10, 5, 5, 3));
        System.out.println("Probability of 3rd heads on 5th tails: " + tester.nbinomdist(5, 3, 0.5));
        System.out.println("Probability of 5 successes with a rate of 5: " + tester.poissondist(5, 5));
        System.out.println("Probability of any event between bounds 0 and 100: " + tester.uniformdist(0, 100));
        System.out.println("Minimum probability of an event occuring within 2 standard deviations: " + tester.tchebys(2));

    }
}
