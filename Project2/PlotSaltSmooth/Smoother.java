package Project2.PlotSaltSmooth;

import java.util.ArrayList;

/**
 * Used to average out the values of salted data in order to try to determine the original data.
 */
public class Smoother {
    /**
     * Averages the points of a plot by looking a configurable amount of spaces before and after that point.
     * @param plot The plot.
     * @param window The number of indexes to view.
     * @param smoothCount The number of times to smooth.
     * @return
     */
    public ArrayList<double[]> smooth(ArrayList<double[]> plot, int window, int smoothCount){

        for(int s = 0; s < smoothCount; s++){
            for(int i = 0; i < plot.size(); i++){
                double sum = 0;
                int count = 0;

                //Look to left of index
                for(int j = i-1; j >= i-window && j > -1; j--){
                    sum += plot.get(j)[1];
                    count++;
                }
                //Look to right of index
                for(int j = i+1; j <= i+window && j < plot.size(); j++){
                    sum += plot.get(j)[1];
                    count++;
                }
                //Average
                plot.get(i)[1] = sum/count;
            }
        }

        return plot;
    }
}
