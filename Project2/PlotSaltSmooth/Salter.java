package Project2.PlotSaltSmooth;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adds a randomly generated number to a plot's y-values.
 */
public class Salter {
    private Random random;

    public Salter(){
        random = new Random();
    }
    
    /**
     * Adds randomly generated numbers to a plot's y-values.
     * @param plot
     * @param saltRange
     * @return
     */
    public ArrayList<double[]> salt (ArrayList<double[]> plot, int saltRange){
        //Salt points
        for(double[] point : plot){
            point[1] += (2 * random.nextInt(saltRange)) - saltRange;
        }

        return plot;
    }
}
