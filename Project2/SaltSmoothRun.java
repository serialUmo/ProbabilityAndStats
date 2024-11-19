package Project2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * When ran, uses the coded parameters to create 3 .csv files: unmodified data, salted data, and the salted data when smoothed a number of times.
 */
public class SaltSmoothRun{
    public static void main(String[] args) throws FileNotFoundException{
        Writer writer = new Writer();
        Plotter plotter = new Plotter();
        Salter salter = new Salter();
        Smoother smoother = new Smoother();

        //Parameters
        double lowerBound = -100;
        double upperBound = 100;
        double interval = 0.1;
        int saltRange = 250000;
        int window = 3;
        int smoothCount = 5;

        ArrayList<double[]> plot = plotter.plot(lowerBound, upperBound, interval);

        writer.printCSV(plot, "default.csv");
        System.out.println("Default chart complete.");

        salter.salt(plot, saltRange);
        writer.printCSV(plot, "salted.csv");
        System.out.println("Salted chart complete.");

        smoother.smooth(plot, window, smoothCount);
        writer.printCSV(plot, "smoothed.csv");
        System.out.println("Smoothed chart complete.");
    }
}
