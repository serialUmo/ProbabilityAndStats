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
    public int permutation (int n, int r){
        return factorial(n)/(factorial(n - r));
    }

    /**
     * Computes combinations. The number of ways to choose r elements out of n elements.
     * @param n The total number of elements.
     * @param r The number of elements to choose.
     * @return The number of combinations.
     */
    public int combination (int n, int r){
        return permutation(n, r)/factorial(r);
    }

    /**
     * Returns the factorial of a number.
     * @param input An integer.
     * @return Factorial of an integer.
     */
    public int factorial(int input){
        if(input == 1){
            return 1;
        }

        return input * factorial(input - 1);
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
    public double bayesTheorem1(double bga, double a, double b){
        return (bga*a)/b;
    }

    /**
     * Find P(A|B) using P(A n B), P(A), P(B).
     * @param anb The probability of A n B.
     * @param a The probability of A.
     * @param b The probability of B.
     * @return The probability of A|B.
     */
    public double bayesTheorem2(double anb, double a, double b){
        return (conditionalProbability(anb, a)*a)/b;
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
        return combination(n, x) * Math.pow(p,x) * Math.pow(1-p, n-x);
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