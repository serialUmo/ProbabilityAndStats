public class MonteCarloTesters {
    public static void main(String[] args){
        System.out.println("Door Simulation");
        MontyHallSim dSim = new MontyHallSim();
        System.out.println("Don't Switch! : " + dSim.play(10000, false));
        System.out.println("Switch! : " + dSim.play(10000, true));

        System.out.println("\nBirthday Simulation");
        BirthdaySim bSim = new BirthdaySim();
        System.out.println("Chance for 20 people to have same bday: " + bSim.run(20, 10000));
    }
}
