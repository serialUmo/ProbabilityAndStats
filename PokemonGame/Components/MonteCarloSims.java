package PokemonGame.Components;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Trainer.RareCandy;

/**
 * Holds 2 Monte Carlo Simulation relating to Pokemon.
 */
public class MonteCarloSims {

    /**
     * Tests for the probability of drawing a valid hand.
     * A valid hand is a 7 card hand that contains at least one Pokemon.
     * 
     * @param trials The number of trials in the test.
     * @param pokemon The number of pokemon in the deck.
     * @return The deduced probability.
     */
    public double validHandSim(int trials, int pokemon){
        Player monte = new Player("");
        int draws = 0;
        
        for(int i = 0; i < trials; i++){
            monte.validHandDeck(pokemon);
            monte.drawHand();
            draws++;
            while(!monte.handHasPokemon()){
                monte.handToDeck();
                monte.drawHand();
                draws++;
            }
            monte.handToDeck();
        }
        
        return 100*(trials/(double) draws);
    }

    /**
     * Tests for the probability of bricking a deck.
     * Bricking, in simple terms, is the chance that all "rare candy" cards
     * are drawn into the prize pile, with none remaining in the deck or hand.
     * 
     * @param trials The number of trials in the test.
     * @param candies The number of candies in the deck. Candies over 6 will return 0.
     * @return The deduced probability.
     */
    public double rareCandySim(int trials, int candies){
        Player monte = new Player("");
        int bricks = 0;
        
        for(int i = 0; i < trials; i++){
            monte.rareCandyDeck(candies);
            monte.resetHand();
            monte.resetPrizePile();
            
            //Draw a valid hand
            monte.drawHand();
            while(!monte.handHasPokemon()){
                monte.handToDeck();
                monte.drawHand();
            }
            
            monte.placePrizes();
            
            //Check if hand or deck has candy
            boolean foundCandy = false;
            for(Card card : monte.getHand()){
                if (card instanceof RareCandy){
                    foundCandy = true;
                }
            }
            for(Card card : monte.getDeck()){
                if (card instanceof RareCandy){
                    foundCandy = true;
                }
            }
            
            if(!foundCandy){
                bricks++;
            }
        }
        return (bricks / (double) trials)*100;
    }
}
