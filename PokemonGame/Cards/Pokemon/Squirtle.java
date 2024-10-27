package PokemonGame.Cards.Pokemon;

import PokemonGame.Cards.Energy.Energy;
import PokemonGame.Components.Player;

public class Squirtle extends Pokemon
{
    public Squirtle(){
        setName("Squirtle");
        setHp(70);
        setMove1Desc("Water Gun (W) - 20 DMG");
    }

    public boolean move1(Player p, Player opp) {
        for(Energy e : getEnergies()){
            if (e.getElement().equals("W")){
                opp.getActive().subtractHp(20);
                return true;
            }
        }
        System.out.println("Not enough energy!");
        return false;
    }


    public boolean move2(Player p, Player opp) {
        System.out.println("This pokemon doesn't have a second move!");
        return false;
    }
    
}
