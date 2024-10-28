package PokemonGame;

import PokemonGame.Components.MonteCarloSims;

/**
 * Runs a Monte Carlo simulation for the chance of bricking a deck.
 */
public class RareCandyTest {
    public static void main(String[] args){
        MonteCarloSims sim = new MonteCarloSims();
        
        for(int i = 1; i < 8; i++){
            System.out.println("The chance of bricking with " + i + " candies is "
                                 + sim.rareCandySim(1000000, i)/100);
        }
    }
}
