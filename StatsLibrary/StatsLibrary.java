import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains formulas and computations relating to probability and statistics.
 */
public class StatsLibrary
{
    //Basic Stats ================================================================

    /**
     * Computes the mean within an arraylist.
     * 
     * @param numList An arraylist of integers.
     * @return The statistical mean.
     */
    public double computeMean(ArrayList<Integer> numList)
    {
        int sum = 0;
        for(int num : numList){
            sum += num;
        }
        return sum / (double) numList.size();
    }
    
    /**
     * Sorts an arraylist and computes the median.
     * @param numList An arraylist of integers.
     * @return The median.
     */
    public double computeMedian(ArrayList<Integer> numList)
    {
        //Sort array
        bubbleSort(numList);
        
        int n = numList.size();
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return numList.get(0);
        }
        
        int x = 0; //first pointer at first index
        n--; //second pointer at end index
        while (x < n && n > x){
            x++;
            n--;
        }
        return (numList.get(x) + numList.get(n)) / 2.0;
    }
    
    /**
     * Commputes the mode out of an arraylist.
     * @param numList An arraylist of integers.
     * @return The mode.
     */
    public double computeMode(ArrayList<Integer> numList)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int mode = 0;
        
        for (int num : numList){
            if (map.get(num) == null){
                map.put(num, 1);
                if (max == 0){
                    mode = num;
                    max = 1;
                }
            }
            else {
                int tempMax = map.get(num) + 1;
                map.put(num, tempMax);
                if(tempMax > max){
                    mode = num;
                    max = tempMax;
                }
            }
        }
        
        return mode;
    }

    /**
     * Computes the variance within an arraylist.
     * @param numList An arraylist of integers.
     * @return The variance.
     */
    public double computeVariance(ArrayList<Integer> numList)
    {
        double mean = computeMean(numList);
        double variance = 0;

        if(numList.size() < 2){
            return 0;
        }

        for (int num : numList){
            variance += Math.pow(num - mean, 2);
        }

        variance /= numList.size() - 1;

        return variance;
    }
    
    /**
     * Computes the standard deviation within an arraylist.
     * @param numList An arraylist of integers.
     * @return The standard deviation.
     */
    public double computeStandardDeviation(ArrayList<Integer> numList)
    {
        return Math.sqrt(computeVariance(numList));
    }

    // Combinatorics =================================================================

    /**
     * Computes permutations. The number of ways to order r elements out of n elements.
     * @param n The total number of elements.
     * @param r The number of elements to order.
     * @return The number of permutations.
     */
    public BigInteger permutation (int n, int r){
        return factorial(n).divide(factorial(n - r));
    }

    /**
     * Computes combinations. The number of ways to choose r elements out of n elements.
     * @param n The total number of elements.
     * @param r The number of elements to choose.
     * @return The number of combinations.
     */
    public BigInteger combination (int n, int r){
        return permutation(n, r).divide(factorial(r));
    }

    /**
     * The top call for factorial.
     * @param input
     * @return
     */
    public BigInteger factorial(int input){
        BigInteger big_integer = BigInteger.valueOf(input);
        return _factorial(big_integer);
    }

    /**
     * Returns the factorial of a number.
     * @param bigInteger A big integer.
     * @return Factorial of an integer.
     */
    private BigInteger _factorial(BigInteger big){
        if(big.compareTo(BigInteger.ONE) <= 0){
            return BigInteger.ONE;
        }

        return _factorial(big.subtract(BigInteger.ONE)).multiply(big);
    }

    //Probability  ===========================================================================

    /**
     * Find P(A|B) using P(A n B), P(B).
     * @param anb The probability of A n B.
     * @param b The probability of B.
     * @return The probability of A|B.
     */
    public double conditionalProbability(double anb, double b){
        return anb/b;
    }

    /**
     * Find P(A|B) using P(B|A), P(A), P(B).
     * @param bga The probability of B|A.
     * @param a The probability of A.
     * @param b The probability of B.
     * @return The probability of A|B.
     */
    public double bayesTheorem(double bga, double a, double b){
        return (bga*a)/b;
    }

    /**
     * Checks if A and B are independent knowing P(A n B).
     * 
     * @param a The probability of A.
     * @param b The probability of B.
     * @param anb The probability of A n B.
     * @return Whether or not A and B are independent.
     */
    public boolean independenceCheck(double a, double b, double anb){
        if(conditionalProbability(anb, b) == a){
            if(conditionalProbability(anb, a) == b){
                if(anb == a * b){
                    return true;
                }
            }
        }
            return false;    
    }

    //Distributions ========================================================

    /**
     * Finds the probability of x successes occuring out of n trials with a chance p.
     * 
     * @param n The number of trials.
     * @param p The probability of success.
     * @param x The number of successes.
     * @return The chance of x successes out of n trials.
     */
    public double binomdist(int n, double p, int x){
        return combination(n, x).intValue() * Math.pow(p,x) * Math.pow(1-p, n-x);
    }

    /**
     * Finds the probability of x trials occuring until a success occurs, including the success.
     * 
     * @param p The probability of success.
     * @param x The number of trials.
     * @return The probability of x trials occuring until a successes.
     */
    public double geometdist(double p, int x){
        return p * Math.pow(1 - p, x);
    }

    /**
     * Finds the probability of getting a number of successful draws from a pool without replacement.
     * @param pop Population.
     * @param n Number of draws.
     * @param r Number of items of interest in population.
     * @param y Number of successful draws.
     * @return
     */
    public double hypergeodist(int pop, int n, int r, int y){
        double num = combination(r, y).multiply(combination(pop-r, n-y)).doubleValue();
        double den = combination(pop, n).doubleValue();
        return num/den;
    }

    /**
     * Finds the probability of getting a success on the end of a number of trials after previous successes.
     * @param trials
     * @param successes
     * @param p Probability of a success.
     * @return
     */
    public double nbinomdist(int trials, int successes, double p){
        return combination(trials - 1, successes - 1).intValue()*(Math.pow(p, successes)*Math.pow(1-p, trials-successes));
    }

    /**
     * Finds the probability of a number of successes occuring with a rate.
     * @param successes
     * @param rate
     * @return
     */
    public double poissondist(int successes, double rate){
        return (Math.pow(rate, successes)*Math.pow(Math.E, rate*-1))/factorial(successes).intValue();
    }

    /*
     * Returns the probability of an event occuring between bounds.
     */
    public double uniformdist(double a, double b){
        return 1/(b-a);
    }

    /**
     * Returns the minimum probability of a value being within a certain number of standard deviations.
     * @param k The number of standard deviations.
     * @return
     */
    public double tchebys(double k){
        return 1.0-(1.0/Math.pow(k, 2));
    }

    //Sorting ================================================================

    /**
     * Orders an arraylist of integers in order of ascending.
     * @param list An arraylist of integers.
     */
    public void bubbleSort (ArrayList<Integer> list){
        while(!isSorted(list)){
            for(int i = 0; i < list.size() - 1; i++){
                if (list.get(i) > list.get(i+1)){
                    int temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                }
            }
        }
    }
    
    /**
     * Checks if an arraylist of integers is sorted.
     * @param list An arraylist of integers.
     * @return Whether or not the list is sorted.
     */
    public boolean isSorted (ArrayList<Integer> list){
        for(int i = 0; i < list.size() - 1; i++){
            if (list.get(i) > list.get(i+1)){
                return false;
            }
        }
        return true;
    }

    
}