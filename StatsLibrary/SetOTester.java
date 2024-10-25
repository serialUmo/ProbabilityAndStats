import java.util.ArrayList;

/**
 * Tests the functionality of SetOperations.
 */
public class SetOTester {
    public static void main(String[] args) {
        SetOperations setOp = new SetOperations();
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
    
        a.add(2);
        a.add(4);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        a.add(10);
        
        b.add(1);
        b.add(3);
        b.add(5);
        b.add(6);
        b.add(7);
        b.add(8);
        b.add(9);
        b.add(10);

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        
        System.out.println("A u B: " + setOp.union(a, b));
        System.out.println("A n B: " + setOp.intersect(a, b));
        System.out.println("!B : " + setOp.complement(a, b));
        
    }
}
