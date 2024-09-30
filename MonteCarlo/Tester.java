package MonteCarlo;

public class Tester {
    public static void main(String[] args){
        MonteCarloSim sim = new MonteCarloSim();

        System.out.println("Don't Switch! : " + sim.play(10000, false));
        System.out.println("Switch! : " + sim.play(10000, true));
    }
}
