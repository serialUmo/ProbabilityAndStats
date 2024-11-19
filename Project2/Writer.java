package Project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Writes a plot's data into a .csv file.
 */
public class Writer {
    private PrintWriter out; //Writes CSV 

    /**
     * Creates a .csv file from an arraylist of points.
     * @param name The name of the file.
     * @throws FileNotFoundException
     */
    public void printCSV(ArrayList<double[]> plot, String name) throws FileNotFoundException{ //throws declaration for when file doesn't exist
        //Create file. Supress a warning that says it's unused--declaring it is enough
        @SuppressWarnings("unused")
        File file = new File(name);

        //Link printer to file
        out = new PrintWriter(name);

        //Write points to file
        out.println("x, y");
        for(double[] point : plot){
            out.println(point[0] + ", " + point[1]);
        }

        out.close();
    }
}
