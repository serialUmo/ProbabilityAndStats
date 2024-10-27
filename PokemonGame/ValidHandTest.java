package PokemonGame;

import PokemonGame.Components.MonteCarloSims;

public class ValidHandTest {
    public static void main(String[] args){
        MonteCarloSims sim = new MonteCarloSims();
        
        for(int i = 1; i < 61; i++){
            System.out.println(sim.validHandSim(10000, i));
        }
    }
}
