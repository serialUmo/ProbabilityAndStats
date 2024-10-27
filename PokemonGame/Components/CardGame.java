package PokemonGame.Components;

import java.util.Random;
import java.util.Scanner;

import PokemonGame.Cards.Pokemon.Pokemon;

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

        
    }

    private void doTurn(Player p) {
        say(p + " =========================================");

        //Draw card
        say("Drawing a card...");
        if(!p.drawCard()){
            say("NO CARDS LEFT!");
            done =  true;
            loser = p.toString();
            return;
        }

        //Display information
        say("ACTIVE: " + p.getActive());
        say("BENCH: ");
        p.printBench();
        say("HAND: ");
        p.printHand();

        turnDone = false;
        while(!turnDone){
            say("What do you wanna do? (Enter #)");
            say("1. ATTACK\n" +
            "2. PLACE ENERGY CARD\n" +
            "3. PLAY TRAINER CARD\n" +
            "4. SWAP ACTIVE POKEMON\n" +
            "5. BENCH POKEMON");
            int choice = scan.nextInt();

            if(choice == 1){
                attack(p, getOpp(p));
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
            else{
                say("What?");
            }
        }


    }

    private void attack(Player p, Player opp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    private void placeEnergy(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeEnergy'");
    }

    private void playTrainer(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playTrainer'");
    }

    /**
     * Chooses a pokemon from the bench to swap with the active pokemon.
     * @param p The player performing the action.
     */
    private void swapActive(Player p) {
        if(p.getBench().size() == 0){
            say("There's no one in your bench!");
            return;
        }

        say("ACTIVE: " + p.getActive());
        say("BENCH:");
        p.printBench();
        say("-1: CANCEL");

        say("Swap " + p.getActive().getName() + " with who? (Enter #)");
        int choice = scan.nextInt();

        if(choice == -1){
            return;
        }

        p.activeToBench();
        p.setActive(p.getBench().remove(choice));
    }

    /**
     * Chooses a pokemon from the hand to bench.
     * @param p The player performing the action.
     */
    private void bench(Player p) {
        if(p.getBench().size() == 5){
            say("Your bench is full, dude!");
            return;
        }

        say("Pick a Pokemon to bench! (Enter #)");
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
        p.handToActive(choice);
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
     * @param p
     */
    private void pickInitialActive(Player p) {
        say(p + ", pick a Pokemon to place as your active! (Enter #)");
        p.printHand();
        int choice = scan.nextInt();
        while(!(p.getHand().get(choice) instanceof Pokemon)){
            say("Huh?! That's not a Pokemon!");
            choice = scan.nextInt();
        }
        say("You chose " + p.getHand().get(choice).getName() + "!");
        p.handToActive(choice);
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