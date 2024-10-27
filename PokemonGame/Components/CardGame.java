package PokemonGame.Components;

import java.util.Random;
import java.util.Scanner;

import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Cards.Pokemon.Pokemon;
import PokemonGame.Cards.Trainer.Trainer;

/**
 * Runs a Pokemon card game.
 */
public class CardGame
{
    private Random rng;
    private Scanner scan;
    private Player p1;
    private Player p2;

    private boolean done;
    private boolean turnDone;
    private boolean placedEnergy;
    private boolean placedSupporter;
    private String loser;
    
    public CardGame(){
        rng = new Random();
        scan = new Scanner(System.in);
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
    }
    
    /**
     * Runs a Pokemon match.
     */
    public void run(){
        say("WELCOME ====================================");
        
        reset();
        //Starting up
        fillDecks();
        drawHands();
        placePrizePiles();
        pickInitialActive(p1);
        pickInitialActive(p2);

        done = false;

        //Coin Flip
        if(rng.nextBoolean()){
            say("Coin: TAILS!\nPlayer 2 goes first!");
            doTurn(p2);
        }
        else{
            say("Coin: HEADS!\nPlayer 1 goes first!");
        }

        while(!done){
            doTurn(p1);
            if(!done){
                doTurn(p2);
            }
        }

        say("=========================================");
        say(loser + " lost!");
    }

    /**
     * Performs a player's turn within a Pokemon match.
     * During a turn, a player may attack, play a trainer card, place an energy card, swap active pokemon with benched pokemon, and end turn without attacking.
     * @param p The player whose turn it is.
     */
    private void doTurn(Player p) {
        br();
        say(p + " =========================================");

        //Draw card
        say("Drawing a card...");
        if(!p.drawCard()){
            say("NO CARDS LEFT!");
            done =  true;
            loser = p.toString();
            return;
        }

        

        placedEnergy = false;
        placedSupporter = false;
        turnDone = false;
        while(!turnDone){
            br();
            //Display information
            say("ACTIVE: " + p.getActive());
            say("BENCH: ");
            p.printBench();
            say("HAND: ");
            p.printHand();

            say("What do you wanna do? (Enter #)");
            say("1. ATTACK\n" +
            "2. PLACE ENERGY CARD\n" +
            "3. PLAY TRAINER CARD\n" +
            "4. SWAP ACTIVE POKEMON\n" +
            "5. BENCH POKEMON\n" +
            "6. END TURN");
            int choice = scan.nextInt();

            if(choice == 1){
                attack(p, getOpp(p));

                //Check if pokemon fainted
                if(!p.getActive().isAlive()){
                    //Draw Prize
                    done = !winPrize(getOpp(p));
                    if(done){
                        say("ALL PRIZES DRAWN!");
                        loser = p.toString();
                        return;
                    }

                    //Swap Pokemon
                    done = !swapActive(p);
                    if(done){
                        say("OUT OF POKEMON!");
                        loser = p.toString();
                        return;
                    }
                }
                if(!getOpp(p).getActive().isAlive()){
                    //Draw Prize
                    done = !winPrize(getOpp(p));
                    if(done){
                        say("ALL PRIZES DRAWN!");
                        loser = p.toString();
                        return;
                    }
                    //Swap Pokemon
                    done = !swapActive(getOpp(p));
                    if(done){
                        say("OUT OF POKEMON!");
                        loser = getOpp(p).toString();
                        return;
                    }
                }
            }
            else if (choice == 2){
                placeEnergy(p);
            }
            else if (choice == 3){
                playTrainer(p);
            }
            else if (choice == 4){
                swapActive(p);
            }
            else if (choice == 5){
                bench(p);
            }
            else if (choice == 6){
                say("Passed!");
                return;
            }
            else{
                say("What?");
            }
        }

        
    }

    /**
     * Selects active pokemon moves and attempts to perform them.
     * @param p The player performing the action.
     * @param opp The opposing player.
     */
    private void attack(Player p, Player opp) {
        br();
        say(p.getActive().toString());
        say("1: " + p.getActive().getMove1Desc());
        say("2: " + p.getActive().getMove2Desc());
        say("-1: CANCEL");

        boolean attackDone = false;

        while(!attackDone){
            int choice = scan.nextInt();

            if(choice == -1){
                return;
            }
            if(choice == 1){
                attackDone = p.getActive().move1(p, opp);
            }
            if(choice == 2){
                attackDone = p.getActive().move2(p, opp);
            }
        }
        turnDone = true;
    }

    /**
     * Picks an energy card from hand to attach to a pokemon.
     * @param p The player performing the action.
     */
    private void placeEnergy(Player p) {
        br();
        if(placedEnergy){
            say("You can only place one energy card per turn!");
            return;
        }

        //Pick Energy
        say(p + ", pick a Energy to attach! (Enter #)");
        p.printHand();
        say("-1: CANCEL");
        int choice = scan.nextInt();
        if(choice == -1){
            return;
        }
        while(!(p.getHand().get(choice) instanceof Energy)){
            say("That's not an Energy card!");
            choice = scan.nextInt();
            if(choice == -1){
                return;
            }
        }
        int energyIndex = choice;


        br();
        //Pick Pokemon
        say(p + ", pick a Pokemon to attach! (Enter #)");
        say("-1: ACTIVE - " + p.getActive());
        say("BENCH: ");
        p.printBench();
        say("-2: CANCEL");
        choice = scan.nextInt();
        if(choice == -2){
            return;
        }
        if(choice == -1){
            p.getActive().getEnergies().add((Energy) p.getHand().remove(energyIndex));
            placedEnergy = true;
            return;
        }
        while(!(p.getBench().get(choice) instanceof Pokemon)){
            say("That's not a Pokemon, silly!");
            choice = scan.nextInt();
            if(choice == -1){
                p.getActive().getEnergies().add((Energy) p.getHand().remove(energyIndex));
                placedEnergy = true;
                return;
            }
            if(choice == -2){
                return;
            }
        }

        p.getBench().get(choice).getEnergies().add((Energy) p.getHand().remove(energyIndex));
        placedEnergy = true;
    }

    /**
     * Picks a Trainer card to use. Returns if already played a supporter card.
     * @param p The player performing the action.
     */
    private void playTrainer(Player p) {
        br();
        //Pick Trainer
        say(p + ", pick a Trainer Card to use! (Enter #)");
        p.printHand();
        say("-1: CANCEL");
        int choice = scan.nextInt();
        if(choice == -1){
            return;
        }
        while(!(p.getHand().get(choice) instanceof Trainer)){
            say("That's not a Trainer card!");
            choice = scan.nextInt();
            if(choice == -1){
                return;
            }
        }

        //Check supporter status
        Trainer temp = (Trainer) p.getHand().get(choice);
        if(temp.isSupporter() && placedSupporter){
            say("This is a supporter card! You already played one this turn!");
            return;
        }

        //Take out card
        p.getHand().remove(choice);

        //Use and discard
        temp.use(p);
        p.discard(temp);
    }

    /**
     * Chooses a pokemon from the bench to swap with the active pokemon.
     * @param p The player performing the action.
     * @return Whether or not a swap can be and was performed.
     */
    private boolean swapActive(Player p) {
        br();
        if(p.getBench().size() == 0){
            say("There's no one in your bench!");
            return false;
        }

        say("ACTIVE: " + p.getActive());
        say("BENCH:");
        p.printBench();

        if(p.getActive().isAlive()){
            say("-1: CANCEL");
        }

        say(p + ", swap " + p.getActive().getName() + " with who? (Enter #)");
        int choice = scan.nextInt();

        if(p.getActive().isAlive() && choice == -1){
            return false;
        }

        if(p.getActive().isAlive()){
            p.getBench().add(p.getActive());
        }
        else{
            while(p.getActive().getEnergies().size() != 0){
                p.discard(p.getActive().getEnergies().remove(0));;
            }
            p.discard(p.getActive());
        }
        p.setActive(p.getBench().remove(choice));
        return true;
    }

    /**
     * Chooses a pokemon from the hand to bench.
     * @param p The player performing the action.
     */
    private void bench(Player p) {
        br();
        if(p.getBench().size() == 5){
            say("Your bench is full, dude!");
            return;
        }

        say(p + ", pick a Pokemon to bench! (Enter #)");
        p.printHand();
        say("-1: CANCEL");
        int choice = scan.nextInt();
        if(choice == -1){
            return;
        }
        while(!(p.getHand().get(choice) instanceof Pokemon)){
            say("That's not a Pokemon, silly!");
            choice = scan.nextInt();
            if(choice == -1){
                return;
            }
        }
        say("Benched " + p.getHand().get(choice).getName() + "!");
        p.getBench().add((Pokemon) p.getHand().remove(choice));
    }

    /**
     * Draws a card from the prize pile.
     * @param p The player drawing.
     * @return If there are remaining prizes after drawing.
     */
    public boolean winPrize(Player p){
        say(p + " draws a prize card!");
        p.takePrize();

        if(p.getPrizes().size() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    //GAME START METHODS =======================================

    /**
     * Fills player and opponent decks.
     */
    private void fillDecks() {
        p1.fillDeck();
        p2.fillDeck();
    }

    /**
     * Draws player and opponent hands, accounting for mulligans.
     */
    private void drawHands() {
        //Mulligan Counters
        int p1Mulls = 0;
        int p2Mulls = 0;

        //Player draw hand
        say("Drawing Player 1's hand...");
        p1.drawHand();

        while(!p1.handHasPokemon()){
            p1Mulls++;
            p1.handToDeck();
            p1.drawHand();
        }
        if(p1Mulls > 0){
            say(p1 + " had " + p1Mulls + " mulligans. " + p2 + " gets " + p1Mulls + " extra cards.");
        }
        
        //Opponent draw hand
        say("Drawing Player 2's hand...");
        p2.drawHand();

        while(!p2.handHasPokemon()){
            p2Mulls++;
            p2.handToDeck();
            p2.drawHand();
        }
        if(p2Mulls > 0){
            say(p2 + " had " + p2Mulls + " mulligans. " + p1 + " gets " + p2Mulls + " extra cards.");
        }
        
        //Draw for mulligans
        for(int i = 0; i < p1Mulls; i++){p2.drawCard();}
        for(int i = 0; i < p2Mulls; i++){p1.drawCard();}
    }

    /**
     * Places player and opponent prize piles.
     */
    private void placePrizePiles() {
        say("Placing prize piles...");
        p1.placePrizes();
        p2.placePrizes();
    }

    /**
     * Chooses initial active pokemon.
     * @param p The player performing the action.
     */
    private void pickInitialActive(Player p) {
        br();
        say(p + ", pick a Pokemon to place as your active! (Enter #)");
        p.printHand();
        int choice = scan.nextInt();
        while(!(p.getHand().get(choice) instanceof Pokemon)){
            say("Huh?! That's not a Pokemon!");
            choice = scan.nextInt();
        }
        say("You chose " + p.getHand().get(choice).getName() + "!");
        p.setActive((Pokemon) p.getHand().remove(choice));
    }

    // UTILITY ===========================================================

    /**
     * Prints a line of text.
     * @param text
     */
    private void say(String text){
        System.out.println(text);
    }

    /**
     * Makes a line break.
     */
    private void br(){
        System.out.println();
    }
    
    /**
     * Clears player and opponent data.
     */
    private void reset(){
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
    }

    /**
     * Gets opposing player.
     * @param p The current player.
     * @return The opposing player.
     */
    private Player getOpp(Player p){
        if(p.toString() == p1.toString()){
            return p2;
        }
        else{
            return p1;
        }
    }
}