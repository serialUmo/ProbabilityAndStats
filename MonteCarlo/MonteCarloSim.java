package MonteCarlo;
import java.util.Random;

public class MonteCarloSim
{
    Random random;
    Door[] doors;

    public class Door {
        boolean winner;
        boolean open;
        public Door(){winner = false; open = false;}
        public void makeWinner(){winner = true;}
        public void open(){open = true;}
        public void reset(){winner = false;}
    }

    public MonteCarloSim (){
        random = new Random();
        doors = new Door[3];
    }

    public double play (int count, boolean change){
        int wins = 0;
        int i = count;

        while (i > 0){
            int solution = reset();
            int guess = random.nextInt(3);
            openADoor(guess);

            if (change){
                guess = changeGuess(guess);
            }

            if(guess == solution){
                wins++;
            }

            i--;
        }

        return wins / (double) count;
    }

    public int reset(){
        for(int x = 0; x < 3; x++){
            doors[x] = new Door();
        }

        int solution = random.nextInt(3);
        doors[solution].makeWinner();
        return solution;
    }

    public void openADoor(int guess){
        for(int i = 0; i < 3; i++){
            if (i != guess && !doors[i].winner){
                doors[i].open();
                return;
            }
        }
    }

    public int changeGuess(int guess){
        for(int i = 0; i < 3; i++){
            if (i != guess && !doors[i].open){
                return i;
            }
        }
        return 0;
    }
}