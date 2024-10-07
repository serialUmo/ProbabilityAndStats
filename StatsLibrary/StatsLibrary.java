import java.util.ArrayList;
import java.util.HashMap;

public class StatsLibrary
{
    //Computations
    public double computeMean(ArrayList<Integer> numList)
    {
        int sum = 0;
        for(int num : numList){
            sum += num;
        }
        return sum / (double) numList.size();
    }
    
    public double computeMedian(ArrayList<Integer> numList)
    {
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
    
    public double computeStandardDeviation(ArrayList<Integer> numList)
    {
        return Math.sqrt(computeVariance(numList));
    }

    public int permutation (int n, int r){
        return factorial(n)/(factorial(n - r));
    }

    public int combination (int n, int r){
        return permutation(n, r)/factorial(r);
    }

    public int factorial(int input){
        if(input == 1){
            return 1;
        }

        return input * factorial(input - 1);
    }

    //Sorting
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
    
    public boolean isSorted (ArrayList<Integer> list){
        for(int i = 0; i < list.size() - 1; i++){
            if (list.get(i) > list.get(i+1)){
                return false;
            }
        }
        return true;
    }
}