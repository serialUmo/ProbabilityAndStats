import java.util.ArrayList;

public class SetOperations {
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

    public ArrayList<Integer> intersect(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < b.size(); i++){
            if(a.contains(b.get(i))){
                output.add(b.get(i));
            }
        }

        return output;
    }

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
