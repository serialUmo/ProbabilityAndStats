package PokemonGame;

import PokemonGame.Components.MonteCarloSims;

/**
 * Runs a Monte Carlo simulation for the chance of bricking a deck.
 */
public class RareCandyTest {
    public static void main(String[] args){
        MonteCarloSims sim = new MonteCarloSims();
        
        for(int i = 1; i < 60; i++){
            System.out.println(sim.rareCandySim(10000, i)/100);
        }
    }
}
