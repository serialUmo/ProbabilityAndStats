import java.util.Random;

public class MontyHallSim
{
    private Random random;
    private Door[] doors;

    private class Door {
        boolean winner;
        boolean open;
        public Door(){winner = false; open = false;}
        public void makeWinner(){winner = true;}
        public void open(){open = true;}
    }

    public MontyHallSim (){
        random = new Random();
        doors = new Door[3];
    }

    public double play (int count, boolean change){
        int wins = 0;
        int i = count;

        while (i > 0){
            reset();
            int solution = pickSolution();
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

    //Clear all door data
    public void reset(){
        for(int x = 0; x < 3; x++){
            doors[x] = new Door();
        }
    }

    //Pick a random door to be the winner
    private int pickSolution() {
        int solution = random.nextInt(3);
        doors[solution].makeWinner();
        return solution;
    }

    //Open a door that is not the player's guess or the winning door
    public void openADoor(int guess){
        for(int i = 0; i < 3; i++){
            if (i != guess && !doors[i].winner){
                doors[i].open();
                return;
            }
        }
    }

    //Set the player's guess to the door that isn't already open or the current guess
    public int changeGuess(int guess){
        for(int i = 0; i < 3; i++){
            if (i != guess && !doors[i].open){
                return i;
            }
        }
        return 0;
    }
}