package Project2;

import java.util.ArrayList;

/**
 * The Plotter takes in a lower and upper bound and returns an array list of point coordinates to be used by the Writer.
 */
public class Plotter
{
    /**
     * Returns an array list of [x, y] values according to a formula.
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     * @param interval The space between points.
     * @return
     */
    public ArrayList<double[]> plot(double lowerBound, double upperBound, double interval){
        ArrayList<double[]> plot = new ArrayList<>();

        for (double pointer = lowerBound; pointer <= upperBound; pointer += interval){
            double[] points = {pointer, formula(pointer)};
            plot.add(points);
        }

        return plot;
    }

    /**
     * Returns the value of x^3.
     * @param x
     */
    private double formula(double x){
        return Math.pow(x, 3);
    }
}