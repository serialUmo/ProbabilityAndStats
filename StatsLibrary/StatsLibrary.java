import java.util.ArrayList;
import java.util.HashMap;

public class StatsLibrary
{
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