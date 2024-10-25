import java.util.ArrayList;

/**
 * Contains computations relating to set operations.
 */
public class SetOperations {
    /**
     * Returns an arraylist containing elements from A union B.
     * @param a An arraylist of integers.
     * @param b An arraylist of integers.
     * @return The resulting arraylist.
     */
    public ArrayList<Integer> union(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < a.size(); i++){
            output.add(a.get(i));
        }

        for (int i = 0; i < b.size(); i++){
            if(!a.contains(b.get(i))){
                output.add(b.get(i));
            }
        }

        return output;
    }

    /**
     * Returns an arraylist containing elements from A intersect B.
     * @param a An arraylist of integers.
     * @param b An arraylist of integers.
     * @return The resulting arraylist.
     */
    public ArrayList<Integer> intersect(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < b.size(); i++){
            if(a.contains(b.get(i))){
                output.add(b.get(i));
            }
        }

        return output;
    }

    /**
     * Returns the complement of B, where A is the universal set.
     * @param a Arraylist of the universal set.
     * @param b An arraylist of integers.
     * @return The resulting arraylist.
     */
    public ArrayList<Integer> complement(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < b.size() && i < a.size(); i++){
            if(!b.contains(a.get(i))){
                output.add(a.get(i));
            }
        }

        return output;
    }
}
