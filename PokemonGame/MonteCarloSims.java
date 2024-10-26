package PokemonGame;

import PokemonGame.Cards.Card;
import PokemonGame.Cards.Trainer.RareCandy;

public class MonteCarloSims {

    //Monte Carlo!
    public double validHandSim(int trials, int pokemon){
        Player monte = new Player();
        int draws = 0;
        
        for(int i = 0; i < trials; i++){
            monte.validHandDeck(pokemon);
            monte.drawHand();
            draws++;
            while(!monte.handHasPokemon()){
                monte.discardHand();
                monte.drawHand();
                draws++;
            }
            monte.discardHand();
        }
        
        return 100*(trials/(double) draws);
    }

    public double rareCandySim(int trials, int candies){
        Player monte = new Player();
        int bricks = 0;
        
        for(int i = 0; i < trials; i++){
            monte.rareCandyDeck(candies);
            monte.resetHand();
            monte.resetPrizePile();
            
            //Draw a valid hand
            monte.drawHand();
            while(!monte.handHasPokemon()){
                monte.discardHand();
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
